package tcc.artregistrationservice.records.artist;

public record ArtistCreateRequestRecord(String name,
                                        String dateOfBirth,
                                        String artSchool,
                                        String country,
                                        String description) {
}
