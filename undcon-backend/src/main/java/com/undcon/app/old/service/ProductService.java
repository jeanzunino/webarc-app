package com.undcon.app.old.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.undcon.app.model.ProductEntity;
import com.undcon.app.old.repository.ProdutoRepository;

@Service
public class ProductService {

    @Autowired
    private ProdutoRepository repo;

    public ProductEntity save(ProductEntity produto) {
        return repo.save(produto);
    }
    
    public ProductEntity getReading(long id) {
        return repo.findOne(id);
    }

    public Page<ProductEntity>  getAll(Integer page, Integer size) {
        return repo.findAll(new PageRequest(page, size));
    }

}
