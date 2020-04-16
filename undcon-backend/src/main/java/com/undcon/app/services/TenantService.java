package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.TenantEntity;
import com.undcon.app.repositories.ITenantRepository;
import com.undcon.app.utils.LongUtils;
import com.undcon.app.utils.PageUtils;

@Component
public class TenantService {

	@Autowired
	private ITenantRepository tenantRepository;

	@Autowired
	private PermissionService permissionService;

	public Page<TenantEntity> getAll(Integer page, Integer size) throws UndconException {
		permissionService.checkPermission(ResourceType.TENANT);
		return tenantRepository.findAll(PageUtils.createPageRequest(page, size));
	}

	public TenantEntity findById(Long id) throws UndconException {
		permissionService.checkPermission(ResourceType.TENANT);
		return tenantRepository.findOne(id);
	}

	public TenantEntity persist(TenantEntity tenant) throws UndconException {
		permissionService.checkPermission(ResourceType.TENANT);
		if (LongUtils.longIsPositiveValue(tenant.getId())) {
			throw new UndconException(UndconError.NEW_REGISTER_INVALID_ID);
		}

		return tenantRepository.save(tenant);
	}

	public TenantEntity update(TenantEntity tenant) throws UndconException {
		permissionService.checkPermission(ResourceType.TENANT);
		return tenantRepository.save(tenant);
	}

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.TENANT);
		tenantRepository.delete(id);
	}

}
