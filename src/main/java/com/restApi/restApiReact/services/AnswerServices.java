package com.restApi.restApiReact.services;

import com.restApi.restApiReact.entities.Answer;


import java.util.List;

public interface AnswerServices {
    List<Answer> getAllAnswers(Long id);
    Answer addAnswer(Answer Answer);
    Answer editAnswer(Answer Answer);
    Answer getAnswer(Long id);
    void deleteAnswer(Answer Answer);
}
