package com.galape.tusuper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.galape.tusuper.entities.ProductType;
import com.galape.tusuper.entities.SubCategory;
import com.galape.tusuper.services.ProductTypeService;
import com.galape.tusuper.services.SubCategoryService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/tiposdeproducto")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("")
    public String productType(ModelMap model) {
        List<ProductType> productTypeList = productTypeService.listAll();
        model.put("productTypeList", productTypeList);
        List<SubCategory> subCategoryList = subCategoryService.listAll();
        model.put("subCategoryList", subCategoryList);
        return "productType/productType";
    }

    @PostMapping("/guardar")
    public String save(@RequestParam String name, @RequestParam Integer subCategoryId,
            RedirectAttributes redirectAttributes) {
        try {
            productTypeService.create(name.trim(), subCategoryId);
            redirectAttributes.addFlashAttribute("exito", "El tipo de producto se ha guardado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/tiposdeproducto";
    }

    @PostMapping("/modificar")
    public String modify(@RequestParam(required = false) Integer id, @RequestParam String name,
            @RequestParam Integer subCategoryId, RedirectAttributes redirectAttributes) {
        try {
            productTypeService.modify(id, name.trim(), subCategoryId);
            redirectAttributes.addFlashAttribute("exito2", "El tipo de producto se ha modificado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error2", e.getMessage());
        }

        return "redirect:/tiposdeproducto";
    }

    @PostMapping("/eliminar")
    public String delete(@RequestParam(required = false) Integer id, RedirectAttributes redirectAttributes) {
        try {
            productTypeService.delete(id);
            redirectAttributes.addFlashAttribute("exito3", "El tipo de producto se ha eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error3", e.getMessage());
        }

        return "redirect:/tiposdeproducto";
    }

}
