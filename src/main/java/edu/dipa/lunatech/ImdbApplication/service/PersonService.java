package edu.dipa.lunatech.ImdbApplication.service;

import edu.dipa.lunatech.ImdbApplication.entity.NameBasic;
import edu.dipa.lunatech.ImdbApplication.repository.NameBasicRepository;
import edu.dipa.lunatech.ImdbApplication.repository.TitleBasicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonService {

    private final static Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    NameBasicRepository nameRepo;

    @Autowired
    TitleBasicRepository titleRepo;

    /**
     * This method is used when primary name is not unique
     */
    public List<Map<String, String>> isTypeCasted(String name) {
        List<Map<String, String>> resultList = new ArrayList<>();
        List<NameBasic> persons = nameRepo.findAllByPrimaryName(name);
        for (NameBasic nameBasic : persons) {
            resultList.add(isTypeCasted(nameBasic));
        }

        return resultList;
    }

    public Map<String, String> isPersonTypeCasted(String name) {
        Optional<NameBasic> nameBasicOptional = nameRepo.findFirstByPrimaryName(name);
        if (nameBasicOptional.isPresent()) {
            return isTypeCasted(nameBasicOptional.get());
        } else {
            return null;
        }
    }

    private Map<String, String> isTypeCasted(NameBasic nameBasic) {
        List<String> genres = getGenresByNameBasic(nameBasic);
        return isPersonTypeCastedByGenre(genres);
    }

    protected Map<String, String> isPersonTypeCastedByGenre(List<String> listOfGenres) {

        Map<String, Integer> genreCountMap = new HashMap<String, Integer>();
        Map<String, String> resultMap = new HashMap<>();

        for (String genre : listOfGenres) {
            if (genreCountMap.containsKey(genre)) {
                genreCountMap.put(genre, genreCountMap.get(genre) + 1);
            } else {
                genreCountMap.put(genre, 1);
            }
        }
        logger.debug("genreCountMap" + genreCountMap);

        Integer total = 0;
        for (Integer value : genreCountMap.values()) {
            total = value + total;
        }
        logger.debug("total = " + total);
        for (Map.Entry<String, Integer> entry : genreCountMap.entrySet()) {
            logger.debug(entry.getKey() + " = " + entry.getValue());
            double percentage = ((double) entry.getValue() / total) * 100;
            logger.debug("percent" + percentage);
            if (percentage >= (50.0 / 100.0) * 100) {
                resultMap.put("isTypeCasted", "true");
                resultMap.put("genre", entry.getKey());
                return resultMap;
            }
        }
        resultMap.put("isTypeCasted", "false");
        return resultMap;
    }

    protected List<String> getGenresByNameBasic(NameBasic nameBasic) {
        List<String> listOfGenres = new ArrayList<>();

        String knownForTitles = nameBasic.getKnownForTitles();
        List<String> titles = Arrays.asList(knownForTitles.split("\\s*,\\s*"));

        for (String tconst : titles) {
            Integer intTconst = Integer.valueOf(tconst);
            Optional<String> genres = titleRepo.findByTconst(intTconst);
            if (genres.isPresent()) {
                List<String> genreList = Arrays.asList(genres.get().split("\\s*,\\s*"));
                listOfGenres.addAll(genreList);
            }
        }

        return listOfGenres;
    }

    public List<String> getCommonList(String name1, String name2) {
        List<NameBasic> nameList1 = nameRepo.findAllByPrimaryName(name1);
        List<NameBasic> nameList2 = nameRepo.findAllByPrimaryName(name2);
        List<String> commonList = new ArrayList<>();
        for (NameBasic nameBasic1 : nameList1) {
            for (NameBasic nameBasic2 : nameList2) {
                List<String> getListOfMoviesOrTvShowsNameBasic1 = getListOfMoviesOrTvShows(nameBasic1);
                List<String> getListOfMoviesOrTvShowsNameBasic2 = getListOfMoviesOrTvShows(nameBasic2);
                commonList.addAll(getCommonListOfMoviesOrTvShows(getListOfMoviesOrTvShowsNameBasic1,
                        getListOfMoviesOrTvShowsNameBasic2));
                logger.debug("commonList" + commonList);
            }
        }
        return commonList;
    }

    protected List<String> getListOfMoviesOrTvShows(NameBasic nameBasic) {
        if (nameBasic != null) {
            if (nameBasic.getKnownForTitles() != null) {
                String knownForTitles = nameBasic.getKnownForTitles();
                logger.debug("knownForTitles" + knownForTitles);
                List<String> titles = Arrays.asList(knownForTitles.split("\\s*,\\s*"));
                logger.debug("titles" + titles);
                List<String> listOfMoviesOrTvShows = new ArrayList<>();
                for (String tconst : titles) {
                    Integer intTconst = Integer.valueOf(tconst);
                    Optional<String> shows = titleRepo.findByTconstTitle(intTconst);
                    if (shows.isPresent()) {
                        List<String> showsList = Arrays.asList(shows.get());
                        listOfMoviesOrTvShows.addAll(showsList);
                    }
                }

                logger.debug("listOfMoviesOrTvShows" + listOfMoviesOrTvShows);

                return listOfMoviesOrTvShows;
            } else {
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }

    }

    protected List<String> getCommonListOfMoviesOrTvShows(List<String> listOfMoviesOrTvShowsForName1,
                                                          List<String> listOfMoviesOrTvShowsForName2) {
        List<String> listOfCommonMoviesOrTvShows = new ArrayList<>();
        for (String value : listOfMoviesOrTvShowsForName1) {
            if (listOfMoviesOrTvShowsForName2.contains(value)) {
                listOfCommonMoviesOrTvShows.add(value);
            }
        }
        logger.debug("listOfCommonMoviesOrTvShows" + listOfCommonMoviesOrTvShows);
        return listOfCommonMoviesOrTvShows;

    }

}
