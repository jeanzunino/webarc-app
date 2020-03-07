package com.undcon.app.services;

import java.util.List;

import javax.ws.rs.ForbiddenException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.enums.ResourseType;
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

	public List<TenantEntity> getAll(Integer page, Integer size) {
		permissionService.checkPermission(ResourseType.TENANT);
		return tenantRepository.findAll(PageUtils.createPageRequest(page, size)).getContent();
	}

	public TenantEntity findById(Long id) {
		permissionService.checkPermission(ResourseType.TENANT);
		return tenantRepository.findOne(id);
	}

	public TenantEntity persist(TenantEntity tenant) throws UndconException, ForbiddenException {
		permissionService.checkPermission(ResourseType.TENANT);
		if (LongUtils.longIsPositiveValue(tenant.getId())) {
			throw new UndconException(UndconError.NEW_REGISTER_INVALID_ID);
		}

		return tenantRepository.save(tenant);
	}

	public TenantEntity update(TenantEntity tenant) throws ForbiddenException {
		permissionService.checkPermission(ResourseType.TENANT);
		return tenantRepository.save(tenant);
	}

	public void delete(long id) {
		tenantRepository.delete(id);
	}

}
