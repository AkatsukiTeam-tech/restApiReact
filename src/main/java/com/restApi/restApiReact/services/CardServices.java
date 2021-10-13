package com.restApi.restApiReact.services;

import com.restApi.restApiReact.entities.Cards;

import java.util.List;

public interface CardServices {
        List<Cards> getAllCards();
        List<Cards> searchCards(String name);
        Cards addCard(Cards card);
        Cards editCard(Cards card);
        Cards getCard(Long id);
        void deleteCard(Cards card);
}
