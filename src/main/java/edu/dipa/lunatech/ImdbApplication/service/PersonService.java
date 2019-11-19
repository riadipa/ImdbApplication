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
                resultMap.put("isTypeCasted","true");
                resultMap.put("genre",entry.getKey());
                return resultMap;
            }
        }
        resultMap.put("isTypeCasted","false");
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

}
