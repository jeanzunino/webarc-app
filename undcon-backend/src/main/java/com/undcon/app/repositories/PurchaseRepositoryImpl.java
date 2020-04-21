package com.undcon.app.repositories;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseRepositoryImpl {

	@Autowired
	private EntityManager em;
	
	public Double getTotalSale(boolean billed){
		Query query = em.createQuery("SELECT SUM((s.quantity * s.price)) AS total from PurchaseItemProductEntity s where s.sale.billed = :billed ", Double.class);
		query.setParameter("billed", billed);
		Double b = (Double) query.getSingleResult();
		return b;
	}
}
