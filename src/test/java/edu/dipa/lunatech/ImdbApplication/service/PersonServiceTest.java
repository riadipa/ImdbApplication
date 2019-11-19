package edu.dipa.lunatech.ImdbApplication.service;

import edu.dipa.lunatech.ImdbApplication.entity.NameBasic;
import edu.dipa.lunatech.ImdbApplication.entity.TitleBasic;
import edu.dipa.lunatech.ImdbApplication.repository.NameBasicRepository;
import edu.dipa.lunatech.ImdbApplication.repository.TitleBasicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

    @Mock
    NameBasicRepository mockedNameBasicRepository;

    @Mock
    TitleBasicRepository mockedTitleBasicRepository;

    @InjectMocks
    PersonService personService;

    private Optional<NameBasic> person;

    @BeforeEach
    public void setUp(){
        person = createPerson();

        when(mockedNameBasicRepository.findByPrimaryName(person.get().getPrimaryName())).thenReturn(person);
        when(mockedTitleBasicRepository.findByTconst(71877)).thenReturn(Optional.of(createTitle1().getGenres()));
        when(mockedTitleBasicRepository.findByTconst(38355)).thenReturn(Optional.of(createTitle2().getGenres()));
        when(mockedTitleBasicRepository.findByTconst(117057)).thenReturn(Optional.of(createTitle3().getGenres()));
        when(mockedTitleBasicRepository.findByTconst(37382)).thenReturn(Optional.of(createTitle4().getGenres()));
    }

    @Test
    public void getPersonGenresByName() {
        List<String> listOfGenres = personService.getPersonGenresDetails(person.get().getPrimaryName());

        List<String> testGenres = Arrays.asList("Crime", "Drama", "Film-Noir", "Comedy", "Adventure");

        for (String testGenre : testGenres) {
            assertTrue(listOfGenres.contains(testGenre));
        }

        Map<String, Integer> genreCountMap = new HashMap<>();
        for (String genre: listOfGenres) {
            if(genreCountMap.containsKey(genre)){
                genreCountMap.put(genre, genreCountMap.get(genre) + 1);
            }else {
                genreCountMap.put(genre,1);
            }

        }
        for (String testGenre : testGenres) {
            int countForGenre = genreCountMap.get(testGenre);
            if (testGenre.equals("Crime")) {
                assertEquals(4, countForGenre);
            }
        }

    }

    @Test
    public void isPersonTypeCasted() {
        List<String> listOfGenres = personService.getPersonGenresDetails(person.get().getPrimaryName());
        Map<String, String> resultMap= personService.isPersonTypeCastedByGenre(listOfGenres);
        assertEquals("true", resultMap.get("isTypeCasted"));
        assertEquals("Crime", resultMap.get("genre"));
    }


    public Optional<NameBasic> createPerson() {
        NameBasic nameBasic = new NameBasic();
        nameBasic.setNconst(1);
        nameBasic.setPrimaryName("Lauren Bacall");
        nameBasic.setKnownForTitles("0071877,0038355,0117057,0037382");
        return Optional.of(nameBasic);
    }

    public TitleBasic createTitle1() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(71877);
        titleBasic.setGenres("Crime, Drama");
        return titleBasic;
    }

    public TitleBasic createTitle2() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(38355);
        titleBasic.setGenres("Crime,Film-Noir");
        return titleBasic;
    }

    public TitleBasic createTitle3() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(117057);
        titleBasic.setGenres("Comedy,Crime");
        return titleBasic;
    }

    public TitleBasic createTitle4() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(37382);
        titleBasic.setGenres("Adventure,Crime");
        return titleBasic;
    }
}
