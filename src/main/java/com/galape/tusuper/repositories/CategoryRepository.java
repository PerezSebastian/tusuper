package com.galape.tusuper.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.galape.tusuper.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Integer> {

    @Query("select c from Category c where c.name = :name")
    public Optional<Category> findByName(@Param("name") String name);
}
