package com.undcon.app.repositories;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

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
import com.undcon.app.dtos.PurchaseItemDto;
import com.undcon.app.model.PurchaseItemProductEntity;
import com.undcon.app.model.PurchaseItemServiceEntity;
import com.undcon.app.model.QPurchaseItemProductEntity;
import com.undcon.app.model.QPurchaseItemServiceEntity;
import com.undcon.app.model.QSaleItemProductEntity;

@Repository
public class PurchaseRepositoryImpl {

	@Autowired
	private EntityManager em;
	
	@Autowired
	private IPurchaseItemProductRepository itemProductRepository;

	@Autowired
	private IPurchaseItemServiceRepository itemServiceRepository;
	
	public Page<PurchaseItemDto> findAllById(Long id, Pageable pageable) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		JPAQuery<PurchaseItemProductEntity> query = jpaQueryFactory.selectFrom(QPurchaseItemProductEntity.purchaseItemProductEntity)
				.where(QPurchaseItemProductEntity.purchaseItemProductEntity.purchase.id.eq(id));
		query.limit(pageable.getPageSize());
		query.offset(pageable.getPageNumber() * pageable.getPageSize());
		Path<Object> fieldPath = Expressions.path(Object.class, QPurchaseItemProductEntity.purchaseItemProductEntity, "id");

		query.orderBy(new OrderSpecifier(Order.ASC, fieldPath));
		List<PurchaseItemProductEntity> itensProduct = query.fetch();
		List<PurchaseItemDto> result = new ArrayList<PurchaseItemDto>();
		for (PurchaseItemProductEntity purchaseItemProductEntity : itensProduct) {
			double subTotalItem = purchaseItemProductEntity.getPrice() * purchaseItemProductEntity.getQuantity();
			result.add(new PurchaseItemDto(purchaseItemProductEntity.getId(), purchaseItemProductEntity.getProduct().getName(),
					purchaseItemProductEntity.getPurchase().getId(), purchaseItemProductEntity.getUser().getLogin(),
					 purchaseItemProductEntity.getPrice(),
					purchaseItemProductEntity.getQuantity(), subTotalItem, ItemType.PRODUCT));
		}
		long countItensProductTotal = itemProductRepository
				.count(QPurchaseItemProductEntity.purchaseItemProductEntity.purchase.id.eq(id));

		if (result.size() < pageable.getPageSize()) {
			jpaQueryFactory = new JPAQueryFactory(em);
			JPAQuery<PurchaseItemServiceEntity> queryService = jpaQueryFactory
					.select(QPurchaseItemServiceEntity.purchaseItemServiceEntity)
					.from(QPurchaseItemServiceEntity.purchaseItemServiceEntity)
					.where(QPurchaseItemServiceEntity.purchaseItemServiceEntity.purchase.id.eq(id));
			if (!result.isEmpty()) {
				queryService.offset(0);
				queryService.limit(pageable.getPageSize() - result.size());
			} else {
				queryService.offset(SaleRepositoryImpl.calcOffSetService(pageable, countItensProductTotal));
				queryService.limit(pageable.getPageSize());
			}
			fieldPath = Expressions.path(Object.class, QPurchaseItemServiceEntity.purchaseItemServiceEntity, "id");
			queryService.orderBy(new OrderSpecifier(Order.ASC, fieldPath));
			List<PurchaseItemServiceEntity> itensService = queryService.fetch();
			for (PurchaseItemServiceEntity purchaseItemServiceEntity : itensService) {
				double subTotalItem = purchaseItemServiceEntity.getPrice() * purchaseItemServiceEntity.getQuantity();
				result.add(new PurchaseItemDto(purchaseItemServiceEntity.getId(), purchaseItemServiceEntity.getService().getName(),
						purchaseItemServiceEntity.getPurchase().getId(), purchaseItemServiceEntity.getUser().getLogin(),
						purchaseItemServiceEntity.getPrice(),
						purchaseItemServiceEntity.getQuantity(), subTotalItem, ItemType.SERVICE));
			}
		}

		long countItensServiceTotal = itemServiceRepository
				.count(QPurchaseItemServiceEntity.purchaseItemServiceEntity.purchase.id.eq(id));
		long total = countItensProductTotal + countItensServiceTotal;
		return new PageImpl<PurchaseItemDto>(result, pageable, total);
	}
	
	public Double getPurchaseTotal(Long id) {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		JPAQuery<PurchaseItemProductEntity> query = jpaQueryFactory.selectFrom(QPurchaseItemProductEntity.purchaseItemProductEntity)
				.where(QPurchaseItemProductEntity.purchaseItemProductEntity.purchase.id.eq(id));
		List<PurchaseItemProductEntity> itensProduct = query.fetch();
		double total = 0;
		for (PurchaseItemProductEntity itemProductEntity : itensProduct) {
			double subTotalItem = itemProductEntity.getPrice() * itemProductEntity.getQuantity();
			total += subTotalItem;
		}

		jpaQueryFactory = new JPAQueryFactory(em);
		JPAQuery<PurchaseItemServiceEntity> queryService = jpaQueryFactory
				.select(QPurchaseItemServiceEntity.purchaseItemServiceEntity).from(QPurchaseItemServiceEntity.purchaseItemServiceEntity)
				.where(QPurchaseItemServiceEntity.purchaseItemServiceEntity.purchase.id.eq(id));
		List<PurchaseItemServiceEntity> itensService = queryService.fetch();
		for (PurchaseItemServiceEntity itemServiceEntity : itensService) {
			double subTotalItem = itemServiceEntity.getPrice() * itemServiceEntity.getQuantity();
			total += subTotalItem;
		}
		return new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}
}
