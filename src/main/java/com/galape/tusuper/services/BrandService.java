package com.galape.tusuper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galape.tusuper.exceptions.MiException;
import com.galape.tusuper.repositories.BrandRepository;
import jakarta.transaction.Transactional;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Transactional
    public void create(String name){
    }

    @Transactional
    public void modify(Integer id, String name){

    }

    public void delete(){

    }

    private void validate(String name) throws MiException{
        if (name == null) {
            throw new MiException("El nombre de la marca no puede ser nulo");
        }
        if (name.isEmpty()) {
            throw new MiException("El nombre de la marca no puede estar vacio");
        }
    }
}
