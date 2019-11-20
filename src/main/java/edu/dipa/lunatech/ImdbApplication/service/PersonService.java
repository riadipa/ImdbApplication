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

    public Map<String, String> isPersonTypeCastedByGenre(List<String> listOfGenres) {

        Map<String, Integer> genreCountMap = new HashMap<String, Integer>();
        Map<String, String> resultMap = new HashMap<>();

        for (String genre : listOfGenres) {
            if (genreCountMap.containsKey(genre)) {
                genreCountMap.put(genre, genreCountMap.get(genre) + 1);
            } else {
                genreCountMap.put(genre, 1);
            }
        }
        logger.info("genreCountMap" + genreCountMap);

        Integer total = 0;
        for (Integer value : genreCountMap.values()) {
            total = value + total;
        }
        logger.info("total = " + total);
        for (Map.Entry<String, Integer> entry : genreCountMap.entrySet()) {
            logger.info(entry.getKey() + " = " + entry.getValue());
            double percentage = ((double) entry.getValue() / total) * 100;
            logger.info("percent" + percentage);
            if (percentage >= (50.0 / 100.0) * 100) {
                resultMap.put("isTypeCasted", "true");
                resultMap.put("genre", entry.getKey());
                return resultMap;
            }
        }
        resultMap.put("isTypeCasted", "false");
        return resultMap;
    }

    public List<String> getPersonGenresDetails(String name) {
        Optional<NameBasic> nameBasic = nameRepo.findByPrimaryName(name);
        if (nameBasic.isPresent()) {
            String knownForTitles = nameBasic.get().getKnownForTitles();
            List<String> titles = Arrays.asList(knownForTitles.split("\\s*,\\s*"));

            List<String> listOfGenres = new ArrayList<>();
            for (String tconst : titles) {
                Integer intTconst = Integer.valueOf(tconst);
                Optional<String> genres = titleRepo.findByTconst(intTconst);
                if (genres.isPresent()) {
                    List<String> genreList = Arrays.asList(genres.get().split("\\s*,\\s*"));
                    listOfGenres.addAll(genreList);
                }
            }
            return listOfGenres;
        } else {
            return new ArrayList<>();
        }
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
                logger.info("commonList" + commonList);
            }
        }
        return commonList;
    }

    public List<String> getListOfMoviesOrTvShows(NameBasic nameBasic) {
        if (nameBasic != null) {
            if (nameBasic.getKnownForTitles() != null) {
                String knownForTitles = nameBasic.getKnownForTitles();
                logger.info("knownForTitles" + knownForTitles);
                List<String> titles = Arrays.asList(knownForTitles.split("\\s*,\\s*"));
                logger.info("titles" + titles);
                List<String> listOfMoviesOrTvShows = new ArrayList<>();
                for (String tconst : titles) {
                    Integer intTconst = Integer.valueOf(tconst);
                    Optional<String> shows = titleRepo.findByTconstTitle(intTconst);
                    if (shows.isPresent()) {
                        List<String> showsList = Arrays.asList(shows.get());
                        listOfMoviesOrTvShows.addAll(showsList);
                    }
                }
                logger.info("listOfMoviesOrTvShows" + listOfMoviesOrTvShows);
                return listOfMoviesOrTvShows;
            } else {
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }

    }

    public List<String> getCommonListOfMoviesOrTvShows(List<String> listOfMoviesOrTvShowsForName1,
                                                       List<String> listOfMoviesOrTvShowsForName2) {
        List<String> listOfCommonMoviesOrTvShows = new ArrayList<>();
        for (String value : listOfMoviesOrTvShowsForName1) {
            if (listOfMoviesOrTvShowsForName2.contains(value)) {
                listOfCommonMoviesOrTvShows.add(value);
            }
        }
        logger.info("listOfCommonMoviesOrTvShows" + listOfCommonMoviesOrTvShows);
        return listOfCommonMoviesOrTvShows;

    }

}
