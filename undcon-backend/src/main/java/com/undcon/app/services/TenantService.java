package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.TenantEntity;
import com.undcon.app.repositories.ITenantRepository;
import com.undcon.app.utils.NumberUtils;

@Component
public class TenantService extends AbstractService<TenantEntity> {

	@Autowired
	private ITenantRepository tenantRepository;

	@Autowired
	private PermissionService permissionService;

	public Page<TenantEntity> getAll(String filter, Integer page, Integer size) throws UndconException {
		permissionService.checkPermission(getResourceType());
		return super.getAll(TenantEntity.class, filter, page, size);
	}

	public TenantEntity findById(Long id) throws UndconException {
		permissionService.checkPermission(ResourceType.TENANT);
		return tenantRepository.findOne(id);
	}

	@Override
	protected void validateBeforePost(TenantEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		if (NumberUtils.longIsPositiveValue(entity.getId())) {
			throw new UndconException(UndconError.NEW_REGISTER_INVALID_ID);
		}
	}

	@Override
	protected JpaRepository<TenantEntity, Long> getRepository() {
		return tenantRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.TENANT;
	}

}
