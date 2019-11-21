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

    private static final String PERSON_NAME_1 = "Salman Khan";
    private static final String PERSON_NAME_2 = "Kajol";

    @BeforeEach
    public void setUp() {

        /**
         * Creating data for test
         */
        person = createPerson1();

        /**
         * Mocking Data for Person
         */
        when(mockedNameBasicRepository.findFirstByPrimaryName(person.get().getPrimaryName())).thenReturn(person);
        when(mockedTitleBasicRepository.findByTconst(71877)).thenReturn(Optional.of(createTitle1().getGenres()));
        when(mockedTitleBasicRepository.findByTconst(38355)).thenReturn(Optional.of(createTitle2().getGenres()));
        when(mockedTitleBasicRepository.findByTconst(117057)).thenReturn(Optional.of(createTitle3().getGenres()));
        when(mockedTitleBasicRepository.findByTconst(37382)).thenReturn(Optional.of(createTitle4().getGenres()));
    }

    @Test
    public void getPersonGenresByName() {

        /**
         * Creating data for test
         */
        List<String> testGenres = Arrays.asList("Crime", "Drama", "Film-Noir", "Comedy", "Adventure");

        /**
         * Test
         */
        List<String> listOfGenres = personService.getGenresByNameBasic(person.get());

        for (String testGenre : testGenres) {
            assertTrue(listOfGenres.contains(testGenre));
        }

        Map<String, Integer> genreCountMap = new HashMap<>();
        for (String genre : listOfGenres) {
            if (genreCountMap.containsKey(genre)) {
                genreCountMap.put(genre, genreCountMap.get(genre) + 1);
            } else {
                genreCountMap.put(genre, 1);
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
        /**
         * Test
         */
        List<String> listOfGenres = personService.getGenresByNameBasic(person.get());
        Map<String, String> resultMap = personService.isPersonTypeCastedByGenre(listOfGenres);
        assertEquals("true", resultMap.get("isTypeCasted"));
        assertEquals("Crime", resultMap.get("genre"));
    }

    @Test
    public void getCommonList() {

        /**
         * Creating data for test
         */
        List<NameBasic> personList1 = createListOfPersonsWithName1();
        List<NameBasic> personList2 = createListOfPersonsWithName2();

        /**
         * Mocking Data for Person 1
         */
        when(mockedNameBasicRepository.findAllByPrimaryName(PERSON_NAME_1)).thenReturn(personList1);
        when(mockedTitleBasicRepository.findByTconstTitle(4832640)).thenReturn(Optional.of(createTitle5().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(3863552)).thenReturn(Optional.of(createTitle6().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(1620719)).thenReturn(Optional.of(createTitle7().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(2016894)).thenReturn(Optional.of(createTitle8().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(2354223)).thenReturn(Optional.of(createTitle9().getOriginalTitle()));

        /**
         * Mocking Data for Person 2
         */
        when(mockedNameBasicRepository.findAllByPrimaryName(PERSON_NAME_2)).thenReturn(personList2);
        when(mockedTitleBasicRepository.findByTconstTitle(4832640)).thenReturn(Optional.of(createTitle10().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(1620719)).thenReturn(Optional.of(createTitle11().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(1188996)).thenReturn(Optional.of(createTitle12().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(439662)).thenReturn(Optional.of(createTitle13().getOriginalTitle()));
        when(mockedTitleBasicRepository.findByTconstTitle(5661532)).thenReturn(Optional.of(createTitle14().getOriginalTitle()));

        /**
         * Test
         */
        List<String> getListOfMoviesOrTvShowsPerson1 = personService.getListOfMoviesOrTvShows(personList1.get(0));
        List<String> getListOfMoviesOrTvShowsPerson2 = personService.getListOfMoviesOrTvShows(personList2.get(0));

        List<String> listOfCommonMoviesOrTvShows = personService.getCommonListOfMoviesOrTvShows
                (getListOfMoviesOrTvShowsPerson1, getListOfMoviesOrTvShowsPerson2);

        List<String> testCommonList = Arrays.asList("Pyar Kiya to Darna Kya", "Kuchh Kuchh Hota Hai");
        assertEquals(testCommonList, listOfCommonMoviesOrTvShows);
    }

    public List<NameBasic> createListOfPersonsWithName1() {
        NameBasic nameBasic1 = new NameBasic();
        nameBasic1.setNconst(1);
        nameBasic1.setPrimaryName(PERSON_NAME_1);
        nameBasic1.setKnownForTitles("4832640,3863552,1620719,2016894");
        NameBasic nameBasic2 = new NameBasic();
        nameBasic2.setNconst(2);
        nameBasic2.setPrimaryName(PERSON_NAME_1);
        nameBasic2.setKnownForTitles("2354223");
        List<NameBasic> nameList = Arrays.asList(nameBasic1, nameBasic2);
        return nameList;
    }

    public List<NameBasic> createListOfPersonsWithName2() {
        NameBasic nameBasic1 = new NameBasic();
        nameBasic1.setNconst(1);
        nameBasic1.setPrimaryName(PERSON_NAME_2);
        nameBasic1.setKnownForTitles("4832640,1620719,1188996,0439662");
        NameBasic nameBasic2 = new NameBasic();
        nameBasic2.setNconst(2);
        nameBasic2.setPrimaryName(PERSON_NAME_2);
        nameBasic2.setKnownForTitles("5661532");
        List<NameBasic> nameList = Arrays.asList(nameBasic1, nameBasic2);
        return nameList;
    }

    public Optional<NameBasic> createPerson1() {
        NameBasic nameBasic = new NameBasic();
        nameBasic.setNconst(1);
        nameBasic.setPrimaryName("Lauren Bacall");
        nameBasic.setKnownForTitles("0071877,0038355,0117057,0037382");
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
        titleBasic.setTconst(4832640);
        titleBasic.setOriginalTitle("Pyar Kiya to Darna Kya");
        titleBasic.setGenres("Crime, Drama");
        return titleBasic;
    }

    public TitleBasic createTitle6() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(3863552);
        titleBasic.setOriginalTitle("Ek Tha Tiger");
        titleBasic.setGenres("Crime,Film-Noir");
        return titleBasic;
    }

    public TitleBasic createTitle7() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(1620719);
        titleBasic.setOriginalTitle("Kuchh Kuchh Hota Hai");
        titleBasic.setGenres("Comedy,Crime");
        return titleBasic;
    }

    public TitleBasic createTitle8() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(2016894);
        titleBasic.setOriginalTitle("Bajrangi Bhaijan");
        titleBasic.setGenres("Adventure,Crime");
        return titleBasic;
    }

    public TitleBasic createTitle9() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(2354223);
        titleBasic.setOriginalTitle("Dabangg");
        titleBasic.setGenres("Adventure,Crime");
        return titleBasic;
    }

    public TitleBasic createTitle10() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(4832640);
        titleBasic.setOriginalTitle("Pyar Kiya to Darna Kya");
        titleBasic.setGenres("Adventure,Crime");
        return titleBasic;
    }

    public TitleBasic createTitle11() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(1620719);
        titleBasic.setOriginalTitle("Kuchh Kuchh Hota Hai");
        titleBasic.setGenres("Adventure,Crime");
        return titleBasic;
    }

    public TitleBasic createTitle12() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(1188996);
        titleBasic.setOriginalTitle("My Name is Khan");
        titleBasic.setGenres("Adventure,Crime");
        return titleBasic;
    }

    public TitleBasic createTitle13() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(439662);
        titleBasic.setOriginalTitle("Gupt");
        titleBasic.setGenres("Adventure,Crime");
        return titleBasic;
    }

    public TitleBasic createTitle14() {
        TitleBasic titleBasic = new TitleBasic();
        titleBasic.setTconst(5661532);
        titleBasic.setOriginalTitle("Baazigar");
        titleBasic.setGenres("Adventure,Crime");
        return titleBasic;
    }

}
