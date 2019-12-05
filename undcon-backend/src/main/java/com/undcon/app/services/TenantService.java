package com.undcon.app.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.undcon.app.model.TenantEntity;
import com.undcon.app.repositories.ITenantRepository;

@Component
public class TenantService  implements ApplicationContextAware, InitializingBean{

	private ITenantRepository tenantRepository;

	private ApplicationContext context;
	
	@Autowired
	public void setTenantRepository(ITenantRepository tenantRepository) {
		this.tenantRepository = tenantRepository;
	}

	public List<TenantEntity> getAll() {
		return StreamSupport.stream(tenantRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		tenantRepository = context.getBean(ITenantRepository.class);
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
}
