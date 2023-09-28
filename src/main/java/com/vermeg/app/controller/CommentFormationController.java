package com.vermeg.app.controller;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.vermeg.app.dto.CommentDTO;
import com.vermeg.app.entity.Comment;
import com.vermeg.app.iserviceRest.IServiceComment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vermeg.app.dto.*;
import com.vermeg.app.entity.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;








@CrossOrigin(origins ="http://localhost:4200/")
@RequestMapping(value = "/api/Comments")
@RestController

public class CommentFormationController {
	@Autowired
	private IServiceComment iComment;
	
	 @Autowired
     private ModelMapper mapper;

	
	@PostMapping("/{id}/formation/{idFormation}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentDTO> createComment(@RequestBody Comment comment , @PathVariable("id") long id, @PathVariable("idFormation") long idFormation){
		
	
	    CommentDTO responseComment = mapper.map(comment, CommentDTO.class);
		responseComment=iComment.addcomment(responseComment, id,idFormation);
		return new ResponseEntity<>(responseComment, HttpStatus.OK);

	}
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentDTO> updateComment(@RequestBody Comment comment ,@PathVariable("id") long id){
		
		
	
	    CommentDTO responseComment = mapper.map(comment, CommentDTO.class);
		responseComment=iComment.updateComment(id, responseComment);
		return new ResponseEntity<>(responseComment, HttpStatus.OK);

	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDTO> getAllComments(){
		
		List<CommentDTO>p= iComment.getList();
		
		Collections.sort(p, new Comparator<CommentDTO>() {


			@Override
			public int compare(CommentDTO o1,CommentDTO o2) {
 
			return	0;
			}
		});
		
		return p;
	}
	
	@GetMapping("post/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDTO> getCommentByPost(@PathVariable("id") long id){
		return iComment.getCommentByIdPost(id);
	}	
	
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CommentDTO getCommentById(@PathVariable(name = "id") Long id){
		return iComment.getCommentById(id);
	}
	
	
	
	
	
	@DeleteMapping(path = "/{idComment}")
	public void deleteCourse(@PathVariable(name = "idComment") Long idComment) {
		iComment.deleteComment(idComment);
		
	}
	
	@GetMapping(path="/getComment/{idComment}")
	public CommentDTO getComment(@PathVariable(name = "idComment") Long idComment)
	{
		return iComment.getCommentById(idComment);
	}
}
