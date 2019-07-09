package com.webarc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.webarc.app.model.Sale;
import com.webarc.app.repository.VendaRepository;

@Service
public class SaleService {

    @Autowired
    private VendaRepository repo;

    public Sale create(Sale reading) {
        return repo.save(reading);
    }

    public Sale getReading(long id) {
        return repo.findOne(id);
    }

    public Page<Sale>  getAll(Integer page, Integer size) {
        return repo.findAll(new PageRequest(page, size));
    }

}
