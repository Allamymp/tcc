package tcc.artregistrationservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.artregistrationservice.models.Artist;
import tcc.artregistrationservice.records.artist.ArtistCreateRequestRecord;
import tcc.artregistrationservice.service.ArtistService;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    private  final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping("/create")
    public ResponseEntity<Artist> create(@RequestBody @Valid ArtistCreateRequestRecord record){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(artistService.create(record));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
