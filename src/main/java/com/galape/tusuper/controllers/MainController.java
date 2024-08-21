package com.galape.tusuper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galape.tusuper.entities.Category;
import com.galape.tusuper.services.CategoryService;
import com.galape.tusuper.services.ProductService;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String index(ModelMap model){
        List<Category> categoryList = categoryService.listAll();
        model.put("categoryList", categoryList);
        return "index";
    }

    // @GetMapping("/productos/${productTypeName}")
    // public String productsByTeam(@PathVariable String productTypeName, ModelMap model){
    //     try {
            
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //     }
    //     return "product/productByType";
    // }
}
