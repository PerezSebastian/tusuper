package com.galape.tusuper.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galape.tusuper.entities.Category;
import com.galape.tusuper.exceptions.MiException;
import com.galape.tusuper.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void create(String name) throws MiException{

        validate(name);

        Category category = categoryRepository.findByName(name);
        
        if(category != null ){
            throw new MiException("La Categoria que quiere crear ya exixte");
        }

        category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }

    @Transactional
    public void modify(Integer id, String name) throws MiException{

        validate(name);
        validateId(id);
        Optional<Category> resp = categoryRepository.findById(id);

        if(!resp.isPresent()){
            throw new MiException("La Categoria que quiere modificar no existe");
        }
        Category category = categoryRepository.findByName(name);
        
        if(category != null ){
            throw new MiException("NO puede modificar porque ya existe "+name);
        }
        category = resp.get();
        category.setName(name);
        categoryRepository.save(category);
    }

    @Transactional
    public void delete(Integer id) throws MiException{
        validateId(id);
        Optional<Category> resp = categoryRepository.findById(id);

        if(!resp.isPresent()){
            throw new MiException("La Categoria que quiere eliminar no existe");
        }
        
        categoryRepository.deleteById(id);
        
    }

    public Category findByName(String name) throws MiException{

        validate(name);
        
        Category category = categoryRepository.findByName(name);

        if(category == null){
            throw new MiException("La categoria que busca no existe");
        }
        return category;
    }
    
    public List<Category> listAll(){

        return categoryRepository.findAll();
    }

    private void validate(String name) throws MiException{
        if(name == null){
            throw new MiException("El nombre no puede nula");
        }
        if(name.isEmpty()){
            throw new MiException("El nombre no puede esta vacio");
        }
    }

    private void validateId(Integer id) throws MiException{
        if (id < 0) {
            throw new MiException("Debe ingresar un id valido");
        }
        if (id == 0) {
            throw new MiException("Debe seleccionar una categoria");
        }
    }
}
