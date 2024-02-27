package tcc.artregistrationservice.records.art;

import tcc.artregistrationservice.models.Art;
import tcc.artregistrationservice.models.Artist;

import java.time.LocalDate;

public record ArtRequestRecord(Long id, String name, String description, LocalDate date
        , String artSchool, String artistName) {

    public static Art toArt(ArtRequestRecord artRequestRecord, Artist artist) {
        return new Art(
                artRequestRecord.name(),
                artRequestRecord.description(),
                artRequestRecord.date(),
                artRequestRecord.artSchool(),
                artist
        );
    }
}
