package tcc.artregistrationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcc.artregistrationservice.models.Artist;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByName(String name);

    Optional<List<Artist>> findAllByDateOfBirth(Date date);

    Optional<List<Artist>> findAllByArtSchool(String artSchool);
}
