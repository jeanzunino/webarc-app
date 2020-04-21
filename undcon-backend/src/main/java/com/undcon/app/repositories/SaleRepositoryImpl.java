package com.undcon.app.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.undcon.app.dtos.ProductSaledInfoDto;

@Repository
public class SaleRepositoryImpl {

	@Autowired
	private EntityManager em;
	
	public Double getTotalSale(boolean billed){
		Query query = em.createQuery("SELECT SUM((s.quantity * s.price)) AS total from SaleItemProductEntity s where s.sale.billed = :billed ", Double.class);
		query.setParameter("billed", billed);
		Double b = (Double) query.getSingleResult();
		return b;
	}
	
	public List<ProductSaledInfoDto> getTopProductSaled(boolean billed){
		TypedQuery<ProductSaledInfoDto>  query = em.createQuery("SELECT new com.undcon.app.dtos.ProductSaledInfoDto(s.product.id as productId, s.product.name as productName, SUM((s.quantity )) AS quantity, SUM((s.quantity * s.price)) AS totalSaled ) from SaleItemProductEntity s where s.sale.billed = :billed GROUP BY s.product.id, s.product.name", ProductSaledInfoDto.class);
		query.setParameter("billed", billed);
		List<ProductSaledInfoDto> b = query.getResultList();
		return b;
	}
}
