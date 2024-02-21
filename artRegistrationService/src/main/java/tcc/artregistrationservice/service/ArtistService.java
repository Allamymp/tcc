package tcc.artregistrationservice.service;

import org.springframework.stereotype.Service;
import tcc.artregistrationservice.models.Artist;
import tcc.artregistrationservice.records.artist.ArtistCreateRequestRecord;
import tcc.artregistrationservice.records.artist.ArtistRequestRecord;
import tcc.artregistrationservice.repository.ArtistRepository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist create(ArtistCreateRequestRecord record) throws ParseException {
        Timestamp timestamp = toTimestamp(record.dateOfBirth());
        Artist artist = new Artist(
                record.name(),
                timestamp,
                record.description(),
                record.artSchool(),
                record.country()
        );
        return artistRepository.save(artist);
    }

    public void delete(ArtistRequestRecord record) {
        Optional<Artist> artistOptional = artistRepository.findById(record.id());
        artistOptional.ifPresent(artistRepository::delete);
    }

    public Optional<Artist> update(ArtistRequestRecord record) {
        Optional<Artist> artistOptional = findById(record.id());

        if (artistOptional.isPresent()) {
            artistOptional.ifPresent(artist -> {

                artist.setName(notNullNotBlank(record.name()) ? record.name() : artist.getName());

                artist.setDateOfBirth(notNullNotBlank(record.dateOfBirth()) ?
                        Timestamp.valueOf(record.dateOfBirth()) : artist.getDateOfBirth());

                artist.setDescription(notNullNotBlank(record.description()) ?
                        record.description() : artist.getDescription());

                artist.setArtSchool(notNullNotBlank(record.artSchool()) ?
                        record.artSchool() : artist.getArtSchool());

                artist.setCountry(notNullNotBlank(record.country()) ?
                        record.country() : artist.getCountry());
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

    public Optional<List<Artist>> findAllByDateOfBirth(Timestamp dateOfBirth) {
        return artistRepository.findAllByDateOfBirth(dateOfBirth);
    }

    public Optional<List<Artist>> findAllByArtSchool(String artSchool) {
        return artistRepository.findAllByArtSchool(artSchool);
    }

    public boolean notNullNotBlank(String value) {
        return value != null && !value.isBlank();
    }
    public Timestamp toTimestamp(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = dateFormat.parse(date);
        return new Timestamp(parsedDate.getTime());
    }

}
