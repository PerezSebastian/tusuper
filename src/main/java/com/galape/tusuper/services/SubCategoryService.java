package com.galape.tusuper.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galape.tusuper.entities.Category;
import com.galape.tusuper.entities.SubCategory;
import com.galape.tusuper.exceptions.MiException;
import com.galape.tusuper.repositories.CategoryRepository;
import com.galape.tusuper.repositories.SubCategoryRepository;
import jakarta.transaction.Transactional;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void create(String name, Integer categoryId) throws MiException {
        validate(name);
        validateId(categoryId, "categoría");
        Optional<Category> respCategory = categoryRepository.findById(categoryId);
        if (!respCategory.isPresent()) {
            throw new MiException("La categoría ingresada no se encuentra en la base de datos.");
        }
        Optional<SubCategory> respSubCategory = subCategoryRepository.findByName(name);
        if (respSubCategory.isPresent()) {
            throw new MiException("La sub-categoría ya se encuentra en la base de datos.");
        }
        SubCategory subCategory = new SubCategory();
        subCategory.setName(name);
        subCategory.setCategory(respCategory.get());
        subCategoryRepository.save(subCategory);
    }

    @Transactional
    public void delete(Integer id) throws MiException {
        validateId(id, "sub-categoría");
        Optional<SubCategory> resp = subCategoryRepository.findById(id);
        if (!resp.isPresent()) {
            throw new MiException("El nombre proporcionado no se encuentra en la base de datos.");
        }
        subCategoryRepository.deleteById(id);
    }

    @Transactional
    public void modify(Integer id, String name, Integer categoryId) throws MiException {
        validate(name);
        validateId(id, "sub-categoría");
        validateId(categoryId, "categoría");
        Optional<Category> resp1 = categoryRepository.findById(categoryId);
        if (!resp1.isPresent()) {
            throw new MiException("La categoría proporcionada no se encuentra en la base de datos.");
        }
        Optional<SubCategory> resp2 = subCategoryRepository.findById(id);
        if (!resp2.isPresent()) {
            throw new MiException("La sub-categoría proporcionada no se encuentra en la base de datos.");
        }
        Optional<SubCategory> respSubCategory = subCategoryRepository.findByName(name);
        if (respSubCategory.isPresent()) {
            throw new MiException("La sub-categoría ya se encuentra en la base de datos.");
        }
        SubCategory subCategory = resp2.get();
        subCategory.setName(name);
        subCategory.setCategory(resp1.get());
        subCategoryRepository.save(subCategory);
    }

    public SubCategory findByName(String name) throws MiException {
        validate(name);
        Optional<SubCategory> resp = subCategoryRepository.findByName(name);
        if (!resp.isPresent()) {
            throw new MiException("El nombre proporcionado no se encuentra en la base de datos.");
        }
        return resp.get();
    }

    public List<SubCategory> listAll() {
        return subCategoryRepository.findAll();
    }

    private void validate(String name) throws MiException {
        if (name == null) {
            throw new MiException("El nombre de la sub-categoría no puede ser nulo.");
        }
        if (name.isEmpty()) {
            throw new MiException("El nombre de la sub-categoría no puede estar vacio.");
        }

    }

    private void validateId(Integer id, String name) throws MiException {
        if (id < 0) {
            throw new MiException("Debe ingresar un id valido");
        }
        if (id == 0) {
            throw new MiException("Debe seleccionar una " + name);
        }
    }
}
