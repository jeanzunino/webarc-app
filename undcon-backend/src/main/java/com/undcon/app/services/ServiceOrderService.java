package com.undcon.app.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.enums.PaymentStatus;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.ServiceOrderStatus;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ServiceOrderEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.IServiceOrderRepository;

@Component
public class ServiceOrderService extends AbstractService<ServiceOrderEntity> {

	@Autowired
	private IServiceOrderRepository repository;
	
	@Autowired
	private UserService userService;

	public Page<ServiceOrderEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(ServiceOrderEntity.class, filter, page, size);
	}
	
	@Override
	protected void validateBeforePost(ServiceOrderEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public ServiceOrderEntity persist(ServiceOrderEntity entity) throws UndconException {
		UserEntity user = userService.getCurrentUser();
		entity.setUser(user);
		
		Date currentDate = new Date(System.currentTimeMillis());
		entity.setRegisterDate(currentDate);
		
		entity.setPaymentStatus(PaymentStatus.PENDING);
		entity.setStatus(ServiceOrderStatus.OPENED);
		if(entity.getTechnicalReport() == null) {
			entity.setTechnicalReport("");
		}
		if(entity.getObservations() == null) {
			entity.setObservations("");
		}
		return super.persist(entity);
	}
	
	@Override
	protected void validateBeforeUpdate(ServiceOrderEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
	}

	@Override
	protected JpaRepository<ServiceOrderEntity, Long> getRepository() {
		return repository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.SERVICE_ORDER;
	}
}
