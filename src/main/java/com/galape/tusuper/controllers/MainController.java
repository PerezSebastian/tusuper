package com.galape.tusuper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galape.tusuper.entities.Category;
import com.galape.tusuper.services.CategoryService;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String index(ModelMap model){
        List<Category> categoryList = categoryService.listAll();
        model.put("categoryList", categoryList);
        return "index";
    }
}
