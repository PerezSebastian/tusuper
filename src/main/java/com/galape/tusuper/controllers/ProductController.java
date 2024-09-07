package com.galape.tusuper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductController {
    public String filterProduct(){
        return "redirect:/productos/" + "productType reemplazar";
    }
}
