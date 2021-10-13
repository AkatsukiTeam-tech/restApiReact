package com.restApi.restApiReact.repositories;

import com.restApi.restApiReact.entities.CardTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CardTasksRepository extends JpaRepository<CardTasks, Long> {
    List<CardTasks> findAllByCard_Id(Long id);
}
