package com.undcon.app.filtering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.undcon.app.enums.PaymentType;
import com.undcon.app.enums.BillingStatus;
import com.undcon.app.model.ExpenseEntity;
import com.undcon.app.model.IncomeEntity;
import com.undcon.app.model.MenuTemplateItemEntity;
import com.undcon.app.model.ProductEntity;
import com.undcon.app.model.QIncomeEntity;
import com.undcon.app.model.QMenuTemplateItemEntity;
import com.undcon.app.model.QProductEntity;
import com.undcon.app.model.QSaleEntity;
import com.undcon.app.model.SaleEntity;

public class PredicateBuilderTest {

	@Test
	public void getCriteriasSemEspaco() {
		List<SearchCriteria> criterias = PredicateBuilder.getCriterias("name:Cerveja");
		assertEquals(1, criterias.size());
		assertEquals("name", criterias.get(0).getKey());
		assertEquals(":", criterias.get(0).getOperation());
		assertEquals("Cerveja", criterias.get(0).getValue());
	}

	@Test
	public void getCriteriasComEspacoMeio() {
		List<SearchCriteria> criterias = PredicateBuilder.getCriterias("name:Cerveja Skol");
		assertEquals(1, criterias.size());
		assertEquals("name", criterias.get(0).getKey());
		assertEquals(":", criterias.get(0).getOperation());
		assertEquals("Cerveja Skol", criterias.get(0).getValue());
	}

	@Test
	public void getCriteriasComEspacoFim() {
		List<SearchCriteria> criterias = PredicateBuilder.getCriterias("name:Cerveja Skol   ");
		assertEquals(1, criterias.size());
		assertEquals("name", criterias.get(0).getKey());
		assertEquals(":", criterias.get(0).getOperation());
		assertEquals("Cerveja Skol", criterias.get(0).getValue());
	}

	@Test
	public void getCriteriasComDoisFiltrosTest() {
		List<SearchCriteria> criterias = PredicateBuilder.getCriterias("name:Cerveja Skol, status=1");
		assertEquals(2, criterias.size());
		assertEquals("name", criterias.get(0).getKey());
		assertEquals(":", criterias.get(0).getOperation());
		assertEquals("Cerveja Skol", criterias.get(0).getValue());

		assertEquals("status", criterias.get(1).getKey());
		assertEquals("=", criterias.get(1).getOperation());
		assertEquals("1", criterias.get(1).getValue());
	}
	
	@Ignore("Ainda não finalizado a parte de filtro com datas")
	@Test
	public void getCriteriasComDataTest() {
		List<SearchCriteria> criterias = PredicateBuilder.getCriterias("saleDate>2020-04-18");
		assertEquals(1, criterias.size());
		assertEquals("saleDate", criterias.get(0).getKey());
		assertEquals(">", criterias.get(0).getOperation());
		assertEquals("2020-04-18", criterias.get(0).getValue());
	}
	
	@Test
	public void getCriteriasParamNameComPonto() {
		List<SearchCriteria> criterias = PredicateBuilder.getCriterias("sale.id=1");
		assertEquals(1, criterias.size());
		assertEquals("sale.id", criterias.get(0).getKey());
		assertEquals("=", criterias.get(0).getOperation());
		assertEquals("1", criterias.get(0).getValue());
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
	
	@Test
	public void buildFilteredResultComIgualEnum() {
		PathBuilder<SaleEntity> pathBuilder = new PathBuilder<SaleEntity>(SaleEntity.class, "saleEntity");
		Predicate predicate = new PredicateBuilder<SaleEntity>(pathBuilder).buildFilteredResult("status=TO_BILL");
		assertEquals(QSaleEntity.saleEntity.status.eq(BillingStatus.TO_BILL), predicate);
	}
	
	@Test
	public void buildFilteredResultComDiferenteEnum() {
		PathBuilder<SaleEntity> pathBuilder = new PathBuilder<SaleEntity>(SaleEntity.class, "saleEntity");
		Predicate predicate = new PredicateBuilder<SaleEntity>(pathBuilder).buildFilteredResult("status!=TO_BILL");
		assertEquals(QSaleEntity.saleEntity.status.ne(BillingStatus.TO_BILL), predicate);
	}
	
	@Test
	public void buildFilteredResultComMaiorDoubleNative() {
		PathBuilder<ProductEntity> pathBuilder = new PathBuilder<ProductEntity>(ProductEntity.class, "productEntity");
		Predicate predicate = new PredicateBuilder<ProductEntity>(pathBuilder).buildFilteredResult("purchasePrice>10");
		assertEquals(QProductEntity.productEntity.purchasePrice.gt(Double.valueOf("10")), predicate);
	}
	
	@Test
	public void buildFilteredResultComMaiorLongNative() {
		PathBuilder<ProductEntity> pathBuilder = new PathBuilder<ProductEntity>(ProductEntity.class, "productEntity");
		Predicate predicate = new PredicateBuilder<ProductEntity>(pathBuilder).buildFilteredResult("stock>10");
		assertEquals(QProductEntity.productEntity.stock.gt(Double.valueOf("10")), predicate);
	}
	
	@Test
	public void buildFilteredResultComMaiorIntNative() {
		PathBuilder<MenuTemplateItemEntity> pathBuilder = new PathBuilder<MenuTemplateItemEntity>(MenuTemplateItemEntity.class, "menuTemplateItemEntity");
		Predicate predicate = new PredicateBuilder<MenuTemplateItemEntity>(pathBuilder).buildFilteredResult("order>11");
		assertEquals(QMenuTemplateItemEntity.menuTemplateItemEntity.order.gt(11), predicate);
	}
	
	@Test
	public void buildFilteredResultComMaiorOuIgualDoubleNative() {
		PathBuilder<ProductEntity> pathBuilder = new PathBuilder<ProductEntity>(ProductEntity.class, "productEntity");
		Predicate predicate = new PredicateBuilder<ProductEntity>(pathBuilder).buildFilteredResult("purchasePrice>=10");
		assertEquals(QProductEntity.productEntity.purchasePrice.gt(Double.valueOf("10")).or(QProductEntity.productEntity.purchasePrice.eq(Double.valueOf("10"))), predicate);
	}
	
	@Test
	public void buildFilteredResultComMaiorOuIgualLongNative() {
		PathBuilder<ProductEntity> pathBuilder = new PathBuilder<ProductEntity>(ProductEntity.class, "productEntity");
		Predicate predicate = new PredicateBuilder<ProductEntity>(pathBuilder).buildFilteredResult("stock>=10");
		assertEquals(QProductEntity.productEntity.stock.gt(Long.valueOf("10")).or(QProductEntity.productEntity.stock.eq(10L)), predicate);
	}
	
	@Test
	public void buildFilteredResultComMaiorOuIgualIntNative() {
		PathBuilder<MenuTemplateItemEntity> pathBuilder = new PathBuilder<MenuTemplateItemEntity>(MenuTemplateItemEntity.class, "menuTemplateItemEntity");
		Predicate predicate = new PredicateBuilder<MenuTemplateItemEntity>(pathBuilder).buildFilteredResult("order>=11");
		assertEquals(QMenuTemplateItemEntity.menuTemplateItemEntity.order.gt(11).or(QMenuTemplateItemEntity.menuTemplateItemEntity.order.eq(11)), predicate);
	}
	
	@Test
	public void buildFilteredResultComMenorOuIgualDoubleNative() {
		PathBuilder<ProductEntity> pathBuilder = new PathBuilder<ProductEntity>(ProductEntity.class, "productEntity");
		Predicate predicate = new PredicateBuilder<ProductEntity>(pathBuilder).buildFilteredResult("purchasePrice<=10");
		assertEquals(QProductEntity.productEntity.purchasePrice.lt(Double.valueOf("10")).or(QProductEntity.productEntity.purchasePrice.eq(Double.valueOf("10"))), predicate);
	}
	
	@Test
	public void buildFilteredResultComMenorOuIgualLongNative() {
		PathBuilder<ProductEntity> pathBuilder = new PathBuilder<ProductEntity>(ProductEntity.class, "productEntity");
		Predicate predicate = new PredicateBuilder<ProductEntity>(pathBuilder).buildFilteredResult("stock<=10");
		assertEquals(QProductEntity.productEntity.stock.lt(Long.valueOf("10")).or(QProductEntity.productEntity.stock.eq(10L)), predicate);
	}
	
	@Test
	public void buildFilteredResultWithParamNameComPonto() {
		PathBuilder<IncomeEntity> pathBuilder = new PathBuilder<IncomeEntity>(IncomeEntity.class, "incomeEntity");
		Predicate predicate = new PredicateBuilder<IncomeEntity>(pathBuilder).buildFilteredResult("sale.id=4&paymentType=BANK_CHECK");
		assertEquals(QIncomeEntity.incomeEntity.sale.id.eq(4L).and(QIncomeEntity.incomeEntity.paymentType.eq(PaymentType.BANK_CHECK)), predicate);
	}
	
	@Test
	public void buildFilteredResultComMenorOuIgualIntNative() {
		PathBuilder<MenuTemplateItemEntity> pathBuilder = new PathBuilder<MenuTemplateItemEntity>(MenuTemplateItemEntity.class, "menuTemplateItemEntity");
		Predicate predicate = new PredicateBuilder<MenuTemplateItemEntity>(pathBuilder).buildFilteredResult("order<=11");
		assertEquals(QMenuTemplateItemEntity.menuTemplateItemEntity.order.lt(11).or(QMenuTemplateItemEntity.menuTemplateItemEntity.order.eq(11)), predicate);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void buildFilteredResultComMaiorDate() {
		PathBuilder<ExpenseEntity> pathBuilder = new PathBuilder<ExpenseEntity>(ExpenseEntity.class, "expenseEntity");
		new PredicateBuilder<ExpenseEntity>(pathBuilder).buildFilteredResult("duaDate>\"2020-07-12T08:50Z\"");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void buildFilteredResultComMenorDate() {
		PathBuilder<ExpenseEntity> pathBuilder = new PathBuilder<ExpenseEntity>(ExpenseEntity.class, "expenseEntity");
		new PredicateBuilder<ExpenseEntity>(pathBuilder).buildFilteredResult("duaDate<\"2020-07-12T08:50Z\"");
	}
}
