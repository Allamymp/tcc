package tcc.artregistrationservice.records.artist;

import tcc.artregistrationservice.models.Artist;

import java.time.LocalDate;

public record ArtistResponseRecord(Long id, String name, LocalDate birth, String description, String artSchool,
                                   String country) {

    public static ArtistResponseRecord fromArtist(Artist artist){
        return new ArtistResponseRecord(
                artist.getId(),
                artist.getName(),
                artist.getBirth(),
                artist.getDescription(),
                artist.getArtSchool(),
                artist.getCountry()
        );
    }

}
