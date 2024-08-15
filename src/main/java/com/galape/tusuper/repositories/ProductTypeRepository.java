package com.galape.tusuper.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.galape.tusuper.entities.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {

    @Query("SELECT pt FROM ProductType pt WHERE pt.name = :name")
    public Optional<ProductType> findByName(@Param("name") String name);

}
