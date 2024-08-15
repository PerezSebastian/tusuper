package com.galape.tusuper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galape.tusuper.exceptions.MiException;
import com.galape.tusuper.repositories.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void create(String name, Integer productTypeId) throws MiException{

    }
}
