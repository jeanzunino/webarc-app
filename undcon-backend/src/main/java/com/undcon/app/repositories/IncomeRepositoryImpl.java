package com.undcon.app.repositories;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.undcon.app.dtos.ValueByInterval;
import com.undcon.app.enums.IntervalType;

@Repository
public class IncomeRepositoryImpl {

	@Autowired
	private EntityManager em;

	public List<ValueByInterval> getTotalByInterval(String startDate, String endDate, IntervalType type) {
		type = type == null ? IntervalType.MONTHLY : type;
		String groupBy =type.getGroupBy();
		TypedQuery<ValueByInterval> query = em.createQuery(
				"SELECT new com.undcon.app.dtos.ValueByInterval(to_char(s.duaDate, :groupBy) as saleDate, SUM((s.value)) AS totalSaled ) from IncomeEntity s where s.sale.saleDate >= :startDate AND s.sale.saleDate <= :endDate GROUP BY 1 ORDER BY 1",
				ValueByInterval.class);
		query.setParameter("startDate", Date.from(Instant.parse(startDate)));
		query.setParameter("endDate", Date.from(Instant.parse(endDate)));
		query.setParameter("groupBy", groupBy);
		return query.getResultList();
	}

}
