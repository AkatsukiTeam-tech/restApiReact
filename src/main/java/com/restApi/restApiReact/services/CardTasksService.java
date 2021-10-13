package com.restApi.restApiReact.services;

import com.restApi.restApiReact.entities.CardTasks;

import java.util.List;

public interface CardTasksService {
    List<CardTasks> getAllTasks(Long id);
    CardTasks addTask(CardTasks cardTask);
    CardTasks editTask(CardTasks cardTask);
    CardTasks getTask(Long id);
    void deleteTask(CardTasks cardTask);
}

