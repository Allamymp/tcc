package tcc.artregistrationservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.artregistrationservice.models.Art;
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
    public ResponseEntity<Art> create(@RequestBody @Valid Art art) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artService.create(art));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Valid Long id) {
        artService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/update")
    public ResponseEntity<Art> update(@RequestBody @Valid Art data) {
        return artService.update(data).map(art -> ResponseEntity.status(HttpStatus.OK).body(art))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Art> findById(@PathVariable @Valid Long id) {
        return artService.findById(id).map(art -> ResponseEntity.status(HttpStatus.OK).body(art))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/findAllByName/{name}")
    public ResponseEntity<List<Art>> findAllByName(@PathVariable @Valid String name) {
        return ResponseEntity.status(HttpStatus.OK).body(artService.findAllByName(name));
    }

    @GetMapping("/findAllByArtSchool/{artSchool}")
    public ResponseEntity<List<Art>> findAllByArtSchool(@PathVariable @Valid String artSchool) {
        return ResponseEntity.status(HttpStatus.OK).body(artService.findAllByArtSchool(artSchool));
    }
}
