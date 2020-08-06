package com.undcon.app.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.undcon.app.dtos.ProductSaledInfoDto;
import com.undcon.app.model.QIncomeEntity;
import com.undcon.app.model.SaleEntity;

@Repository
public class SaleIncomeRepositoryImpl {

	@Autowired
	private EntityManager em;

	public List<ProductSaledInfoDto> getTopProductSaled(boolean billed) {

		TypedQuery<ProductSaledInfoDto> query = em.createQuery(
				"SELECT SUM((s.quantity * s.price)) AS totalSaled ) from SaleItemProductEntity s where s.sale.billed in :billed AND s. GROUP BY s.product.id, s.product.name",
				ProductSaledInfoDto.class);
		query.setParameter("billed", billed);
		List<ProductSaledInfoDto> b = query.getResultList();
		return b;
	}

	public Double getIncomeValueBilledBySale(SaleEntity sale, Optional<Boolean> settledFilter) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		JPAQuery<Double> query = jpaQueryFactory.select(QIncomeEntity.incomeEntity.value.sum()) //
				.from(QIncomeEntity.incomeEntity) //
				.where(QIncomeEntity.incomeEntity.sale.eq(sale));
		if (settledFilter.isPresent()) {
			query.where(QIncomeEntity.incomeEntity.settled.eq(settledFilter.get()));
		}
		return query.fetchOne();
	}

}
