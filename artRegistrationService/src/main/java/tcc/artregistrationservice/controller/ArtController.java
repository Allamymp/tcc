package tcc.artregistrationservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.artregistrationservice.records.art.ArtRequestRecord;
import tcc.artregistrationservice.records.art.ArtResponseRecord;
import tcc.artregistrationservice.service.ArtService;

import java.util.List;

@RestController
@RequestMapping("/art")
public class ArtController {

    private final ArtService artService;

    public ArtController(ArtService artService) {
        this.artService = artService;
    }


    @PostMapping("/create")
    public ResponseEntity<ArtResponseRecord> create(@RequestBody @Valid ArtRequestRecord art) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ArtResponseRecord.fromArt(artService.create(art)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Valid Long id) {
        try {
            artService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ArtResponseRecord> update(@RequestBody @Valid ArtRequestRecord data) {
        try {
            return artService.update(data).map(
                            art -> ResponseEntity.status(HttpStatus.OK).body(ArtResponseRecord.fromArt(art)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtResponseRecord> findById(@PathVariable @Valid Long id) {
        try {
            return artService.findById(id).map(
                            art -> ResponseEntity.status(HttpStatus.OK).body(ArtResponseRecord.fromArt(art)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/name")
    public ResponseEntity<List<ArtResponseRecord>> findAllByName(@RequestParam @Valid String name) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(artService.findAllByName(name).stream()
                    .map(ArtResponseRecord::fromArt)
                    .toList());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/byArtSchool")
    public ResponseEntity<List<ArtResponseRecord>> findAllByArtSchool(@RequestParam @Valid String artSchool) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(artService.findAllByArtSchool(artSchool)
                    .stream()
                    .map(ArtResponseRecord::fromArt)
                    .toList());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
