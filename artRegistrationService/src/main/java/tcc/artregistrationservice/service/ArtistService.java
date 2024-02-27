package tcc.artregistrationservice.service;

import org.springframework.stereotype.Service;
import tcc.artregistrationservice.models.Artist;
import tcc.artregistrationservice.records.artist.ArtistRequestRecord;
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

    public Artist create(ArtistRequestRecord artist) {

        return artistRepository.save(ArtistRequestRecord.toArtist(artist));
    }

    public void delete(Long id) {
        artistRepository.deleteById(id);
    }

    public Optional<Artist> update(ArtistRequestRecord data) {
        Optional<Artist> artistOptional = artistRepository.findById(data.id());

        if (artistOptional.isEmpty()) {
            return Optional.empty();
        }
        return artistOptional.map(artist -> {
            if (notNullNotBlank(data.name())) {
                artist.setName(data.name());
            }
            if (data.birth() != null) {
                artist.setBirth(data.birth());
            }
            if (notNullNotBlank(data.description())) {
                artist.setDescription(data.description());
            }
            if (notNullNotBlank(data.artSchool())) {
                artist.setArtSchool(data.artSchool());
            }
            if (notNullNotBlank(data.country())) {
                artist.setCountry(data.country());
            }

            return artistRepository.save(artist);
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
