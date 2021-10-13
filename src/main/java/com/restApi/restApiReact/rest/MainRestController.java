package com.restApi.restApiReact.rest;

import com.restApi.restApiReact.dto.UserDTO;
import com.restApi.restApiReact.entities.*;
import com.restApi.restApiReact.repositories.RolesRepository;
import com.restApi.restApiReact.services.AnswerServices;
import com.restApi.restApiReact.services.CardServices;
import com.restApi.restApiReact.services.CardTasksService;
import com.restApi.restApiReact.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api")
public class MainRestController {

    @Autowired
    private CardServices cardService;

    @Autowired
    private UserService userService;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private CardTasksService cardTasksService;

    @Autowired
    private AnswerServices answerServices;

    @GetMapping(value = "/allCards")
    public ResponseEntity<?> getAllCards() {
        List<Cards> cards = cardService.getAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping(value = "/searchCards")
    public ResponseEntity<?> getSearchCards(@RequestParam(name = "name") String name) {
        List<Cards> cards = cardService.searchCards(name);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping(value = "/allTasks")
    public ResponseEntity<?> getAllTasks() {
        Users user = getUser();
        List<CardTasks> tasks = cardTasksService.getAllTasks(user.getCard().getId());
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(value = "/ListTasks")
    public ResponseEntity<?> ListTasks(@RequestParam(name = "id") Long id) {
        List<CardTasks> tasks = cardTasksService.getAllTasks(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(value = "/allAnswers")
    public ResponseEntity<?> getAllAnswers(@RequestParam(name = "id") Long taskId) {
        List<Answer> answers = answerServices.getAllAnswers(taskId);
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }


    @PostMapping(value = "/addCard")
    public ResponseEntity<?> addCard(@RequestBody Cards card) {
        cardService.addCard(card);
        return ResponseEntity.ok(card);
    }

    @PostMapping(value = "/addTask")
    public ResponseEntity<?> addTask(@RequestBody CardTasks task) {
        cardTasksService.addTask(task);
        return ResponseEntity.ok(task);
    }

    @PostMapping(value = "/addAnswer")
    public ResponseEntity<?> addAnswer(@RequestBody Answer answer) {

        answerServices.addAnswer(answer);
        return ResponseEntity.ok(answer);

    }

    @GetMapping(value = "/getCard/{id}")
    public ResponseEntity<?> getCard(@PathVariable(name = "id") Long id) {
        Cards card = cardService.getCard(id);
        if (card != null) {
            return ResponseEntity.ok(card);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/getTask/{id}")
    public ResponseEntity<?> getTask(@PathVariable(name = "id") Long id) {
        CardTasks task = cardTasksService.getTask(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/getAnswer/{id}")
    public ResponseEntity<?> getAnswer(@PathVariable(name = "id") Long id) {
        Users user = getUser();
        List<Answer> answers = answerServices.getAllAnswers(id);

        Answer ans = new Answer();
        for (Answer answer:answers
             ) {
            if (answer.getAnsUser().getId() == user.getId()){
                ans = answer;
            }
        }
        if (ans != null) {
            return ResponseEntity.ok(ans);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/getAnswers/{id}")
    public ResponseEntity<?> getAnswers(@PathVariable(name = "id") Long id) {
        Answer answer = answerServices.getAnswer(id);
        if (answer != null) {
            return ResponseEntity.ok(answer);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping(value = "/saveCard")
    public ResponseEntity<?> saveCard(@RequestBody Cards card) {
        cardService.addCard(card);
        return ResponseEntity.ok(card);
    }

    @PutMapping(value = "/saveTask")
    public ResponseEntity<?> saveTask(@RequestBody CardTasks task) {
        cardTasksService.editTask(task);
        return ResponseEntity.ok(task);
    }

    @PutMapping(value = "/saveAnswer")
    public ResponseEntity<?> saveAnswer(@RequestBody Answer answer) {

        Users user = getUser();
        List<Answer> answers = answerServices.getAllAnswers(answer.getAnsTask().getId());

        Answer ans = new Answer();
        for (   Answer a : answers    )
        {
            if (a.getAnsUser().getId() == user.getId()){
                ans = a;
            }

        }
        if (ans != null) {
            answer.setAnsId(ans.getAnsId());
        }

        if (answer.getAnsUser() == null){
            answer.setAnsUser(user);
        }

        answerServices.editAnswer(answer);

        return ResponseEntity.ok(answer);
    }

    @PutMapping(value = "/addMark")
    public ResponseEntity<?> addMark(@RequestBody Answer answer) {

        Answer ans = answerServices.getAnswer(answer.getAnsId());
        if (ans != null){
            ans.setMark(answer.getMark());
            System.out.println(answer.getMark());
            answerServices.editAnswer(ans);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.ok(answer);
    }

    @DeleteMapping(value = "/deleteCard")
    public ResponseEntity<?> deleteCard(@RequestBody Cards card) {
        Cards checkCard = cardService.getCard(card.getId());
        if (checkCard != null) {
            cardService.deleteCard(checkCard);
            return ResponseEntity.ok(checkCard);
        }
        return ResponseEntity.ok(card);
    }

    @DeleteMapping(value = "/deleteTask")
    public ResponseEntity<?> deleteTask(@RequestBody CardTasks task) {
        CardTasks checkTask = cardTasksService.getTask(task.getId());
        if (checkTask != null) {
            cardTasksService.deleteTask(checkTask);
            return ResponseEntity.ok(checkTask);
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping(value = "/deleteAnswer")
    public ResponseEntity<?> deleteAnswer(@RequestBody Answer answer) {
        Answer ans = answerServices.getAnswer(answer.getAnsId());

        if (ans != null) {
            answerServices.deleteAnswer(ans);
            return ResponseEntity.ok(ans);
        }
        return ResponseEntity.ok(ans);
    }


    @PostMapping(value = "/registr")
    public ResponseEntity<?> registr(@RequestBody Users user) {
        System.out.println(user.getUsername());
        Users new_user = userService.getUserByEmail(user.getUsername());
        if (new_user != null) {
            return ResponseEntity.notFound().build();
        } else {

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            List<Roles> roles = new ArrayList<>();
            roles.add(rolesRepository.getOne(3L));
            user.setRoles(roles);
            userService.saveUser(user);
            return ResponseEntity.ok().build();

        }
    }

    @GetMapping(value = "/profile")
    public ResponseEntity<?> profilePage() {
        Users user = getUser();
        return new ResponseEntity<>(new UserDTO(user.getId(), user.getEmail(), user.getFullName(), user.getRoles(), user.getCard()), HttpStatus.OK);
    }

    private Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Users user = (Users) authentication.getPrincipal();
            return user;
        }
        return null;
    }

    @PutMapping(value = "/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody Users user) {
        Users u = userService.getUserByEmail(user.getUsername());
        if (u != null) {
            u.setFullName(user.getFullName());
            userService.saveUser(u);
            return ResponseEntity.ok(u);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody UserDTO user) {
        Users u = userService.getUserByEmail(user.getEmail());
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        if (!bCrypt.matches(user.getPassword(), u.getPassword())) {
            return ResponseEntity.notFound().build();
        } else {
            u.setPassword(bCrypt.encode(user.getNewPassword()));
            userService.saveUser(u);
            return ResponseEntity.ok(u);
        }
    }


}

