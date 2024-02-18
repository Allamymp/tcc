package tcc.artregistrationservice.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="TB_ARTWORK")
@NoArgsConstructor
@Getter
@ToString
public class ArtWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Setter
    private String name;
    @Setter
    private String description;
    @Setter
    private String year;
    @Setter
    private String artSchool;
    @ManyToOne
    private Artist artist;

    public ArtWork(String name, String description, String year, String artSchool, Artist artist) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.artSchool = artSchool;
        this.artist = artist;
    }
}
