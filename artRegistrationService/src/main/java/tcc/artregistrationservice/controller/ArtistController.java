package tcc.artregistrationservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.artregistrationservice.records.artist.ArtistRequestRecord;
import tcc.artregistrationservice.records.artist.ArtistResponseRecord;
import tcc.artregistrationservice.service.ArtistService;

import java.util.List;

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
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ArtistResponseRecord.fromArtist(artistService.create(artist)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ArtistResponseRecord> update(@RequestBody @Valid ArtistRequestRecord data) {
        try {
            return artistService.update(data).map(
                            artist -> ResponseEntity.status(HttpStatus.OK).body(ArtistResponseRecord.fromArtist(artist)))
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
            return artistService.findById(id).map(
                            artist -> ResponseEntity.status(HttpStatus.OK).body(ArtistResponseRecord.fromArtist(artist)))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/name")
    public ResponseEntity<ArtistResponseRecord> findByName(@RequestParam @Valid String name) {
        try {
            return artistService.findByName(name).map(
                            artist -> ResponseEntity.status(HttpStatus.OK).body(ArtistResponseRecord.fromArtist(artist)))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/byArtSchool")
    public ResponseEntity<List<ArtistResponseRecord>> findAllByArtSchool(@RequestParam @Valid String artSchool) {
        try {

            return ResponseEntity.status(HttpStatus.OK)
                    .body(artistService.findAllByArtSchool(artSchool).stream()
                            .map(ArtistResponseRecord::fromArtist)
                            .toList());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
