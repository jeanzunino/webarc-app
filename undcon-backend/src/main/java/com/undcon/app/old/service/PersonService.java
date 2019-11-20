package com.undcon.app.old.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.undcon.app.model.old.Person;
import com.undcon.app.old.repository.PessoaRepository;

@Service
public class PersonService {

	@Autowired
    private PessoaRepository repo;

    public Person save(Person produto) {
        return repo.save(produto);
    }
    
    public Person getReading(long id) {
        return repo.findOne(id);
    }

    public Page<Person>  getAll(Integer page, Integer size) {
        return repo.findAll(new PageRequest(page, size));
    }
}
