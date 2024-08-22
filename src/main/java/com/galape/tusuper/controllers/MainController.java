package com.galape.tusuper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galape.tusuper.entities.Category;
import com.galape.tusuper.entities.Product;
import com.galape.tusuper.entities.ProductType;
import com.galape.tusuper.services.CategoryService;
import com.galape.tusuper.services.ProductService;
import com.galape.tusuper.services.ProductTypeService;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("")
    public String index(ModelMap model){
        List<Category> categoryList = categoryService.listAll();
        model.put("categoryList", categoryList);
        return "index";
    }

    @GetMapping("/productos/{productTypeName}")
    public String productsByType(@PathVariable String productTypeName, ModelMap model){
        List<String> brandList = productService.listBrandsByType(productTypeName);
        model.put("brandList", brandList);
        List<Category> categoryList = categoryService.listAll();
        model.put("categoryList", categoryList);
        List<Product> productList = productService.listByProductTypeName(productTypeName);
        if (productList.isEmpty()) {
            model.put("error", "No hay productos "+productTypeName+" disponibles");
        }
        model.put("productList", productList);
        try {
            ProductType productType = productTypeService.findByName(productTypeName);
            model.put("productType", productType);
        } catch (Exception e) {
            model.put("error2", e.getMessage());
        }
        return "product/productList";
    }
}
