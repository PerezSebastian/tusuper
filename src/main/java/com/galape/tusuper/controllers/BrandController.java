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
import com.galape.tusuper.entities.Brand;
import com.galape.tusuper.services.BrandService;

@Controller
@RequestMapping("marcas")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping("")
    public String brand(ModelMap model){
        List<Brand> brandList = brandService.listAll();
        model.put("brandList",brandList);
        return "brand/brand";
    }
    @PostMapping("/guardar")
    public String save(@RequestParam String name, RedirectAttributes redirectAttributes){
        try {
            brandService.create(name.trim());
            redirectAttributes.addFlashAttribute("exito", "La marca "+ name +" se guardo exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/marcas";
    }
    @PostMapping("/modificar")
    public String modify(@RequestParam(required = false) Integer id, @RequestParam String name, RedirectAttributes redirectAttributes){
        try {
            brandService.modify(id, name.trim());
            redirectAttributes.addFlashAttribute("exito2", "La marca "+ name +" se modifico exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error2", e.getMessage());
        }
        return "redirect:/marcas";
    }
    @PostMapping("/eliminar")
    public String modify(@RequestParam(required = false) Integer id, RedirectAttributes redirectAttributes){
        try {
            brandService.delete(id);
            redirectAttributes.addFlashAttribute("exito3", "La marca se elimino exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error3", e.getMessage());
        }
        return "redirect:/marcas";
    }
}
