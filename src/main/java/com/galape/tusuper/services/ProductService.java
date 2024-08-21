package com.galape.tusuper.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.galape.tusuper.entities.Brand;
import com.galape.tusuper.entities.Photo;
import com.galape.tusuper.entities.Product;
import com.galape.tusuper.entities.ProductType;
import com.galape.tusuper.enums.MeasurementType;
import com.galape.tusuper.exceptions.MiException;
import com.galape.tusuper.repositories.BrandRepository;
import com.galape.tusuper.repositories.ProductRepository;
import com.galape.tusuper.repositories.ProductTypeRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private PhotoService photoService;

    public void create(String name, Double price, Integer discount, Integer stock, Integer weight,
            MeasurementType measurementType, Integer productTypeId, Integer brandId, MultipartFile file)
            throws MiException {
        validate(name, "producto");
        validatePrice(price);
        validateDiscount(discount);
        validateStock(stock);
        validateWeight(weight);
        validateId(brandId, "la marca");
        validateId(productTypeId, "tipo de producto");
        // Validar MeasurementType enum
        Optional<Brand> respBrand = brandRepository.findById(brandId);
        if (!respBrand.isPresent()) {
            throw new MiException("La marca ingresada no se encuentra en la base de datos.");
        }
        Optional<ProductType> respProductType = productTypeRepository.findById(productTypeId);
        if (!respProductType.isPresent()) {
            throw new MiException("El tipo de producto ingresado no se encuentra en la base de datos.");
        }
        validateDuplicate(name, weight, productTypeId, brandId);
        Photo photo = photoService.create(file);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setStock(stock);
        product.setWeight(weight);
        product.setPhoto(photo);
        product.setBrand(respBrand.get());
        product.setProductType(respProductType.get());
    }

    public void modify(Integer id, String name, Double price, Integer discount, Integer stock, Integer weight, MeasurementType measurementType, Integer productTypeId, Integer brandId, MultipartFile file)  throws MiException{
        validateId(id, "producto");
        Optional<Product> resp = productRepository.findById(id);
        if (!resp.isPresent()) {
            throw new MiException("El producto proporcionado no se encuentra en la base de datos.");
        }
        validate(name, "producto");
        validatePrice(price);
        validateDiscount(discount);
        validateStock(stock);
        validateWeight(weight);
        validateId(brandId, "la marca");
        validateId(productTypeId, "tipo de producto");

        Optional<Brand> respBrand = brandRepository.findById(brandId);
        if(!respBrand.isPresent()){
            throw new MiException("La marca ingresada no se encuentra en la base de datos.");
        }
        Optional<ProductType> respProductType = productTypeRepository.findById(productTypeId);
        if(!respProductType.isPresent()){
            throw new MiException("El tipo de producto ingresado no se encuentra en la base de datos.");
        }
        validateDuplicate(name, weight, productTypeId, brandId);
        Photo photo = photoService.create(file);
        Product product = resp.get();
        product.setName(name);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setStock(stock);
        product.setWeight(weight);
        product.setMeasurementType(measurementType);
        product.setProductType(respProductType.get());
        product.setBrand(respBrand.get());
        if (photo != null){
            product.setPhoto(photo);
        }
        productRepository.save(product);
    }

    public void delete(Integer id) throws MiException {
        validateId(id, "producto");
        Optional<Product> resp = productRepository.findById(id);
        if (!resp.isPresent()) {
            throw new MiException("El producto proporcionado no se encuentra en la base de datos.");
        }
        productRepository.deleteById(id);
    }

    public List<Product> findAllByName(String name) {
        return productRepository.findAllByName(name);
    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    private void validate(String name, String msg) throws MiException {
        if (name == null) {
            throw new MiException("El nombre de " + msg + "no puede ser nulo.");
        }
        if (name.isEmpty()) {
            throw new MiException("El nombre de " + msg + " no puede estar vacio.");
        }
    }

    private void validateId(Integer id, String name) throws MiException {
        if (id < 0) {
            throw new MiException("Debe ingresar un id valido");
        }
        if (id == 0) {
            throw new MiException("Debe seleccionar " + name);
        }
    }

    private void validatePrice(Double price) throws MiException {
        if (price < 0) {
            throw new MiException("El precio del producto debe ser mayor a $0");
        }
    }

    private void validateDiscount(Integer discount) throws MiException {
        if (discount < 0 || discount > 100) {
            throw new MiException("El descuento debe ser un valor entre 0 y 100.");
        }
    }

    private void validateWeight(Integer weight) throws MiException {
        if (weight <= 0) {
            throw new MiException("El peso debe ser mayor a 0.");
        }
    }

    private void validateStock(Integer stock) throws MiException {
        if (stock < 0) {
            throw new MiException("El stock del producto debe ser mayor o igual a 0.");
        }
    }

    private void validateDuplicate(String name, Integer weight, Integer productTypeId, Integer brandId)
            throws MiException {
        Optional<Product> respProduct = productRepository.findDuplicate(name, weight, productTypeId, brandId);
        if (respProduct.isPresent()) {
            throw new MiException("El producto ingresado ya se encuentra en la base de datos.");
        }
    }
}
