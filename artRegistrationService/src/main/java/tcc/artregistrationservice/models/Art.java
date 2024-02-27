package tcc.artregistrationservice.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_ARTWORK")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Art {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalDate date;

    private String artSchool;
    @ManyToOne
   @JsonManagedReference
    private Artist artist;

    public Art(String name, String description, LocalDate date, String artSchool, Artist artist) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.artSchool = artSchool;
        this.artist = artist;
    }
}
