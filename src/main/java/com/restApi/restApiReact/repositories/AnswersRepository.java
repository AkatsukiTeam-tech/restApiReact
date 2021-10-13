package com.restApi.restApiReact.repositories;

import com.restApi.restApiReact.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnswersRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByAnsTask_Id(Long id);
}
