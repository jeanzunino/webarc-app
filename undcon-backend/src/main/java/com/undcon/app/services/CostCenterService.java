package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.CostCenterEntity;
import com.undcon.app.repositories.ICostCenterRepository;

@Component
public class CostCenterService extends AbstractService<CostCenterEntity> {

	@Autowired
	private ICostCenterRepository repo;
	
	public Page<CostCenterEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(CostCenterEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(CostCenterEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		validateName(0L, entity.getName());
	}

	@Override
	protected void validateBeforeUpdate(CostCenterEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
		validateName(entity.getId(), entity.getName());
	}

	@Override
	protected void validateBeforeDelete(CostCenterEntity entity) throws UndconException {
		super.validateBeforeDelete(entity);
	}
	
	private void validateName(Long id, String name) throws UndconException {
		List<CostCenterEntity> findByIdNotAndName = repo.findByIdNotAndName(id, name);
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	@Override
	protected JpaRepository<CostCenterEntity, Long> getRepository() {
		return repo;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.COST_CENTER;
	}

}
