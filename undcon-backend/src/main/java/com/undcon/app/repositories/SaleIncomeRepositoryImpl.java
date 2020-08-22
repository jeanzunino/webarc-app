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
import com.undcon.app.enums.PaymentStatus;
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

	public Double getIncomeValueBilledBySale(SaleEntity sale, List<PaymentStatus> paymentStatusFilter) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		JPAQuery<Double> query = jpaQueryFactory.select(QIncomeEntity.incomeEntity.value.sum()) //
				.from(QIncomeEntity.incomeEntity) //
				.where(QIncomeEntity.incomeEntity.sale.eq(sale));
		if (!paymentStatusFilter.isEmpty()) {
			query.where(QIncomeEntity.incomeEntity.paymentStatus.in(paymentStatusFilter));
		}
		Double value = query.fetchOne();
		return value == null ? 0d : value;
	}

}
