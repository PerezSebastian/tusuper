package com.galape.tusuper.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galape.tusuper.entities.Category;
import com.galape.tusuper.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class Categoryservice  {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void create(String name){
        
       Category category = new Category();

       category.setName(name);
       categoryRepository.save(category);
    }

    @Transactional
    public void modify(Integer id, String name){


        Optional<Category> resp = categoryRepository.findById(id);
        
        if(resp.isPresent()){
            Category category = resp.get();
            category.setName(name);
            categoryRepository.save(category);
        }
    }

    @Transactional
    public void delete(Integer id){

        Optional<Category> resp = categoryRepository.findById(id);

        if(resp.isPresent()){
            categoryRepository.deleteById(id);
        }
    }

    @Transactional
    public Category findByName(){
        Optional<Category> resp = CategoryRepository.findByName(name)
    }
}
