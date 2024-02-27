package tcc.artregistrationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcc.artregistrationservice.models.Art;
import tcc.artregistrationservice.models.Artist;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {
    List<Art> findAllByNameIgnoreCase(String name);

    List<Art> findAllByArtSchoolIgnoreCase(String artSchool);

    List<Art> findAllByArtist(Artist artist);
}

