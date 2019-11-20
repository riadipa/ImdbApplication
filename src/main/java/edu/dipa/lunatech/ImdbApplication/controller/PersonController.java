package edu.dipa.lunatech.ImdbApplication.controller;

import edu.dipa.lunatech.ImdbApplication.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping(value = "/person/{name}")
    public ResponseEntity<Map<String, String>> isPersonTypeCasted(@PathVariable("name") String name) {
        List<String> listOfGenres = personService.getPersonGenresDetails(name);
        if (listOfGenres.isEmpty()) {
            return new ResponseEntity<Map<String, String>>(HttpStatus.NOT_FOUND);
        }
        Map<String, String> result = personService.isPersonTypeCastedByGenre(listOfGenres);
        return new ResponseEntity<Map<String, String>>(result, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/person")
    public ResponseEntity<List<String>> getCommonListOfMoviesOrTvShows(@RequestParam("name1") String name1,
                                                                       @RequestParam("name2") String name2){
        List<String> commonListOfMoviesOrTvShows= personService.getCommonList(name1, name2);
        if (commonListOfMoviesOrTvShows.isEmpty()) {
            return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<List<String>>(commonListOfMoviesOrTvShows, HttpStatus.ACCEPTED);
        }
    }
}
