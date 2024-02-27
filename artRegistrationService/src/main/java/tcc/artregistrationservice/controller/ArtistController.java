package tcc.artregistrationservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.artregistrationservice.models.Artist;
import tcc.artregistrationservice.records.artist.ArtistRequestRecord;
import tcc.artregistrationservice.records.artist.ArtistResponseRecord;
import tcc.artregistrationservice.service.ArtistService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping("/create")
    public ResponseEntity<ArtistResponseRecord> create(@RequestBody @Valid ArtistRequestRecord artist) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(artistService.create(artist));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ArtistResponseRecord> update(@RequestBody @Valid ArtistRequestRecord data) {
        try {
            Optional<ArtistResponseRecord> artistOptional = artistService.update(data);
            return artistOptional.map(artist -> ResponseEntity.status(HttpStatus.OK).body(artist))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Valid Long id) {
        try {
            artistService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseRecord> findById(@PathVariable @Valid Long id) {
        try {
            Optional<ArtistResponseRecord> artistOptional = artistService.findById(id);
            return artistOptional.map(artist -> ResponseEntity.status(HttpStatus.OK).body(artist))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/name")
    public ResponseEntity<ArtistResponseRecord> findByName(@RequestParam @Valid String name) {
        try {
            Optional<ArtistResponseRecord> artistOptional = artistService.findByName(name);
            return artistOptional.map(artist -> ResponseEntity.status(HttpStatus.OK).body(artist))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/by-art-school")
    public ResponseEntity<List<ArtistResponseRecord>> findAllByArtSchool(@RequestParam @Valid String artSchool) {
        try {
            List<ArtistResponseRecord> artists = artistService.findAllByArtSchool(artSchool);
            return ResponseEntity.status(HttpStatus.OK).body(artists);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
