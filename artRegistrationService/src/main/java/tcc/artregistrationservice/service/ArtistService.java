package tcc.artregistrationservice.service;

import org.springframework.stereotype.Service;
import tcc.artregistrationservice.models.Artist;
import tcc.artregistrationservice.repository.ArtistRepository;

import java.util.List;
import java.util.Optional;

import static tcc.artregistrationservice.utilities.Utilities.notNullNotBlank;

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

        if (artistOptional.isEmpty()) {
            return Optional.empty();
        }
        return artistOptional.flatMap(artist -> {
            if (notNullNotBlank(data.getName())) {
                artist.setName(data.getName());
            }
            if (data.getBirth() != null) {
                artist.setBirth(data.getBirth());
            }
            if (notNullNotBlank(data.getDescription())) {
                artist.setDescription(data.getDescription());
            }
            if (notNullNotBlank(data.getArtSchool())) {
                artist.setArtSchool(data.getArtSchool());
            }
            if (notNullNotBlank(data.getCountry())) {
                artist.setCountry(data.getCountry());
            }

            return Optional.of(artistRepository.save(artist));
        });
    }


    public Optional<Artist> findById(Long id) {
        return artistRepository.findById(id);
    }

    public Optional<Artist> findByName(String name) {
        return artistRepository.findByName(name);
    }

    public List<Artist> findAllByArtSchool(String artSchool) {
        return artistRepository.findAllByArtSchool(artSchool);
    }


}
