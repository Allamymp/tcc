package tcc.artregistrationservice.common;

import tcc.artregistrationservice.models.Artist;

import java.time.LocalDate;

public class ArtistConstants {
    public static final Artist ARTIST = new Artist(
            "artist",
            LocalDate.of(2024, 2, 22),
            "description",
            "art school",
            "country"
    );
}
