package tcc.artregistrationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcc.artregistrationservice.models.Art;

import java.util.List;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {
    List<Art> findAllByName(String name);

    List<Art> findAllByArtSchool(String artSchool);
}
