package com.restApi.restApiReact.services.impl;

import com.restApi.restApiReact.entities.Answer;
import com.restApi.restApiReact.repositories.AnswersRepository;
import com.restApi.restApiReact.services.AnswerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerServices {

    @Autowired
    private AnswersRepository answersRepository;

    @Override
    public List<Answer> getAllAnswers(Long id) {
        return answersRepository.findAllByAnsTask_Id(id);
    }

    @Override
    public Answer addAnswer(Answer answer) {
        return answersRepository.save(answer);
    }

    @Override
    public Answer editAnswer(Answer answer) {
        return answersRepository.save(answer);
    }

    @Override
    public Answer getAnswer(Long id) {
        return answersRepository.getOne(id);
    }

    @Override
    public void deleteAnswer(Answer answer) {
        answersRepository.delete(answer);
    }
}
