package com.undcon.app.filtering;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.querydsl.core.types.dsl.BooleanExpression;
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
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		}

	}

	public BooleanExpression buildFilteredResult(List<SearchCriteria> filters)
			throws NoSuchFieldException, SecurityException {
		BooleanExpression expression = null;
		for (SearchCriteria filter : filters) {
			Class type = pathBuilder.getType().getDeclaredField(filter.getKey()).getType();

			//filter=name:João
			switch (filter.getOperation()) {
			case "=":
				PathBuilder<Object> propertyEq = pathBuilder.get(filter.getKey());
				expression = expressionAnd(expression, propertyEq.eq(convertToValue(type, filter.getValue())));
				break;
			case ":":
				StringPath path = pathBuilder.getString(filter.getKey());
				expression = expressionAnd(expression, path.containsIgnoreCase(filter.getValue().toString()));
				break;
			case "!=":
				PathBuilder<Object> propertyNe = pathBuilder.get(filter.getKey());
				expression = expressionAnd(expression, propertyNe.ne(filter.getValue()));
				break;
			case ">":
				StringPath propertyGt = pathBuilder.getString(filter.getKey());
				expression = expressionAnd(expression, propertyGt.gt(filter.getValue()));
				break;
			case "<":
				StringPath propertyLt = pathBuilder.getString(filter.getKey());
				expression = expressionAnd(expression, propertyLt.lt(filter.getValue()));
				break;
			case ">=":
				StringPath propertyGoe = pathBuilder.getString(filter.getKey());
				expression = expressionAnd(expression, (propertyGoe.goe(filter.getValue())));
				break;
			case "<=":
				StringPath propertyLoe = pathBuilder.getString(filter.getKey());
				expression = expressionAnd(expression, propertyLoe.loe(filter.getValue()));
				break;
			default:
				break;
			}
		}
		return expression;
	}

	private Object convertToValue(Class type, String value) {
		switch (type.getName()) {
		case "long":
			return Long.valueOf(value);
		case "java.lang.String":
			return value;
		case "Integer":
			return Integer.parseInt(value);
		case "double":
			return Double.parseDouble(value);
			
		//TODO Falta converter outros tipos	
		default:
			break;
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
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|>=|<=|contains|=)((\\w|\\s)+?),");
		Matcher matcher = pattern.matcher(search + ",");
		List<SearchCriteria> criterias = new ArrayList<SearchCriteria>();
		while (matcher.find()) {
			criterias.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
		}
		return criterias;
	}

}
