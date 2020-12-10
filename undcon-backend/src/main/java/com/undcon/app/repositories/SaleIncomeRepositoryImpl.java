package com.undcon.app.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.undcon.app.enums.PaymentStatus;
import com.undcon.app.model.QIncomeEntity;
import com.undcon.app.model.SaleEntity;

@Repository
public class SaleIncomeRepositoryImpl {

	@Autowired
	private EntityManager em;

	public Double getIncomeValueBilledBySale(SaleEntity sale, List<PaymentStatus> paymentStatusFilter) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		JPAQuery<Double> query = jpaQueryFactory.select(QIncomeEntity.incomeEntity.value.sum()) //
				.from(QIncomeEntity.incomeEntity) //
				.where(QIncomeEntity.incomeEntity.sale.eq(sale));
		if (!paymentStatusFilter.isEmpty()) {
			query.where(QIncomeEntity.incomeEntity.paymentStatus.in(paymentStatusFilter));
		}
		Double value = query.fetchOne();
		if (value == null) {
			value = 0d;
		}
		return value == null ? 0d : value;
	}

}
