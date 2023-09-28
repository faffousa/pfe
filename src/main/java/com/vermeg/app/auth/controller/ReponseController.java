package com.vermeg.app.auth.controller;


import com.vermeg.app.auth.entity.Question;
import com.vermeg.app.auth.entity.Reponse;
import com.vermeg.app.auth.entity.User;
import com.vermeg.app.auth.repo.QuestionRepository;
import com.vermeg.app.auth.repo.ReponseRepository;
import com.vermeg.app.auth.service.ReponseService;
import com.vermeg.app.auth.service.SequenceGeneratorService;
import com.vermeg.app.entity.Comment;
import com.vermeg.app.repository.CommentRepository;
import com.vermeg.app.repository.PostRepository;
import com.vermeg.app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reponse")
@CrossOrigin(origins = "http://localhost:4200")
public class ReponseController {


    @Autowired
    ReponseService reponseService;

    @PostMapping("/add/{idUser}/{idQuestion}")
    public ResponseEntity<Reponse> addReponse(@RequestBody Reponse reponse, @PathVariable("idUser") long idUser, @PathVariable("idQuestion") long idQuestion) throws Exception {
        Reponse newReponse = reponseService.addReponse(reponse, idUser, idQuestion);
        return new ResponseEntity<>(newReponse, HttpStatus.CREATED);
    }

    @GetMapping("/getReponses/{idQuestion}")
    public ResponseEntity<List<Reponse>> getReponseByQuestion (@PathVariable("idQuestion") long idQuestion) {
        List<Reponse> reponses = reponseService.getReponseByQuestion(idQuestion);
        return new ResponseEntity<>(reponses, HttpStatus.OK);
    }

    @DeleteMapping("/deleteReponse/{idReponse}")
    public ResponseEntity<Reponse>  deleteReponse(@PathVariable("idReponse") long idReponse){
        reponseService.deleteReponse(idReponse);
        return new ResponseEntity<Reponse>(HttpStatus.OK);
    }

    @GetMapping("/getReponseById/{idReponse}")
    public ResponseEntity<Reponse> getReponseById (@PathVariable("idReponse") long idReponse) {
        Reponse reponse = reponseService.getReponseById(idReponse);
        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @PutMapping("/update/{idReponse}")
    public ResponseEntity<Reponse> updateReponse(@PathVariable("idReponse")long idReponse, @RequestBody Reponse newreponse){
        Reponse reponse = reponseService.updateReponse(idReponse, newreponse);
        return new ResponseEntity<Reponse>(reponse, HttpStatus.OK);
    }
    @PostMapping("/like/{idReponse}/{idUser}")
    public ResponseEntity<Reponse> likeReponse(@PathVariable("idReponse") long idReponse, @PathVariable("idUser") long idUser) {
        Reponse reponse = reponseService.likeReponse(idReponse, idUser);
        if (reponse != null) {
            return new ResponseEntity<>(reponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dislike/{idReponse}")
    public ResponseEntity<Reponse> dislikeReponse(@PathVariable("idReponse") long idReponse) {
        Reponse reponse = reponseService.dislikeReponse(idReponse);
        if (reponse != null) {
            return new ResponseEntity<>(reponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{idReponse}")
    public ResponseEntity<Reponse>  deletesReponse(@PathVariable("idReponse") long idReponse){
        reponseService.deletesReponse(idReponse);
        return new ResponseEntity<Reponse>(HttpStatus.OK);
    }


}
