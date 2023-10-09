package com.vermeg.app.auth.controller;

import com.vermeg.app.auth.entity.Question;
import com.vermeg.app.auth.entity.Reponse;
import com.vermeg.app.auth.repo.QuestionRepository;
import com.vermeg.app.auth.service.NotificationService;
import com.vermeg.app.auth.service.QuestionService;
import com.vermeg.app.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {
    @Autowired
    QuestionService questionservice;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    NotificationService notificationService;


    @PostMapping("/add/{id}")
    public ResponseEntity<Question> addCart(@RequestBody Question question, @PathVariable("id") long id) throws Exception {
        Question newQuestion = questionservice.addQuestion(question, id);
        notificationService.sendQuestionAddedNotification();
        return new ResponseEntity<>(newQuestion, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions () {
        List<Question> questions = questionservice.getQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/getQuestionById/{idQuestion}")
    public ResponseEntity<Question> getQuestionById (@PathVariable("idQuestion") long idQuestion) {
        Question question = questionservice.getQuestionById(idQuestion);
        return new ResponseEntity<Question>(question, HttpStatus.OK);
    }

    @GetMapping("/questions/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        List<Question> questions = questionservice.getQuestionsByCategory(category);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable("id") long id) throws Exception {
        questionservice.deleteQuestion(id);
        return new ResponseEntity<Question>(HttpStatus.OK);
    }

    @PutMapping("/updatequestion/{id}")
    public ResponseEntity<Question> updatePost(@PathVariable("id") long id, @RequestBody Question newquestion) throws Exception {
        Question updateQuestion = questionservice.updateQuestion(newquestion,id);
        return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
    }

    @PostMapping("/vote/{idQuestion}/{rating}")
    public ResponseEntity<Question> voteForQuestion(@PathVariable long idQuestion, @PathVariable long rating) {
           Question question =  questionservice.voteForQuestion(idQuestion, rating);
        System.out.println(question.getContent());
           return new ResponseEntity<>(question,HttpStatus.OK) ;
    }


    @DeleteMapping("/deletes/{id}")
    public ResponseEntity<Question> deleteQuestions(@PathVariable("id") long id) throws Exception {
        questionservice.deleteQuestions(id);
        return new ResponseEntity<Question>(HttpStatus.OK);
    }


    @GetMapping("/category/statistics/{category}")
    public ResponseEntity<Long> getCategoryStatistics(@PathVariable String category) {
        List<Question> questions = questionRepository.findByCategory(category);
        long count = questions.size();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = questionservice.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @PostMapping("/add-to-favorites/{userId}/{questionId}")
    public ResponseEntity<String> addQuestionToFavorites(
            @PathVariable("userId") Long userId,
            @PathVariable("questionId") Long questionId) {
        questionservice.addQuestionToFavorites(userId, questionId);
        return new ResponseEntity<>("Question added to favorites successfully.", HttpStatus.OK);
    }


    @GetMapping("/favouris/{iduser}")
    public ResponseEntity<List<Question>> getFavoriteQuestions(@PathVariable("iduser") Long iduser) {
        List<Question> favoriteQuestions = questionservice.getFavoriteQuestionsByUserId(iduser);
        return new ResponseEntity<>(favoriteQuestions, HttpStatus.OK);
    }

    @GetMapping("/categoryStatistics")
    public ResponseEntity<List<Question>> getCategoryStatistics() {
        List<Question> stat = questionservice.getCategoryStatistics();
        return new ResponseEntity<>(stat, HttpStatus.OK);
    }

    @DeleteMapping("/remove-from-favorites/{userId}/{questionId}")
    public ResponseEntity<Void> removeQuestionFromFavorites(
            @PathVariable("userId") Long userId,
            @PathVariable("questionId") Long questionId) {
        questionservice.removeQuestionFromFavorites(userId, questionId);
        return ResponseEntity.ok().build(); // Réponse vide avec statut OK (204)
    }














}
