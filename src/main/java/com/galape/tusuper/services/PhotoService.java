package com.galape.tusuper.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.galape.tusuper.entities.Photo;
import com.galape.tusuper.exceptions.MiException;
import com.galape.tusuper.repositories.PhotoRepository;
import jakarta.transaction.Transactional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Transactional
    public Photo create(MultipartFile file) throws MiException {
        validateFile(file);
        try {
            Photo photo = new Photo();
            photo.setMime(file.getContentType());
            photo.setName(file.getName());
            photo.setContent(file.getBytes());
            photoRepository.save(photo);
            return photo;
        } catch (Exception e) {
            throw new MiException("Error al crear la imagen: " + e.getMessage());
        }
    }

    @Transactional
    public void modify(String id, MultipartFile file) throws MiException {
        validateFile(file);
        validateId(id);
        Optional<Photo> resp = photoRepository.findById(id);
        if (!resp.isPresent()) {
            throw new MiException("La imagen que intenta modificar no esta en la base de datos");
        }
        try {
            Photo photo = resp.get();
            photo.setMime(file.getContentType());
            photo.setName(file.getName());
            photo.setContent(file.getBytes());
            photoRepository.save(photo);
        } catch (Exception e) {
            throw new MiException("Error al modificar la imagen: " + e.getMessage());
        }
    }

    private void validateFile(MultipartFile file) throws MiException {
        if (file == null) {
            throw new MiException("La imagen no puede ser nula");
        }
        if (file.isEmpty()) {
            throw new MiException("La imagen no puede estar vacia");
        }
    }

    private void validateId(String id) throws MiException {
        if (id == null) {
            throw new MiException("El id de la imagen no puede ser nula");
        }
        if (id.isEmpty()) {
            throw new MiException("El id de la imagen no puede estar vacia");
        }
    }
}
