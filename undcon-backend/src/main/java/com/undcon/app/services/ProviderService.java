package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.ProviderEntity;
import com.undcon.app.repositories.IProviderRepository;

@Component
public class ProviderService extends AbstractService<ProviderEntity> {

	@Autowired
	private IProviderRepository providerRepository;

	public Page<ProviderEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(ProviderEntity.class, filter, page, size);
	}

	@Override
	protected void validateBeforePost(ProviderEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		validateName(0L, entity.getName());
	}

	@Override
	protected void validateBeforeUpdate(ProviderEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
		validateName(entity.getId(), entity.getName());
	}

	@Override
	protected void validateBeforeDelete(ProviderEntity entity) throws UndconException {
		super.validateBeforeDelete(entity);
	}

	private void validateName(Long id, String name) throws UndconException {
		List<ProviderEntity> findByIdNotAndName = providerRepository.findByIdNotAndNameIgnoreCase(id, name);
		if (!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	@Override
	protected JpaRepository<ProviderEntity, Long> getRepository() {
		return providerRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.PROVIDER;
	}
}
