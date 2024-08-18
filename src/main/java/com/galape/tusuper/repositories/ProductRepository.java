package com.galape.tusuper.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.galape.tusuper.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    public Optional<Product> findByName(@Param("name") String name);
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    public List<Product> findAllByName(@Param("name") String name);
    @Query("SELECT p FROM Product p WHERE p.name = :name AND p.weight = :weight AND p.productType.id = :productTypeId AND p.brand.id = :brandId")
    public Optional<Product> findDuplicate(@Param("name") String name, @Param("weight") Integer weight, @Param("productTypeId") Integer productTypeId, @Param("brandId") Integer brandId);
}
