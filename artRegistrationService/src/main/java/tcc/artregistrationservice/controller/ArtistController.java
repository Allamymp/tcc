package tcc.artregistrationservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.artregistrationservice.models.Artist;
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
    public ResponseEntity<Artist> create(@RequestBody @Valid Artist artist) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(artistService.create(artist));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Artist> update(@RequestBody @Valid Artist data) {
        try {
            Optional<Artist> artistOptional = artistService.update(data);
            return artistOptional.map(artist -> ResponseEntity.status(HttpStatus.OK).body(artist))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Valid Long id) {
        try {
            artistService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> findById(@PathVariable @Valid Long id) {
        try {
            Optional<Artist> artistOptional = artistService.findById(id);
            return artistOptional.map(artist -> ResponseEntity.status(HttpStatus.OK).body(artist))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Artist> findByName(@PathVariable @Valid String name) {
        try {
            Optional<Artist> artistOptional = artistService.findByName(name);
            return artistOptional.map(artist -> ResponseEntity.status(HttpStatus.OK).body(artist))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{artSchool}")
    public ResponseEntity<List<Artist>> findAllByArtSchool(@PathVariable @Valid String artSchool) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(artistService.findAllByArtSchool(artSchool));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
