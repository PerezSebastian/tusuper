package com.galape.tusuper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.galape.tusuper.entities.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {
}
