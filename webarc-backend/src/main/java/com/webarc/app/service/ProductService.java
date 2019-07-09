package com.webarc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.webarc.app.model.Product;
import com.webarc.app.repository.ProdutoRepository;

@Service
public class ProductService {

    @Autowired
    private ProdutoRepository repo;

    public Product save(Product produto) {
        return repo.save(produto);
    }
    
    public Product getReading(long id) {
        return repo.findOne(id);
    }

    public Page<Product>  getAll(Integer page, Integer size) {
        return repo.findAll(new PageRequest(page, size));
    }

}
