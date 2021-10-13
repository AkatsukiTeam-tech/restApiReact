package com.restApi.restApiReact.services.impl;

import com.restApi.restApiReact.entities.Cards;
import com.restApi.restApiReact.repositories.CardsRepository;
import com.restApi.restApiReact.services.CardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardServices {

    @Autowired
    private CardsRepository cardRepository;



    @Override
    public List<Cards> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Cards addCard(Cards card) {
        return cardRepository.save(card);
    }

    @Override
    public Cards editCard(Cards card) {
        return cardRepository.save(card);
    }

    @Override
    public Cards getCard(Long id) {
        Optional<Cards> opt = cardRepository.findById(id);
        return opt.isPresent()?opt.get():null;
    }

    @Override
    public void deleteCard(Cards card) {
        cardRepository.delete(card);
    }

    @Override
    public List<Cards> searchCards(String name) {
        return cardRepository.findAllByNameIsStartingWith(name);
    }
}
