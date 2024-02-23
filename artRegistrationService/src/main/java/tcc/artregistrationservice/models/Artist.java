package tcc.artregistrationservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_ARTIST")
@NoArgsConstructor
@Getter
@Setter
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birth;
    private String description;
    private String artSchool;
    private String country;
    @OneToMany(mappedBy = "artist")
    private List<ArtWork> artWorkList;

    public Artist(String name, LocalDate birth, String description, String artSchool
            , String country) {
        this.name = name;
        this.birth = birth;
        this.description = description;
        this.artSchool = artSchool;
        this.country = country;
        this.artWorkList = new ArrayList<>();
    }

}
