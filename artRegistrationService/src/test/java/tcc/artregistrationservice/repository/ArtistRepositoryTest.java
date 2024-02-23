package tcc.artregistrationservice.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tcc.artregistrationservice.models.Artist;


import static org.assertj.core.api.Assertions.assertThat;
import static tcc.artregistrationservice.common.ArtistConstants.ARTIST;

@DataJpaTest
public class ArtistRepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach
    public void afterEach() {
        ARTIST.setId(null);
    }

    @Test
    public void createArtist_WithValidData_ReturnsArtist() {
        //Arrange
        Artist artist = artistRepository.save(ARTIST);
        //Act
        Artist sut = testEntityManager.find(Artist.class,artist.getId());
        //Assert
        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(artist.getName());
        assertThat(sut.getBirth()).isEqualTo(artist.getBirth());
        assertThat(sut.getArtSchool()).isEqualTo(artist.getArtSchool());
        assertThat(sut.getDescription()).isEqualTo(artist.getDescription());
        assertThat(sut.getCountry()).isEqualTo(artist.getCountry());
    }

}
