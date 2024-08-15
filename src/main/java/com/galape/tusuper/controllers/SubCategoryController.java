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
import com.galape.tusuper.entities.SubCategory;
import com.galape.tusuper.services.CategoryService;
import com.galape.tusuper.services.SubCategoryService;

@Controller
@RequestMapping("/subcategorias")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String brand(ModelMap model) {
        List<SubCategory> subCategoryList = subCategoryService.listAll();
        model.put("subCategoryList", subCategoryList);
        List<Category> categoryList = categoryService.listAll();
        model.put("categoryList", categoryList);
        return "subCategory/subCategory";
    }

    @PostMapping("/guardar")
    public String save(@RequestParam String name, @RequestParam Integer idCategory,
            RedirectAttributes redirectAttributes) {
        try {
            subCategoryService.create(name.trim(), idCategory);
            redirectAttributes.addFlashAttribute("exito", "La subcategoria " + name + " se guardo exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/subcategorias";
    }

    @PostMapping("/modificar")
    public String modify(@RequestParam(required = false) Integer id, @RequestParam String name,
            @RequestParam Integer idCategory, RedirectAttributes redirectAttributes) {
        try {
            subCategoryService.modify(id, name.trim(), idCategory);
            redirectAttributes.addFlashAttribute("exito2", "La subcategoria " + name + " se modifico exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error2", e.getMessage());
        }
        return "redirect:/subcategorias";
    }

    @PostMapping("/eliminar")
    public String modify(@RequestParam(required = false) Integer id, RedirectAttributes redirectAttributes) {
        try {
            subCategoryService.delete(id);
            redirectAttributes.addFlashAttribute("exito3", "La subcategoria se elimino exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error3", e.getMessage());
        }
        return "redirect:/subcategorias";
    }
}
