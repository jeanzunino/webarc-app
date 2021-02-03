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
import com.undcon.app.dtos.ItemDashboardInfoDto;
import com.undcon.app.dtos.SaleItemDto;
import com.undcon.app.dtos.ValueByInterval;
import com.undcon.app.enums.IntervalType;
import com.undcon.app.model.QSaleItemProductEntity;
import com.undcon.app.model.QSaleItemServiceEntity;
import com.undcon.app.model.SaleItemProductEntity;
import com.undcon.app.model.SaleItemServiceEntity;

@Repository
public class SaleRepositoryImpl {

	@Autowired
	private EntityManager em;

	@Autowired
	private ISaleItemProductRepository saleItemProductRepository;

	@Autowired
	private ISaleItemServiceRepository saleItemServiceRepository;

	public Double getTotalSale(boolean billed) {
		Query query = em.createQuery(
				"SELECT SUM((s.quantity * s.price)) AS total from SaleItemProductEntity s where s.sale.billed = :billed ",
				Double.class);
		query.setParameter("billed", billed);
		Double b = (Double) query.getSingleResult();
		return b;
	}

	public List<ItemDashboardInfoDto> getTopProductSaled(String startDate, String endDate, int size) {
		String orderBy = " ORDER BY quantity";
		TypedQuery<ItemDashboardInfoDto> query = em.createQuery(
				"SELECT new com.undcon.app.dtos.ItemDashboardInfoDto(s.product.id as productId, s.product.name as productName, SUM((s.quantity )) AS quantity, SUM((s.quantity * s.price)) AS totalSaled ) FROM SaleItemProductEntity s WHERE s.sale.saleDate >= :startDate AND s.sale.saleDate <= :endDate GROUP BY s.product.id, s.product.name "+orderBy,
				ItemDashboardInfoDto.class);
		query.setParameter("startDate", Date.from(Instant.parse(startDate)));
		query.setParameter("endDate", Date.from(Instant.parse(endDate)));
		query.setMaxResults(size);
		List<ItemDashboardInfoDto> b = query.getResultList();
		return b;
	}

	public Page<SaleItemDto> findAllById(Long id, Pageable pageable) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		JPAQuery<SaleItemProductEntity> query = jpaQueryFactory.selectFrom(QSaleItemProductEntity.saleItemProductEntity)
				.where(QSaleItemProductEntity.saleItemProductEntity.sale.id.eq(id));
		query.limit(pageable.getPageSize());
		query.offset(pageable.getPageNumber() * pageable.getPageSize());
		Path<Object> fieldPath = Expressions.path(Object.class, QSaleItemProductEntity.saleItemProductEntity, "id");

		query.orderBy(new OrderSpecifier(Order.ASC, fieldPath));
		List<SaleItemProductEntity> itensProduct = query.fetch();
		List<SaleItemDto> result = new ArrayList<SaleItemDto>();
		for (SaleItemProductEntity saleItemProductEntity : itensProduct) {
			double subTotalItem = saleItemProductEntity.getPrice() * saleItemProductEntity.getQuantity();
			result.add(new SaleItemDto(saleItemProductEntity.getId(), saleItemProductEntity.getProduct().getName(),
					saleItemProductEntity.getSale().getId(), saleItemProductEntity.getUser().getLogin(),
					saleItemProductEntity.getSalesman().getName(), saleItemProductEntity.getPrice(),
					saleItemProductEntity.getQuantity(), subTotalItem, ItemType.PRODUCT));
		}
		long countItensProductTotal = saleItemProductRepository
				.count(QSaleItemProductEntity.saleItemProductEntity.sale.id.eq(id));

		if (result.size() < pageable.getPageSize()) {
			jpaQueryFactory = new JPAQueryFactory(em);
			JPAQuery<SaleItemServiceEntity> queryService = jpaQueryFactory
					.select(QSaleItemServiceEntity.saleItemServiceEntity)
					.from(QSaleItemServiceEntity.saleItemServiceEntity)
					.where(QSaleItemServiceEntity.saleItemServiceEntity.sale.id.eq(id));
			if (!result.isEmpty()) {
				queryService.offset(0);
				queryService.limit(pageable.getPageSize() - result.size());
			} else {
				queryService.offset(calcOffSetService(pageable, countItensProductTotal));
				queryService.limit(pageable.getPageSize());
			}
			fieldPath = Expressions.path(Object.class, QSaleItemServiceEntity.saleItemServiceEntity, "id");
			queryService.orderBy(new OrderSpecifier(Order.ASC, fieldPath));
			List<SaleItemServiceEntity> itensService = queryService.fetch();
			for (SaleItemServiceEntity saleItemServiceEntity : itensService) {
				double subTotalItem = saleItemServiceEntity.getPrice() * saleItemServiceEntity.getQuantity();
				result.add(new SaleItemDto(saleItemServiceEntity.getId(), saleItemServiceEntity.getService().getName(),
						saleItemServiceEntity.getSale().getId(), saleItemServiceEntity.getUser().getLogin(),
						saleItemServiceEntity.getSalesman().getName(), saleItemServiceEntity.getPrice(),
						saleItemServiceEntity.getQuantity(), subTotalItem, ItemType.SERVICE));
			}
		}

		long countItensServiceTotal = saleItemServiceRepository
				.count(QSaleItemServiceEntity.saleItemServiceEntity.sale.id.eq(id));
		long total = countItensProductTotal + countItensServiceTotal;
		return new PageImpl<SaleItemDto>(result, pageable, total);
	}

	public Double getSaleTotal(Long id) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		JPAQuery<SaleItemProductEntity> query = jpaQueryFactory.selectFrom(QSaleItemProductEntity.saleItemProductEntity)
				.where(QSaleItemProductEntity.saleItemProductEntity.sale.id.eq(id));
		List<SaleItemProductEntity> itensProduct = query.fetch();
		double total = 0;
		for (SaleItemProductEntity saleItemProductEntity : itensProduct) {
			double subTotalItem = saleItemProductEntity.getPrice() * saleItemProductEntity.getQuantity();
			total += subTotalItem;
		}

		jpaQueryFactory = new JPAQueryFactory(em);
		JPAQuery<SaleItemServiceEntity> queryService = jpaQueryFactory
				.select(QSaleItemServiceEntity.saleItemServiceEntity).from(QSaleItemServiceEntity.saleItemServiceEntity)
				.where(QSaleItemServiceEntity.saleItemServiceEntity.sale.id.eq(id));
		List<SaleItemServiceEntity> itensService = queryService.fetch();
		for (SaleItemServiceEntity saleItemServiceEntity : itensService) {
			double subTotalItem = saleItemServiceEntity.getPrice() * saleItemServiceEntity.getQuantity();
			total += subTotalItem;
		}
		return new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}
	
	public Double getSaleTotalByPdv(Long pdvId) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		JPAQuery<SaleItemProductEntity> query = jpaQueryFactory.selectFrom(QSaleItemProductEntity.saleItemProductEntity)
				.where(QSaleItemProductEntity.saleItemProductEntity.sale.pdv.id.eq(pdvId));
		List<SaleItemProductEntity> itensProduct = query.fetch();
		double total = 0;
		for (SaleItemProductEntity saleItemProductEntity : itensProduct) {
			double subTotalItem = saleItemProductEntity.getPrice() * saleItemProductEntity.getQuantity();
			total += subTotalItem;
		}

		jpaQueryFactory = new JPAQueryFactory(em);
		JPAQuery<SaleItemServiceEntity> queryService = jpaQueryFactory
				.select(QSaleItemServiceEntity.saleItemServiceEntity).from(QSaleItemServiceEntity.saleItemServiceEntity)
				.where(QSaleItemServiceEntity.saleItemServiceEntity.sale.pdv.id.eq(pdvId));
		List<SaleItemServiceEntity> itensService = queryService.fetch();
		for (SaleItemServiceEntity saleItemServiceEntity : itensService) {
			double subTotalItem = saleItemServiceEntity.getPrice() * saleItemServiceEntity.getQuantity();
			total += subTotalItem;
		}
		return new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}

	public static long calcOffSetService(Pageable pageable, long countItensProductTotal) {
		if (pageable.getPageNumber() == 0) {
			return 0;
		}
		long count = (pageable.getPageNumber() * pageable.getPageSize())
				- (countItensProductTotal % pageable.getPageSize()) - pageable.getPageSize();
		if (count < 0) {
			return 0;
		}
		return count;
	}

	public List<ValueByInterval> getTotalSaledProductByInterval(String startDate, String endDate, IntervalType type) {
		type = type == null ? IntervalType.MONTHLY : type;
		String groupBy =type.getGroupBy();
		TypedQuery<ValueByInterval> query = em.createQuery(
				"SELECT new com.undcon.app.dtos.ValueByInterval(to_char(s.sale.saleDate, :groupBy) as saleDate, SUM((s.quantity * s.price)) AS totalSaled ) from SaleItemProductEntity s where s.sale.saleDate >= :startDate AND s.sale.saleDate <= :endDate GROUP BY 1 ORDER BY 1",
				ValueByInterval.class);
		query.setParameter("startDate", Date.from(Instant.parse(startDate)));
		query.setParameter("endDate", Date.from(Instant.parse(endDate)));
		query.setParameter("groupBy", groupBy);
		return query.getResultList();
	}
	
	public List<ValueByInterval> getTotalSaledServiceByInterval(String startDate, String endDate, IntervalType type) {
		type = type == null ? IntervalType.MONTHLY : type;
		String groupBy =type.getGroupBy();
		TypedQuery<ValueByInterval> query = em.createQuery(
				"SELECT new com.undcon.app.dtos.ValueByInterval(to_char(s.sale.saleDate, :groupBy) as saleDate, SUM((s.quantity * s.price)) AS totalSaled ) from SaleItemServiceEntity s where s.sale.saleDate >= :startDate AND s.sale.saleDate <= :endDate GROUP BY 1 ORDER BY 1",
				ValueByInterval.class);
		query.setParameter("startDate", Date.from(Instant.parse(startDate)));
		query.setParameter("endDate", Date.from(Instant.parse(endDate)));
		query.setParameter("groupBy", groupBy);
		return query.getResultList();
	}

}
