package tcc.imagesservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tcc.imagesservice.models.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image,String> {
}
