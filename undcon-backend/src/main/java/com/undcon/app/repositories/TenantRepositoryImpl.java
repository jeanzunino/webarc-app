package com.undcon.app.repositories;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.undcon.app.multitenancy.DataSourceProperties;
import com.undcon.app.multitenancy.ThreadLocalStorage;

@Repository
public class TenantRepositoryImpl {

	@Autowired
	private DataSourceProperties dataSourceProperties;

	@Autowired
	private EntityManager em;

	public List<String> getAll() {
		ThreadLocalStorage.setTenantName("public");
		Query query = em.createNativeQuery("SELECT schema_name FROM tenant");
		return query.getResultList();
	}

	public boolean tenantExists(String tenant) {

		EntityManager createEntityManager = new EntityManagerFactoryImpl("public", null, null, null, null)
				.createEntityManager();
		Query query = createEntityManager
				.createNativeQuery("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ?");
		query.setParameter(1, tenant);
		BigInteger count = (BigInteger) query.getSingleResult();
		return count.longValue() > 0;
	}
}
