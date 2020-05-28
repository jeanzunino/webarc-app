package com.undcon.app.filtering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.model.QProductEntity;

class PredicateBuilderTest {

	@Test
	void getCriteriasSemEspaco() {
		List<SearchCriteria> criterias = PredicateBuilder.getCriterias("name:Cerveja");
		assertEquals(1, criterias.size());
		assertEquals("name", criterias.get(0).getKey());
		assertEquals(":", criterias.get(0).getOperation());
		assertEquals("Cerveja", criterias.get(0).getValue());
	}

	@Test
	void getCriteriasComEspacoMeio() {
		List<SearchCriteria> criterias = PredicateBuilder.getCriterias("name:Cerveja Skol");
		assertEquals(1, criterias.size());
		assertEquals("name", criterias.get(0).getKey());
		assertEquals(":", criterias.get(0).getOperation());
		assertEquals("Cerveja Skol", criterias.get(0).getValue());
	}

	@Test
	void getCriteriasComEspacoFim() {
		List<SearchCriteria> criterias = PredicateBuilder.getCriterias("name:Cerveja Skol   ");
		assertEquals(1, criterias.size());
		assertEquals("name", criterias.get(0).getKey());
		assertEquals(":", criterias.get(0).getOperation());
		assertEquals("Cerveja Skol", criterias.get(0).getValue());
	}

	@Test
	void getCriteriasComDoisFiltrosTest() {
		List<SearchCriteria> criterias = PredicateBuilder.getCriterias("name:Cerveja Skol, status=1");
		assertEquals(2, criterias.size());
		assertEquals("name", criterias.get(0).getKey());
		assertEquals(":", criterias.get(0).getOperation());
		assertEquals("Cerveja Skol", criterias.get(0).getValue());

		assertEquals("status", criterias.get(1).getKey());
		assertEquals("=", criterias.get(1).getOperation());
		assertEquals("1", criterias.get(1).getValue());
	}

	@Test
	public void buildFilteredResultComUmFiltro() {
		PathBuilder<ProductEntity> pathBuilder = new PathBuilder<ProductEntity>(ProductEntity.class, "productEntity");
		Predicate predicate = new PredicateBuilder<ProductEntity>(pathBuilder).buildFilteredResult("name:Cerveja Skol");
		assertEquals(QProductEntity.productEntity.name.containsIgnoreCase("Cerveja Skol"), predicate);
	}

	@Test
	public void buildFilteredResultComDoisFiltros() {
		PathBuilder<ProductEntity> pathBuilder = new PathBuilder<ProductEntity>(ProductEntity.class, "productEntity");
		Predicate predicate = new PredicateBuilder<ProductEntity>(pathBuilder).buildFilteredResult("name:Cerveja Skol, stock=1");
		assertEquals(QProductEntity.productEntity.name.containsIgnoreCase("Cerveja Skol").and(QProductEntity.productEntity.stock.eq(1L)), predicate);
	}
	
}
