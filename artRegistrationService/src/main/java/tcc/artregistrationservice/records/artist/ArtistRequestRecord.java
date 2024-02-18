package tcc.artregistrationservice.records.artist;

public record ArtistRequestRecord(
        Long id,
        String name,
        String dateOfBirth,
        String artSchool,
        String country,
        String description) {
}
