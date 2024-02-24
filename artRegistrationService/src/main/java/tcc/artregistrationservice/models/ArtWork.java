package tcc.artregistrationservice.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_ARTWORK")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ArtWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String date;

    private String artSchool;
    @ManyToOne
    private Artist artist;

    public ArtWork(String name, String description, String date, String artSchool, Artist artist) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.artSchool = artSchool;
        this.artist = artist;
    }
}
