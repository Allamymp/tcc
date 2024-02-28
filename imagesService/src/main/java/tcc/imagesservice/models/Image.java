package tcc.imagesservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    private String id;
    private String name;
    private byte[] data;

    public Image(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }
}
