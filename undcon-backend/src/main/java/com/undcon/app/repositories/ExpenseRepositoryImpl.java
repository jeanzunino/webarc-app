package com.undcon.app.repositories;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.undcon.app.dtos.ItemType;
import com.undcon.app.dtos.ProductSaledInfoDto;
import com.undcon.app.dtos.SaleItemDto;
import com.undcon.app.dtos.ValueByInterval;
import com.undcon.app.enums.IntervalType;
import com.undcon.app.model.QSaleItemProductEntity;
import com.undcon.app.model.QSaleItemServiceEntity;
import com.undcon.app.model.SaleItemProductEntity;
import com.undcon.app.model.SaleItemServiceEntity;

@Repository
public class ExpenseRepositoryImpl {

	@Autowired
	private EntityManager em;

	public List<ValueByInterval> getTotalByInterval(String startDate, String endDate, IntervalType type) {
		type = type == null ? IntervalType.MONTHLY : type;
		String groupBy =type.getGroupBy();
		TypedQuery<ValueByInterval> query = em.createQuery(
				"SELECT new com.undcon.app.dtos.ValueByInterval(to_char(s.duaDate, :groupBy) as saleDate, SUM((s.value)) AS totalSaled ) from ExpenseEntity s where s.sale.saleDate >= :startDate AND s.sale.saleDate <= :endDate GROUP BY 1 ORDER BY 1",
				ValueByInterval.class);
		query.setParameter("startDate", Date.from(Instant.parse(startDate)));
		query.setParameter("endDate", Date.from(Instant.parse(endDate)));
		query.setParameter("groupBy", groupBy);
		return query.getResultList();
	}

}
