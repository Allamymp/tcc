package tcc.artregistrationservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import tcc.artregistrationservice.models.Art;
import tcc.artregistrationservice.models.Artist;
import tcc.artregistrationservice.records.art.ArtRequestRecord;
import tcc.artregistrationservice.repository.ArtRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static tcc.artregistrationservice.utilities.Utilities.notNullNotBlank;

@Service
public class ArtService {

    private final ArtRepository artRepository;
    private final ArtistService artistService;


    public ArtService(ArtRepository artRepository, ArtistService artistService) {
        this.artRepository = artRepository;
        this.artistService = artistService;

    }

    public Art create(ArtRequestRecord art) {
        Artist artist = artistService.findByName(art.artistName())
                .orElseThrow(() -> new EntityNotFoundException("Artist not found with name: " + art.artistName()));
        return artRepository.save(ArtRequestRecord.toArt(art, artist));
    }

    public void delete(Long id) {
        artRepository.deleteById(id);
    }

    public Optional<Art> update(ArtRequestRecord record) {
        Optional<Art> artOptional = artRepository.findById(record.id());

        if (artOptional.isEmpty()) {
            return Optional.empty();
        }
        return artOptional.map(art -> {
            if (notNullNotBlank(record.name())) {
                art.setName(record.name());
            }
            if (notNullNotBlank(record.description())) {
                art.setDescription(record.description());
            }
            if (record.date() != null) {
                art.setDate(record.date());
            }
            if (notNullNotBlank(record.artSchool())) {
                art.setArtSchool(record.artSchool());
            }
            return artRepository.save(art);
        });
    }


    public Optional<Art> findById(Long id) {
        return artRepository.findById(id);
    }

    public List<Art> findAllByName(String name) {
        return artRepository.findAllByNameIgnoreCase(name);
    }

    public List<Art> findAllByArtSchool(String artSchool) {
        return artRepository.findAllByArtSchoolIgnoreCase(artSchool);
    }

    public List<Art> findAllByArtistName(String artistName) {
        return artistService.findByName(artistName)
                .map(artRepository::findAllByArtist)
                .orElse(Collections.emptyList());
    }
}
