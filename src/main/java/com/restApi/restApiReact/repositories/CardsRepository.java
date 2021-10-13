package com.restApi.restApiReact.repositories;

import com.restApi.restApiReact.entities.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CardsRepository extends JpaRepository<Cards,Long> {
    List<Cards> findAllByNameIsStartingWith(String name);
}

