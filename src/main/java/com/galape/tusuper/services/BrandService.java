package com.galape.tusuper.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.galape.tusuper.entities.Brand;
import com.galape.tusuper.exceptions.MiException;
import com.galape.tusuper.repositories.BrandRepository;
import jakarta.transaction.Transactional;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Transactional
    public void create(String name) throws MiException{
        validate(name);
        Optional<Brand> respBrand = brandRepository.findByName(name);
        if (respBrand.isPresent()) {
            throw new MiException("La marca ya se encuentra en la base de datos");
            
        }
        Brand brand = new Brand();
        brand.setName(name);
        brandRepository.save(brand);
    }

    @Transactional
    public void modify(Integer id, String name) throws MiException{
        validate(name);
        validateId(id);
        Optional<Brand> resp = brandRepository.findById(id);
        if (!resp.isPresent()) {
            throw new MiException("La marca que intenta modificar no existe");
        }
        Optional<Brand> respBrand = brandRepository.findByName(name);
        if (respBrand.isPresent()) {
            throw new MiException("La marca ya se encuentra en la base de datos");
            
        }
        Brand brand = resp.get();
        brand.setName(name);
        brandRepository.save(brand);
    }

    @Transactional
    public void delete(Integer id) throws MiException{
        validateId(id);
        Optional<Brand> resp = brandRepository.findById(id);
        if (!resp.isPresent()) {
            throw new MiException("La marca que intenta eliminar no existe");
        }
        brandRepository.deleteById(id);
    }

    public Brand findByName(String name) throws MiException{
        validate(name);
        Optional<Brand> resp = brandRepository.findByName(name);
        if (!resp.isPresent()) {
            throw new MiException("La marca que intenta modificar no existe");
        }
        return resp.get();
    }

    public List<Brand> listAll(){
        return brandRepository.findAll();
    }

    private void validate(String name) throws MiException{
        if (name == null) {
            throw new MiException("El nombre de la marca no puede ser nulo");
        }
        if (name.isEmpty()) {
            throw new MiException("El nombre de la marca no puede estar vacio");
        }
    }

    private void validateId(Integer id) throws MiException{
        if (id < 0) {
            throw new MiException("Debe ingresar un id valido");
        }
        if (id == 0) {
            throw new MiException("Debe seleccionar una marca");
        }
    }
}
