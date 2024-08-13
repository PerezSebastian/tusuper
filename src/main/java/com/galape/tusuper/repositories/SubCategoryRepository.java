package com.galape.tusuper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.galape.tusuper.entities.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    @Query("SELECT sc FROM SubCategory sc WHERE sc.name = :name")
    public SubCategory findByName(@Param("name") String name);
    
}
