package com.galape.tusuper.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galape.tusuper.entities.ProductType;
import com.galape.tusuper.entities.SubCategory;
import com.galape.tusuper.exceptions.MiException;
import com.galape.tusuper.repositories.ProductTypeRepository;
import com.galape.tusuper.repositories.SubCategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Transactional
    public void create(String name, Integer subCategoryId) throws MiException {
        validate(name, "tipo de producto");
        validateId(subCategoryId, "la sub-categoría");
        Optional<SubCategory> respSubCategory = subCategoryRepository.findById(subCategoryId);
        if (!respSubCategory.isPresent()) {
            throw new MiException("La sub-categoría ingresada no se encuentra en la base de datos");
        }
        Optional<ProductType> respProductType = productTypeRepository.findByName(name);
        if (respProductType.isPresent()) {
            throw new MiException("El tipo de producto ingresado ya se encuentra en la base de datos.");
        }
        ProductType productType = new ProductType();
        productType.setName(name);
        productType.setSubCategory(respSubCategory.get());
        productTypeRepository.save(productType);
    }

    @Transactional
    public void modify(Integer id, String name, Integer subCategoryId) throws MiException {
        validate(name, "tipo de producto");
        validateId(subCategoryId, "la sub-categoría");
        Optional<SubCategory> respSubCategory = subCategoryRepository.findById(subCategoryId);
        if (!respSubCategory.isPresent()) {
            throw new MiException("La sub-categoría ingresada no se encuentra en la base de datos.");
        }
        Optional<ProductType> respProductType = productTypeRepository.findById(id);
        if (!respProductType.isPresent()) {
            throw new MiException("El tipo de producto ingresado no se encuentra en la base de datos.");
        }
        Optional<ProductType> respName = productTypeRepository.findByName(name);
        if (respName.isPresent()) {
            throw new MiException("El tipo de producto ingresado ya se encuentra en la base de datos.");
        }
        ProductType productType = respProductType.get();
        productType.setName(name);
        productType.setSubCategory(respSubCategory.get());
        productTypeRepository.save(productType);
    }

    @Transactional
    public void delete(Integer id) throws MiException {
        validateId(id, "tipo de producto");
        Optional<ProductType> resp = productTypeRepository.findById(id);
        if (!resp.isPresent()) {
            throw new MiException("El tipo de producto ingresado no se encuentra en la base de datos.");
        }
        productTypeRepository.deleteById(id);
    }

    public ProductType findByName(String name) throws MiException {
        validate(name, "tipo de producto");
        Optional<ProductType> resp = productTypeRepository.findByName(name);
        if (!resp.isPresent()) {
            throw new MiException("El tipo de producto no se encuentra en la base de datos");
        }
        return resp.get();
    }

    public List<ProductType> listAll() {
        return productTypeRepository.findAll();
    }

    private void validate(String name, String msg) throws MiException {
        if (name == null) {
            throw new MiException("El nombre de " + msg + " no puede ser nulo.");
        }
        if (name.isEmpty()) {
            throw new MiException("El nombre de " + msg + " no puede estar vacio.");
        }

    }

    private void validateId(Integer id, String msg) throws MiException {
        if (id < 0) {
            throw new MiException("Debe ingresar un id valido");
        }
        if (id == 0) {
            throw new MiException("Debe seleccionar una " + msg);
        }
    }
}
