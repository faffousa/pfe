package com.vermeg.app.controller;

import com.vermeg.app.dto.QuizDTO;
import com.vermeg.app.iserviceRest.IServiceQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( "/quiz" )
@RestController
public class QuizController {

    @Autowired
    private IServiceQuiz iquiz;

    @GetMapping("/get_quizbyid/{id}")
    public QuizDTO showquizbyid(@PathVariable("id") String id) {

        return  iquiz.getquizbyid(id);

    }
    @PostMapping("/getscoreqcu/{id}/{idUser}")
    public float calculatescoreqcu(@PathVariable("id") String id, @RequestBody QuizDTO quizdto,@PathVariable("idUser") Long idUser) {

        return iquiz.calculatescoreqcu(id,quizdto,idUser);
    }

    @PostMapping("/getscoreqcm/{id}/{idUser}")
    public float calculatescoreqcm(@PathVariable("id") String id, @RequestBody QuizDTO quizdto,@PathVariable("idUser") Long idUser) {


        float score=0;
        switch(iquiz.getquizbyid(id).getEvaluationmodel()) {
            case ALL_OR_NOTHING: {
                score+= iquiz.getqcmallornothing(id,quizdto,idUser);

                break;}
            case PARTIAL_CREDIT:{
                score+= iquiz.getqcmpartialcredit(id,quizdto,idUser);
                break;}
            case GUESSING_PENALTY:{
                score+= iquiz.getqcmguessingpenalty(id,quizdto,idUser);
                break;
            }
        }


        return score;
    }
    @PostMapping("/getscoreopenquestion/{id}/{idUser}")
    public float calculatescoreopenquestion(@PathVariable("id") String id, @RequestBody QuizDTO quizdto,@PathVariable("idUser") Long idUser) {
        float score=0;
        switch(iquiz.getquizbyid(id).getEvaluationmodel()) {
            case ALL_OR_NOTHING: {
                score+= iquiz.getopenquestionsallornothing(id,quizdto,idUser);

                break;}
            case PARTIAL_CREDIT:{
                score+= iquiz.getopenquestionspartialcredit(id,quizdto,idUser);
                break;}
            case GUESSING_PENALTY:{
                score+= iquiz.getopenquestionsguessingpenalty(id,quizdto,idUser);
                break;
            }
        }


        return score;}

}

