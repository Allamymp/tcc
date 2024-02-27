package tcc.artregistrationservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    @Column(unique = true)
    private String name;
    @NotNull
    private LocalDate birth;
    @NotBlank
    private String description;
    @NotBlank
    @Column(name = "ARTSCHOOL")
    private String artSchool;
    @NotBlank
    private String country;
    @OneToMany(mappedBy = "artist")
    @JsonBackReference
    private List<Art> artList;

    public Artist(String name, LocalDate birth, String description, String artSchool
            , String country) {
        this.name = name;
        this.birth = birth;
        this.description = description;
        this.artSchool = artSchool;
        this.country = country;
        this.artList = new ArrayList<>();
    }

}
