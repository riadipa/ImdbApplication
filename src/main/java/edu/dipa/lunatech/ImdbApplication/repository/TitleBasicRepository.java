package edu.dipa.lunatech.ImdbApplication.repository;

import edu.dipa.lunatech.ImdbApplication.entity.TitleBasic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleBasicRepository extends JpaRepository<TitleBasic, Integer> {

    @Query("select tb.genres from TitleBasic tb where tb.tconst =(:tconst)")
    Optional<String> findByTconst(@Param("tconst") Integer tconst);

    @Query("select tb.originalTitle from TitleBasic tb where tb.tconst =(:tconst)")
    Optional<String> findByTconstTitle(@Param("tconst") Integer tconst);


}
