package tcc.artregistrationservice.service;

import org.springframework.stereotype.Service;
import tcc.artregistrationservice.models.Artist;
import tcc.artregistrationservice.repository.ArtistRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist create(Artist artist) {
        return artistRepository.save(artist);
    }

    public void delete(Long id) {
        Optional<Artist> artistOptional = artistRepository.findById(id);
        artistOptional.ifPresent(artistRepository::delete);
    }

    public Optional<Artist> update(Artist data) {
        Optional<Artist> artistOptional = findById(data.getId());
        if (artistOptional.isPresent()) {
            artistOptional.ifPresent(artist -> {
                artist.setName(notNullNotBlank(data.getName())
                        ? data.getName() : artist.getName());
                artist.setBirth(notNullNotBlank(String.valueOf(data.getBirth()))
                        ? data.getBirth() : artist.getBirth());
                artist.setDescription(notNullNotBlank(data.getDescription()) ?
                        data.getDescription() : artist.getDescription());
                artist.setArtSchool(notNullNotBlank(data.getArtSchool()) ?
                        data.getArtSchool() : artist.getArtSchool());
                artist.setCountry(notNullNotBlank(data.getCountry()) ?
                        data.getCountry() : artist.getCountry());
            });
            return artistOptional.map(artistRepository::save);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Artist> findById(Long id) {
        return artistRepository.findById(id);
    }

    public Optional<Artist> findByName(String name) {
        return artistRepository.findByName(name);

    }

    public Optional<List<Artist>> findAllByArtSchool(String artSchool) {
        return artistRepository.findAllByArtSchool(artSchool);
    }

    public boolean notNullNotBlank(String value) {
        return value != null && !value.isBlank();
    }
}
