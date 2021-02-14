package com.undcon.app.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.undcon.app.model.ProductEntity;

@Repository
public class ProductRepositoryImpl{

	@Autowired
	private EntityManager em;
	
	public List<ProductEntity> getStockMin(){
		Query query = em.createQuery("SELECT p FROM ProductEntity p WHERE p.stock < p.stockMin AND p.stockMin > 0", ProductEntity.class);
		query.setMaxResults(10);
		List<ProductEntity> c = query.getResultList();
		return c;
	}
}
