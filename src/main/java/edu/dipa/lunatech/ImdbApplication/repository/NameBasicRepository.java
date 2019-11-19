package edu.dipa.lunatech.ImdbApplication.repository;

import edu.dipa.lunatech.ImdbApplication.entity.NameBasic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NameBasicRepository extends JpaRepository<NameBasic, String> {

    Optional<NameBasic> findByPrimaryName(String name);

    /*@Query("select nb.primaryName, nb.knownForTitles from NameBasic nb " +
            "where nb.knownForTitles=(:knownForTitles) ")
    List<NameBasic> findAllByTwoPrimaryNames(String name1, String name2);*/

    List<NameBasic> findByPrimaryNameLike(String pattern);



}
