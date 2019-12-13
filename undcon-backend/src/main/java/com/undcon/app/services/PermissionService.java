package com.undcon.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.enums.ResourseType;
import com.undcon.app.model.PermissionEntity;
import com.undcon.app.model.PermissionItenEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.multitenancy.ThreadLocalStorage;
import com.undcon.app.repositories.IPermissionItenRepository;
import com.undcon.app.repositories.IPermissionRepository;
import com.undcon.app.repositories.IUserRepository;

@Component
public class PermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private IPermissionItenRepository permissionItenRepository;

    public List<PermissionEntity> getAll() {
        return permissionRepository.findAll();
    }
    
    public PermissionEntity persist(PermissionEntity entity) {
        validateName(0L, entity.getName());
        return permissionRepository.save(entity);
    }

    public PermissionEntity update(PermissionEntity entity) {
        validateName(entity.getId(), entity.getName());
        return permissionRepository.save(entity);
    }

    private void validateName(Long id, String name) {
        List<PermissionEntity> findByIdNotAndName = permissionRepository.findByIdNotAndName(id, name);
        if (!findByIdNotAndName.isEmpty()) {
            throw new IllegalArgumentException("Nome já está em uso");
        }
    }

    public void checkPermission(ResourseType configuration) throws IllegalAccessException {
        Long currentUserId = ThreadLocalStorage.getUser();
        UserEntity find = userRepository.findOne(currentUserId);
        PermissionEntity permission = find.getPermission();
        verifyPermissionHasResource(permission, configuration);
    }

    private void verifyPermissionHasResource(PermissionEntity permission, ResourseType configuration) throws IllegalAccessException {
        List<PermissionItenEntity> itens = permissionItenRepository.findByPermission(permission);
        boolean hasResource = itens.stream().filter(iten -> iten.getResourceType() == configuration).count() > 0;
        if (!hasResource) {
            throw new IllegalAccessException("Usuário não possui permissão para o recurso " + configuration);
        }
    }
}
