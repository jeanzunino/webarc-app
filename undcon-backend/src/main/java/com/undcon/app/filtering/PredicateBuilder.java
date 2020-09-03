package com.undcon.app.filtering;

import java.sql.Date;
import java.text.Normalizer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

public class PredicateBuilder<T> {

	private PathBuilder<T> pathBuilder;

	public PredicateBuilder(PathBuilder<T> pathBuilder) {
		this.pathBuilder = pathBuilder;
	}

	public BooleanExpression buildFilteredResult(String filter) {
		List<SearchCriteria> filters = getCriterias(filter);
		try {
			return buildFilteredResult(filters);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}

	}

	public BooleanExpression buildFilteredResult(List<SearchCriteria> filters) throws SecurityException {
		BooleanExpression expression = null;
		for (SearchCriteria filter : filters) {
			Class type;
			try {
				String key = filter.getKey();
				String[] fieldWithDot = key.split("\\.");
				if(fieldWithDot.length > 0) {
					key = fieldWithDot[0];
				}
				type = pathBuilder.getType().getDeclaredField(key).getType();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				throw new IllegalArgumentException(
						"Invalid field filter '" + filter.getKey() + "' for type " + pathBuilder.getType());
			}
			switch (filter.getOperation()) {
			case "=":
				if (type.isEnum()) {
					Class<Enum> e = type;
					Enum[] enumConstants = e.getEnumConstants();
					for (Enum enumConstant : enumConstants) {
						if (enumConstant.name().equals(filter.getValue().toUpperCase())) {
							EnumPath propertyEnum = pathBuilder.getEnum(filter.getKey(), e);
							expression = expressionAnd(expression, propertyEnum.eq(enumConstant));
						}
					}

				} else {
					PathBuilder<Object> propertyEq = pathBuilder.get(filter.getKey());
					expression = expressionAnd(expression, propertyEq.eq(convertToValue(type, filter.getValue())));
				}
				break;
			case "!=":
				if (type.isEnum()) {
					Class<Enum> e = type;
					Enum[] enumConstants = e.getEnumConstants();
					for (Enum enumConstant : enumConstants) {
						if (enumConstant.name().equals(filter.getValue().toUpperCase())) {
							EnumPath propertyEnum = pathBuilder.getEnum(filter.getKey(), e);
							expression = expressionAnd(expression, propertyEnum.ne(enumConstant));
						}
					}
				} else {
					PathBuilder<Object> propertyEq = pathBuilder.get(filter.getKey());
					expression = expressionAnd(expression, propertyEq.ne(convertToValue(type, filter.getValue())));
				}
				break;
			case ":":
				StringPath path = pathBuilder.getString(filter.getKey());
				expression = expressionAnd(expression, path.containsIgnoreCase(filter.getValue().toString()));
				break;
			case ">":
				switch (type.getName()) {
				case "long":
				case "java.lang.Long":
					NumberPath<Long> propertyGtLong = pathBuilder.getNumber(filter.getKey(), Long.class);
					expression = expressionAnd(expression, propertyGtLong.gt(Long.valueOf(filter.getValue())));
					break;
				case "int":
				case "java.lang.Integer":
					NumberPath<Integer> propertyGtInteger = pathBuilder.getNumber(filter.getKey(), Integer.class);
					expression = expressionAnd(expression, propertyGtInteger.gt(Integer.valueOf(filter.getValue())));
					break;
				case "double":
				case "java.lang.Double":
					NumberPath<Double> propertyGtDouble = pathBuilder.getNumber(filter.getKey(), Double.class);
					expression = expressionAnd(expression, propertyGtDouble.gt(Double.valueOf(filter.getValue())));
					break;
				case "java.sql.Date":
					DatePath propertyGtDate = pathBuilder.getDate(filter.getKey(), Date.class);
					expression = expressionAnd(expression,
							propertyGtDate.gt(java.util.Date.from(Instant.parse(filter.getValue()))));
					break;
				default:
					throw new IllegalArgumentException(
							"Invalid operation filter " + filter.getOperation() + " for type " + type.getName());
				}

				break;
			case "<":
				switch (type.getName()) {
				case "long":
				case "java.lang.Long":
					NumberPath<Long> propertyGtLong = pathBuilder.getNumber(filter.getKey(), Long.class);
					expression = expressionAnd(expression, propertyGtLong.lt(Long.valueOf(filter.getValue())));
					break;
				case "int":
				case "java.lang.Integer":
					NumberPath<Integer> propertyGtInteger = pathBuilder.getNumber(filter.getKey(), Integer.class);
					expression = expressionAnd(expression, propertyGtInteger.lt(Integer.valueOf(filter.getValue())));
					break;
				case "double":
				case "java.lang.Double":
					NumberPath<Double> propertyGtDouble = pathBuilder.getNumber(filter.getKey(), Double.class);
					expression = expressionAnd(expression, propertyGtDouble.lt(Double.valueOf(filter.getValue())));
					break;
//				case "java.sql.Date":
//					DatePath propertyGtDate = pathBuilder.getDate(filter.getKey(), Date.class);
//					expression = expressionAnd(expression,
//							propertyGtDate.lt(Date.from(Instant.parse(filter.getValue()))));
//					break;
				default:
					throw new IllegalArgumentException(
							"Invalid operation filter " + filter.getOperation() + " for type " + type.getName());
				}
				break;
			case ">=":
				switch (type.getName()) {
				case "long":
				case "java.lang.Long":
					NumberPath<Long> propertyGtLong = pathBuilder.getNumber(filter.getKey(), Long.class);
					expression = expressionAnd(expression, propertyGtLong.gt(Long.valueOf(filter.getValue()))
							.or(propertyGtLong.eq(Long.valueOf(filter.getValue()))));
					break;
				case "int":
				case "java.lang.Integer":
					NumberPath<Integer> propertyGtInteger = pathBuilder.getNumber(filter.getKey(), Integer.class);
					expression = expressionAnd(expression, propertyGtInteger.gt(Integer.valueOf(filter.getValue()))
							.or(propertyGtInteger.eq(Integer.valueOf(filter.getValue()))));
					break;
				case "double":
				case "java.lang.Double":
					NumberPath<Double> propertyGtDouble = pathBuilder.getNumber(filter.getKey(), Double.class);
					expression = expressionAnd(expression, propertyGtDouble.gt(Double.valueOf(filter.getValue()))
							.or(propertyGtDouble.eq(Double.valueOf(filter.getValue()))));
					break;
//				case "java.sql.Date":
//					DatePath propertyGtDate = pathBuilder.getDate(filter.getKey(), Date.class);
//					expression = expressionAnd(expression,
//							propertyGtDate.gt(Date.from(Instant.parse(filter.getValue()))));
//					break;
				default:
					throw new IllegalArgumentException(
							"Invalid operation filter " + filter.getOperation() + " for type " + type.getName());
				}
				break;
			case "<=":
				switch (type.getName()) {
				case "long":
				case "java.lang.Long":
					NumberPath<Long> propertyGtLong = pathBuilder.getNumber(filter.getKey(), Long.class);
					expression = expressionAnd(expression, propertyGtLong.lt(Long.valueOf(filter.getValue()))
							.or(propertyGtLong.eq(Long.valueOf(filter.getValue()))));
					break;
				case "int":
				case "java.lang.Integer":
					NumberPath<Integer> propertyGtInteger = pathBuilder.getNumber(filter.getKey(), Integer.class);
					expression = expressionAnd(expression, propertyGtInteger.lt(Integer.valueOf(filter.getValue()))
							.or(propertyGtInteger.eq(Integer.valueOf(filter.getValue()))));
					break;
				case "double":
				case "java.lang.Double":
					NumberPath<Double> propertyGtDouble = pathBuilder.getNumber(filter.getKey(), Double.class);
					expression = expressionAnd(expression, propertyGtDouble.lt(Double.valueOf(filter.getValue()))
							.or(propertyGtDouble.eq(Double.valueOf(filter.getValue()))));
					break;
//				case "java.sql.Date":
//					DatePath propertyGtDate = pathBuilder.getDate(filter.getKey(), Date.class);
//					expression = expressionAnd(expression,
//							propertyGtDate.gt(Date.from(Instant.parse(filter.getValue()))));
//					break;
				default:
					throw new IllegalArgumentException(
							"Invalid operation filter " + filter.getOperation() + " for type " + type.getName());
				}
				break;
			default:
				throw new IllegalArgumentException(
						"Invalid operation filter " + filter.getOperation() + " for type " + type.getName());
			}

		}
		return expression;
	}

	private Object convertToValue(Class type, String value) {
		switch (type.getName()) {
		case "long":
		case "java.lang.Long":
			return Long.valueOf(value);
		case "java.lang.String":
			return value;
		case "int":
		case "java.lang.Integer":
			return Integer.parseInt(value);
		case "double":
		case "java.lang.Double":
			return Double.parseDouble(value);

		case "java.lang.Boolean":
		case "boolean":
			return Boolean.parseBoolean(value);
		default:
			break;
		}
		
		if(type.getName().contains("Entity")) {
			return Long.parseLong(value);
		}
		return null;
	}

	private BooleanExpression expressionAnd(BooleanExpression expression, BooleanExpression nextExpression) {
		if (expression == null) {
			return nextExpression;
		}
		return expression.and(nextExpression);
	}

	public static List<SearchCriteria> getCriterias(String search) {
		search = search == null ? "" : search.trim();
		// https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
		search = deAccent(search);
		Pattern pattern = Pattern.compile("(\\w+?|\\w+.\\w+)(:|<|>|>=|<=|contains|=|!=)((\\w|\\s|-)+?),");
		Matcher matcher = pattern.matcher(search + ",");
		List<SearchCriteria> criterias = new ArrayList<SearchCriteria>();
		while (matcher.find()) {
			criterias.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
		}

		if (search != null && search.length() > 0 && criterias.isEmpty()) {
			throw new IllegalArgumentException("Invalid Filter");
		}
		return criterias;
	}
	
	public static String deAccent(String str) {
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
}
