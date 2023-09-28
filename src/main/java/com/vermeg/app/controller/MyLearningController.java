package com.vermeg.app.controller;

import com.vermeg.app.entity.MyLearning;
import com.vermeg.app.service.MyLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mylearning")
@CrossOrigin(origins = "http://localhost:4200")
public class MyLearningController {
	
	@Autowired
    MyLearningService myLearningService;
	
	@PostMapping("/add/{id}")
    public ResponseEntity<MyLearning> addMyLearning(@PathVariable("id") long id) throws Exception {
		MyLearning myLearning = myLearningService.addMylearning(id);
        return new ResponseEntity<>(myLearning, HttpStatus.CREATED);
    }
	
	@GetMapping("/exists/{id}")
	public ResponseEntity<Boolean> existsMyLearning(@PathVariable("id") long id){
		return new ResponseEntity<Boolean>(myLearningService.existsMyLearningByUser(id), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<MyLearning> updateMyLearning(@PathVariable("id") long id){
		MyLearning myLearning = myLearningService.updateMyLearning(id);
		return new ResponseEntity<MyLearning>(myLearning, HttpStatus.OK);
	}
	
	@GetMapping("/getmylearning/{id}")
	public ResponseEntity<MyLearning> findMyLearningByUser(@PathVariable("id") long id){
		MyLearning myLearning = myLearningService.findMyLearningByUser(id);
		return new ResponseEntity<MyLearning>(myLearning, HttpStatus.ACCEPTED);
	}

}
