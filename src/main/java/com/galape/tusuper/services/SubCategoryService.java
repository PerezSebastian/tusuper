package com.galape.tusuper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galape.tusuper.repositories.SubCategoryRepository;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    
}
