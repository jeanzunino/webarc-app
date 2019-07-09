package com.webarc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.webarc.app.model.User;
import com.webarc.app.repository.UsuarioRepository;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository repo;

    public User save(User reading) {
        return repo.save(reading);
    }
    
    public User update(User reading) {
        return repo.save(reading);
    }

    public User getReading(long id) {
        return repo.findOne(id);
    }

    public Page<User> getAllFromEmail(String email, Integer page, Integer size) {
        return repo.findAllByEmail(email, new PageRequest(page, size));
    }
    
    public Page<User>  getAll(Integer page, Integer size) {
        return repo.findAll(new PageRequest(page, size));
    }

}
