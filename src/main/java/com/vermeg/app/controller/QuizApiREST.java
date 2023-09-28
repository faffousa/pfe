package com.vermeg.app.controller;

import com.vermeg.app.dto.QuizDTO;
import com.vermeg.app.iserviceRest.IServiceRestQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( "/api/Quiz" )
@RestController
public class QuizApiREST {

    @Autowired
    private IServiceRestQuiz iquiz;



    @PostMapping("/add_quiz")
    public ResponseEntity<QuizDTO> addquiz(@RequestBody QuizDTO quiz) {
        QuizDTO quizresponse= iquiz.addquiz(quiz);
        return new ResponseEntity<>(quizresponse, HttpStatus.CREATED);
    }

    @PutMapping("/update_quiz/{id}")
    public ResponseEntity<QuizDTO> updatequiz(@PathVariable("id") String id, @RequestBody QuizDTO quizdto) {

        QuizDTO quizresponse=  iquiz.updatequiz(id,quizdto);
        return new ResponseEntity<>(quizresponse,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete_quiz/{id}")
    public void delete(@PathVariable("id") String id) {

        iquiz.delete(id);

    }
    @GetMapping("/getall_quiz")
    public List<QuizDTO> getallquiz() {
        return iquiz.getallquiz();

    }
}

