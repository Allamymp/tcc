package tcc.imagesservice.services;

import org.springframework.stereotype.Service;
import tcc.imagesservice.models.Image;
import tcc.imagesservice.repositories.ImageRepository;

@Service
public class ImageService {

    private final ImageRepository repository;

    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }

    public Image create(Image image) {
        return repository.save(image);
    }
}
