package tcc.artregistrationservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_ARTIST")
@NoArgsConstructor
@Getter
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;
    @Setter
    private Date birth;
    @Setter
    private String description;
    @Setter
    private String artSchool;
    @Setter
    private String country;
    @OneToMany(mappedBy = "artist")
    private List<ArtWork> artWorkList;

    public Artist(String name, Date birth, String description, String artSchool
            , String country) {
        this.name = name;
        this.birth = birth;
        this.description = description;
        this.artSchool = artSchool;
        this.country = country;
        this.artWorkList = new ArrayList<>();
    }

}
