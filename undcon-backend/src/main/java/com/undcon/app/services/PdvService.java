package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.PdvEntity;
import com.undcon.app.model.ProviderEntity;
import com.undcon.app.repositories.IPdvRepository;
import com.undcon.app.repositories.IProviderRepository;
import com.undcon.app.utils.PageUtils;

@Component
public class PdvService {

	@Autowired
	private IPdvRepository repository;
	
	@Autowired
	private PermissionService permissionService;

	public Page<PdvEntity> getAll(String name, Integer page, Integer size) {
		return repository.findAll(PageUtils.createPageRequest(page, size));
    }
	
	public PdvEntity findById(Long id) {
        return repository.findOne(id);
    }
	
	public PdvEntity persist(PdvEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.PROVIDER);
		return repository.save(entity);
	}

	public PdvEntity update(PdvEntity entity) throws UndconException {
		permissionService.checkPermission(ResourceType.PROVIDER);
		return repository.save(entity);
	}

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(ResourceType.PROVIDER);
		repository.delete(id);
	}
}
