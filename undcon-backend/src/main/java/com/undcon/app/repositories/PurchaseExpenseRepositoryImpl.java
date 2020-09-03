package com.undcon.app.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.undcon.app.enums.PaymentStatus;
import com.undcon.app.model.PurchaseEntity;
import com.undcon.app.model.QExpenseEntity;

@Repository
public class PurchaseExpenseRepositoryImpl {

	@Autowired
	private EntityManager em;

	public Double getExpenseValueBilledByPurchase(PurchaseEntity purchase, List<PaymentStatus> paymentStatusFilter) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		JPAQuery<Double> query = jpaQueryFactory.select(QExpenseEntity.expenseEntity.value.sum()) //
				.from(QExpenseEntity.expenseEntity) //
				.where(QExpenseEntity.expenseEntity.purchase.eq(purchase));
		if (!paymentStatusFilter.isEmpty()) {
			query.where(QExpenseEntity.expenseEntity.paymentStatus.in(paymentStatusFilter));
		}
		Double value = query.fetchOne();
		if (value == null) {
			value = 0d;
		}
		return value == null ? 0d : value;
	}

}
