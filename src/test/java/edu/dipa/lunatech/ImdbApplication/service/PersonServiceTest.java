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
        person = createPerson1();

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

    @Test
    public void getCommonListOfMoviesOrTvShows(){
        Optional<NameBasic> person1= createPerson1();
        Optional<NameBasic> person2= createPerson2();

        when(mockedNameBasicRepository.findByPrimaryName(person1.get().getPrimaryName())).thenReturn(person1);
        when(mockedTitleBasicRepository.findByTconstTitle(71877)).thenReturn(Optional.of(createTitle1().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(38355)).thenReturn(Optional.of(createTitle2().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(117057)).thenReturn(Optional.of(createTitle3().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(37382)).thenReturn(Optional.of(createTitle4().getOriginalTitle()));

        when(mockedNameBasicRepository.findByPrimaryName(person2.get().getPrimaryName())).thenReturn(person2);
        when(mockedTitleBasicRepository.findByTconstTitle(284521)).thenReturn(Optional.of(createTitle5().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(4719712)).thenReturn(Optional.of(createTitle6().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(50677)).thenReturn(Optional.of(createTitle7().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(166060)).thenReturn(Optional.of(createTitle8().getOriginalTitle()));

        List<String> listOfCommonMoviesOrTvShows= personService.getCommonListOfMoviesOrTvShows
                (person1.get().getPrimaryName(), person2.get().getPrimaryName());
        List<String> testCommonList=Arrays.asList("Murder on the Orient Express","The Mirror Has Two Faces");
        assertEquals(testCommonList, listOfCommonMoviesOrTvShows);
    }

    public Optional<NameBasic> createPerson1() {
        NameBasic nameBasic = new NameBasic();
        nameBasic.setNconst(1);
        nameBasic.setPrimaryName("Lauren Bacall");
        nameBasic.setKnownForTitles("0071877,0038355,0117057,0037382");
        return Optional.of(nameBasic);
    }

    public Optional<NameBasic> createPerson2() {
        NameBasic nameBasic = new NameBasic();
        nameBasic.setNconst(1);
        nameBasic.setPrimaryName("Fred Lake");
        nameBasic.setKnownForTitles("0284521,4719712,0050677,0166060");
        return Optional.of(nameBasic);
    }

    public TitleBasic createTitle1() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(71877);
        titleBasic.setOriginalTitle("Murder on the Orient Express");
        titleBasic.setGenres("Crime, Drama");
        return titleBasic;
    }

    public TitleBasic createTitle2() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(38355);
        titleBasic.setOriginalTitle("The Big Sleep");
        titleBasic.setGenres("Crime,Film-Noir");
        return titleBasic;
    }

    public TitleBasic createTitle3() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(117057);
        titleBasic.setOriginalTitle("The Mirror Has Two Faces");
        titleBasic.setGenres("Comedy,Crime");
        return titleBasic;
    }

    public TitleBasic createTitle4() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(37382);
        titleBasic.setOriginalTitle("To Have and Have Not");
        titleBasic.setGenres("Adventure,Crime");
        return titleBasic;
    }

    public TitleBasic createTitle5() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(284521);
        titleBasic.setOriginalTitle("The Mirror Has Two Faces");
        titleBasic.setGenres("Crime, Drama");
        return titleBasic;
    }

    public TitleBasic createTitle6() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(4719712);
        titleBasic.setOriginalTitle("Murder on the Orient Express");
        titleBasic.setGenres("Crime,Film-Noir");
        return titleBasic;
    }

    public TitleBasic createTitle7() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(50677);
        titleBasic.setOriginalTitle("Man from Tangier");
        titleBasic.setGenres("Comedy,Crime");
        return titleBasic;
    }

    public TitleBasic createTitle8() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(166060);
        titleBasic.setOriginalTitle("Stranger on the Shore");
        titleBasic.setGenres("Adventure,Crime");
        return titleBasic;
    }
}
