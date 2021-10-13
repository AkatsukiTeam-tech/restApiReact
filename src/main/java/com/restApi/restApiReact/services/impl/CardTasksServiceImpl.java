package com.restApi.restApiReact.services.impl;

import com.restApi.restApiReact.entities.CardTasks;
import com.restApi.restApiReact.repositories.CardTasksRepository;
import com.restApi.restApiReact.services.CardTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardTasksServiceImpl implements CardTasksService {

    @Autowired
    private CardTasksRepository cardTasksRepository;

    @Override
    public List<CardTasks> getAllTasks(Long id) {
        return cardTasksRepository.findAllByCard_Id(id);
    }

    @Override
    public CardTasks addTask(CardTasks cardTask) {
        return cardTasksRepository.save(cardTask);
    }

    @Override
    public CardTasks editTask(CardTasks cardTask) {
        return cardTasksRepository.save(cardTask);
    }

    @Override
    public CardTasks getTask(Long id) {
        Optional<CardTasks> opt = cardTasksRepository.findById(id);
        return opt.isPresent()?opt.get():null;
    }

    @Override
    public void deleteTask(CardTasks cardTask) {
        cardTasksRepository.delete(cardTask);
    }
}
