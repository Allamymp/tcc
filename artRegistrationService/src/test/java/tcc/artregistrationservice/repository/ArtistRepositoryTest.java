package tcc.artregistrationservice.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import tcc.artregistrationservice.models.Artist;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tcc.artregistrationservice.common.ArtistConstants.ARTIST;
import static tcc.artregistrationservice.common.ArtistConstants.INVALID_ARTIST;

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
        Artist sut = testEntityManager.find(Artist.class, artist.getId());
        //Assert
        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(artist.getName());
        assertThat(sut.getBirth()).isEqualTo(artist.getBirth());
        assertThat(sut.getArtSchool()).isEqualTo(artist.getArtSchool());
        assertThat(sut.getDescription()).isEqualTo(artist.getDescription());
        assertThat(sut.getCountry()).isEqualTo(artist.getCountry());
    }

    @Test
    public void createArtist_WithInvalidData_ThrowException() {
        //Arrange
        //Assert
        //Act
        assertThatThrownBy(() -> artistRepository.save(INVALID_ARTIST)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createArtist_WithExistingName_ThrowsException() {
        //Arrange
        Artist duplicatedArtist = testEntityManager.persistFlushFind(ARTIST);
        testEntityManager.detach(duplicatedArtist);
        duplicatedArtist.setId(null);
        //Assert-Act
        assertThatThrownBy(() -> artistRepository.save(duplicatedArtist)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getArtist_ByExistingId_ReturnsArtist() {
        //Arrange
        Artist artist = testEntityManager.persistFlushFind(ARTIST);
        //Act
        Optional<Artist> sut = artistRepository.findById(artist.getId());
        //Assert
        assertThat(sut).isNotEmpty();
        assertThat(sut).isNotNull();
        assertThat(sut.get().getId()).isEqualTo(artist.getId());
        assertThat(sut.get().getName()).isEqualTo(artist.getName());
        assertThat(sut.get().getCountry()).isEqualTo(artist.getCountry());
        assertThat(sut.get().getDescription()).isEqualTo(artist.getDescription());
        assertThat(sut.get().getBirth()).isEqualTo(artist.getBirth());
    }

    @Test
    public void getArtist_ByUnexistingId_ReturnsEmpty() {
        //Arrange
        //Act
        Optional<Artist> sut = artistRepository.findById(1L);
        //Assert
        assertThat(sut).isEmpty();
    }

    @Test
    public void getArtist_ByExistingName_ReturnsArtist() {
        //Arrange
        Artist artist = testEntityManager.persistFlushFind(ARTIST);
        //Act
        Optional<Artist> sut = artistRepository.findByNameIgnoreCase(artist.getName());
        //Assert
        assertThat(sut).isNotEmpty();
    }

    @Test
    public void getArtist_ByUnexistingName_ReturnsEmpty() {
        //Arrange
        //Act
        Optional<Artist> sut = artistRepository.findByNameIgnoreCase(ARTIST.getName());
        //Assert
        assertThat(sut).isEmpty();
    }

    @Sql(scripts = "/import_artists.sql")
    @Test
    public void findAllByArtSchool_ReturnsFilteredPlanets(){
        List<Artist> sud = artistRepository.findAllByArtSchoolIgnoreCase("Renascimento");

        assertThat(sud).hasSize(4);
        assertThat(sud.get(0).getName()).isEqualTo("Leonardo da Vinci");
        assertThat(sud.get(1).getName()).isEqualTo("Michelangelo Buonarroti");
        assertThat(sud.get(2).getName()).isEqualTo("Rafael Sanzio");

    }
    @Test
    public void findAllByArtSchool_ReturnsEmpty(){
        List<Artist> sud = artistRepository.findAllByArtSchoolIgnoreCase("Modernismo");

        assertThat(sud).isEmpty();
    }

}
