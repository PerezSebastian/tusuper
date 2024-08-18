package com.galape.tusuper.entities;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    private Category category;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subCategory")
    private List<ProductType> productTypes;
    public SubCategory() {
    }
    public SubCategory(Integer id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public List<ProductType> getProductTypes() {
        return productTypes;
    }
    public void setProductTypes(List<ProductType> productTypes) {
        this.productTypes = productTypes;
    }
}
