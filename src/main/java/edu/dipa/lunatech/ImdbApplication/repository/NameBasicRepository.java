package edu.dipa.lunatech.ImdbApplication.repository;

import edu.dipa.lunatech.ImdbApplication.entity.NameBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NameBasicRepository extends JpaRepository<NameBasic, String> {

    Optional<NameBasic> findFirstByPrimaryName(String name);

    List<NameBasic> findAllByPrimaryName(String name);

}
