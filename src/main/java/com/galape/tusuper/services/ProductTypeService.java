package com.galape.tusuper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galape.tusuper.repositories.ProductTypeRepository;
import com.galape.tusuper.repositories.SubCategoryRepository;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

}
