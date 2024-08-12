package com.galape.tusuper.entities;

import com.galape.tusuper.enums.MeasurementType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private ProductType ProductType;
    @ManyToOne
    private Brand brand;
    private String name;
    private Double price;
    private Integer discount;
    private Integer stock;
    private Integer weight;
    @Enumerated(EnumType.STRING)
    private MeasurementType measurementType;
    @OneToOne
    private Photo photo;
    public Product() {
    }
    public Product(Integer id, com.galape.tusuper.entities.ProductType productType, Brand brand, String name,
            Double price, Integer discount, Integer stock, Integer weight, MeasurementType measurementType,
            Photo photo) {
        this.id = id;
        ProductType = productType;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.weight = weight;
        this.measurementType = measurementType;
        this.photo = photo;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public ProductType getProductType() {
        return ProductType;
    }
    public void setProductType(ProductType productType) {
        ProductType = productType;
    }
    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getDiscount() {
        return discount;
    }
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public MeasurementType getMeasurementType() {
        return measurementType;
    }
    public void setMeasurementType(MeasurementType measurementType) {
        this.measurementType = measurementType;
    }
    public Photo getPhoto() {
        return photo;
    }
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
