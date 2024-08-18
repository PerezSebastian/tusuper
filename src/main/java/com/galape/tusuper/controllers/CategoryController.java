package com.galape.tusuper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.galape.tusuper.entities.Category;
import com.galape.tusuper.services.CategoryService;

@Controller
@RequestMapping("categorias")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String brand(ModelMap model) {
        List<Category> categoryList = categoryService.listAll();
        model.put("categoryList", categoryList);
        return "category/category";
    }

    @PostMapping("/guardar")
    public String save(@RequestParam String name, RedirectAttributes redirectAttributes) {
        try {
            categoryService.create(name.trim());
            redirectAttributes.addFlashAttribute("exito", "La categoria " + name + " se guardo exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/categorias";
    }

    @PostMapping("/modificar")
    public String modify(@RequestParam(required = false) Integer id, @RequestParam String name,
            RedirectAttributes redirectAttributes) {
        try {
            categoryService.modify(id, name.trim());
            redirectAttributes.addFlashAttribute("exito2", "La categoria " + name + " se modifico exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error2", e.getMessage());
        }
        return "redirect:/categorias";
    }

    @PostMapping("/eliminar")
    public String modify(@RequestParam(required = false) Integer id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.delete(id);
            redirectAttributes.addFlashAttribute("exito3", "La categoria se elimino exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error3", e.getMessage());
        }
        return "redirect:/categorias";
    }
}
