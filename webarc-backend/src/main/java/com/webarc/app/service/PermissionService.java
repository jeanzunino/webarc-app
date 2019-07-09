package com.webarc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.webarc.app.model.Permission;
import com.webarc.app.repository.PermissaoRepository;

@Service
public class PermissionService {

    @Autowired
    private PermissaoRepository repo;

    public Permission save(Permission reading) {
        return repo.save(reading);
    }
    
    public Permission getReading(long id) {
        return repo.findOne(id);
    }

    public Page<Permission>  getAll(Integer page, Integer size) {
        return repo.findAll(new PageRequest(page, size));
    }

}
