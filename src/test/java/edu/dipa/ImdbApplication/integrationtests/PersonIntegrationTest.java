package edu.dipa.ImdbApplication.integrationtests;

import edu.dipa.ImdbApplication.service.PersonService;
import edu.dipa.ImdbApplication.ImdbApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ImdbApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonIntegrationTest {

    @Autowired
    PersonService personService;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void isPersonTypeCasted(){
        String name1= createPerson1().getPrimaryName();

        final String uri= "http://localhost:" + randomServerPort + "/person/"+name1;

        TestRestTemplate restTemplate= new TestRestTemplate();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Person> requestEntity= new HttpEntity<>(null, headers);
        ResponseEntity<PersonTypeCastedValue> result= restTemplate.exchange(uri, HttpMethod.GET,
                requestEntity,PersonTypeCastedValue.class);
        assertEquals(202, result.getStatusCodeValue());

    }

    @Test
    public void retrieveCommonListOfMoviesOrTvShows(){
        String name1= createPerson1().getPrimaryName();
        String name2= createPerson2().getPrimaryName();

        final String uri= "http://localhost:" + randomServerPort + "/person?name1="+ name1+"&name2="+name2;

        TestRestTemplate restTemplate= new TestRestTemplate();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Person> requestEntity= new HttpEntity<>(null, headers);
        ResponseEntity<ListOfCommonMoviesOrTvShows> result= restTemplate.exchange(uri, HttpMethod.GET,
                requestEntity,ListOfCommonMoviesOrTvShows.class);
        assertEquals(202, result.getStatusCodeValue());

    }

    private static class PersonTypeCastedValue extends HashMap<String, String>{

    }

    private static class ListOfCommonMoviesOrTvShows extends ArrayList<String>{

    }

    public Person createPerson1(){
        Person person= new Person();
        person.setNconst(1);
        person.setPrimaryName("Kajol");
        return person;
    }
    public Person createPerson2(){
        Person person= new Person();
        person.setNconst(2);
        person.setPrimaryName("Amitabh Bachchan");
        return person;
    }

    private static class Person{
        private Integer nconst;
        private String primaryName;
        private String knownForTitles;

        public Integer getNconst() {
            return nconst;
        }

        public void setNconst(Integer nconst) {
            this.nconst = nconst;
        }

        public String getPrimaryName() {
            return primaryName;
        }

        public void setPrimaryName(String primaryName) {
            this.primaryName = primaryName;
        }

        public String getKnownForTitles() {
            return knownForTitles;
        }

        public void setKnownForTitles(String knownForTitles) {
            this.knownForTitles = knownForTitles;
        }
    }

    private static class Title{
        private Integer tconst;
        private String genres;
        private String originalTitle;

        public Integer getTconst() {
            return tconst;
        }

        public void setTconst(Integer tconst) {
            this.tconst = tconst;
        }

        public String getGenres() {
            return genres;
        }

        public void setGenres(String genres) {
            this.genres = genres;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }
    }


}
