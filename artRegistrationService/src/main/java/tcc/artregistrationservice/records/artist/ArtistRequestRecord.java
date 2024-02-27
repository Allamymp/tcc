package tcc.artregistrationservice.records.artist;

import tcc.artregistrationservice.models.Artist;

import java.time.LocalDate;

public record ArtistRequestRecord(Long id, String name, LocalDate birth, String description, String artSchool,
                                  String country) {

    public static Artist toArtist(ArtistRequestRecord record){
        return new Artist(
                record.name(),
                record.birth(),
                record.description(),
                record.artSchool(),
                record.country()
        );
    }
}
