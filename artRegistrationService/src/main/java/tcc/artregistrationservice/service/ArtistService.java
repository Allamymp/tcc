package tcc.artregistrationservice.service;

import org.springframework.stereotype.Service;
import tcc.artregistrationservice.models.Artist;
import tcc.artregistrationservice.records.artist.ArtistRequestRecord;
import tcc.artregistrationservice.records.artist.ArtistResponseRecord;
import tcc.artregistrationservice.repository.ArtistRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static tcc.artregistrationservice.utilities.Utilities.notNullNotBlank;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public ArtistResponseRecord create(ArtistRequestRecord artist) {

        return ArtistResponseRecord.fromArtist(
                artistRepository.save(
                        ArtistRequestRecord.toArtist(artist)
                )
        );
    }

    public void delete(Long id) {
        artistRepository.deleteById(id);
    }

    public Optional<ArtistResponseRecord> update(ArtistRequestRecord data) {
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

            return ArtistResponseRecord.fromArtist(artistRepository.save(artist));
        });
    }


    public Optional<ArtistResponseRecord> findById(Long id) {
        Optional<Artist> artistOptional = artistRepository.findById(id);
        return artistOptional.map(ArtistResponseRecord::fromArtist);
    }

    public Optional<ArtistResponseRecord> findByName(String name) {
        Optional<Artist> artistOptional = artistRepository.findByName(name);
        return artistOptional.map(ArtistResponseRecord::fromArtist);
    }

    public List<ArtistResponseRecord> findAllByArtSchool(String artSchool) {
        return artistRepository.findAllByArtSchool(artSchool).stream()
                .map(ArtistResponseRecord::fromArtist)
                .collect(Collectors.toList());
    }


}
