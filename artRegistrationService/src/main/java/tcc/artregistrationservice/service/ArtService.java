package tcc.artregistrationservice.service;

import org.springframework.stereotype.Service;
import tcc.artregistrationservice.models.Art;
import tcc.artregistrationservice.models.Artist;
import tcc.artregistrationservice.records.art.ArtRequestRecord;
import tcc.artregistrationservice.records.art.ArtResponseRecord;
import tcc.artregistrationservice.repository.ArtRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static tcc.artregistrationservice.utilities.Utilities.notNullNotBlank;

@Service
public class ArtService {

    private final ArtRepository artRepository;
    private final ArtistService artistService;

    public ArtService(ArtRepository artRepository, ArtistService artistService) {
        this.artRepository = artRepository;
        this.artistService = artistService;
    }

    public ArtResponseRecord create(ArtRequestRecord art) {
        Artist artist = artistService.findByName(art.artistName())
                .orElseThrow(() -> new RuntimeException("Artist not found: " + art.artistName()));
        Art newArt = ArtRequestRecord.toArt(art, artist);
        return ArtResponseRecord.fromArt(artRepository.save(newArt));
    }

    public void delete(Long id) {
        artRepository.deleteById(id);
    }

    public Optional<ArtResponseRecord> update(ArtRequestRecord artData) {
        Optional<Art> artOptional = artRepository.findById(artData.id());

        if (artOptional.isEmpty()) {
            return Optional.empty();
        }
        return artOptional.map(art -> {
            if (notNullNotBlank(artData.name())) {
                art.setName(artData.name());
            }
            if (notNullNotBlank(artData.description())) {
                art.setDescription(artData.description());
            }
            if (artData.date() != null) {
                art.setDate(artData.date());
            }
            if (notNullNotBlank(artData.artSchool())) {
                art.setArtSchool(artData.artSchool());
            }
            return ArtResponseRecord.fromArt(artRepository.save(art));
        });
    }


    public Optional<ArtResponseRecord> findById(Long id) {
        Optional<Art> artOptional = artRepository.findById(id);
        return artOptional.map(ArtResponseRecord::fromArt);
    }

    public List<ArtResponseRecord> findAllByName(String name) {
        return artRepository.findAllByName(name).stream()
                .map(ArtResponseRecord::fromArt)
                .collect(Collectors.toList());
    }

    public List<ArtResponseRecord> findAllByArtSchool(String artSchool) {
        return artRepository.findAllByArtSchool(artSchool).stream()
                .map(ArtResponseRecord::fromArt)
                .collect(Collectors.toList());
    }
}
