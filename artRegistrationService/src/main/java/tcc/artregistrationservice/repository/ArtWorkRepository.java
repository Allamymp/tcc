package tcc.artregistrationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcc.artregistrationservice.models.ArtWork;

@Repository
public interface ArtWorkRepository extends JpaRepository<ArtWork, Long> {
}
