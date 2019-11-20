package com.undcon.app.old.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.undcon.app.model.old.Product;
import com.undcon.app.old.repository.ProdutoRepository;

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
