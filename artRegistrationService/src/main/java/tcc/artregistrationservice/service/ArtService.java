package tcc.artregistrationservice.service;

import org.springframework.stereotype.Service;
import tcc.artregistrationservice.models.Art;
import tcc.artregistrationservice.repository.ArtRepository;

import java.util.List;
import java.util.Optional;

import static tcc.artregistrationservice.utilities.Utilities.notNullNotBlank;

@Service
public class ArtService {

    private final ArtRepository artRepository;

    public ArtService(ArtRepository artRepository) {
        this.artRepository = artRepository;
    }

    public Art create(Art art) {
        return artRepository.save(art);
    }

    public void delete(Long id) {
        artRepository.deleteById(id);
    }

    public Optional<Art> update(Art artData) {
        Optional<Art> artOptional = findById(artData.getId());

        if (artOptional.isEmpty()) {
            return Optional.empty();
        }

        return artOptional.flatMap(art -> {
            if (notNullNotBlank(artData.getName())) {
                art.setName(artData.getName());
            }
            if (notNullNotBlank(artData.getDescription())) {
                art.setDescription(artData.getDescription());
            }
            if (notNullNotBlank(artData.getArtSchool())) {
                art.setArtSchool(artData.getArtSchool());
            }
            if (artData.getDate()!=null) {
                art.setDate(artData.getDate());
            }
            return Optional.of(artRepository.save(art));
        });
    }

    public Optional<Art> findById(Long id) {
        return artRepository.findById(id);
    }

    public List<Art> findAllByName(String name){
        return artRepository.findAllByName(name);
    }
    public List<Art> findAllByArtSchool(String artSchool){
        return artRepository.findAllByArtSchool(artSchool);
    }
}
