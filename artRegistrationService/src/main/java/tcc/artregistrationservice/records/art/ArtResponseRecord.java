package tcc.artregistrationservice.records.art;

import tcc.artregistrationservice.models.Art;
import tcc.artregistrationservice.models.Artist;

import java.time.LocalDate;

public record ArtResponseRecord(Long id, String name, String description, LocalDate date
        , String artSchool, Artist artist) {


    public static ArtResponseRecord fromArt(Art art) {
        return new ArtResponseRecord(
                art.getId(),
                art.getName(),
                art.getDescription(),
                art.getDate(),
                art.getArtSchool(),
                art.getArtist()
        );
    }
}
