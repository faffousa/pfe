package com.vermeg.app.controller;




import com.vermeg.app.dto.ReactionDTO;
import com.vermeg.app.entity.Reaction;
import com.vermeg.app.iserviceRest.IServiceReaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.vermeg.app.iserviceRest.*;
import com.vermeg.app.repository.*;

@CrossOrigin(origins ="http://localhost:4200/")
@RequestMapping(value = "/api/Reactions")
@RestController
public class ReactionController {
	 
	 @Autowired
		private IServiceReaction iReaction;
	 
		@PostMapping("/{idUser}/post/{idPost}")
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<ReactionDTO> createReact(@PathVariable("idUser") long idUser, @PathVariable("idPost") long idPost, @RequestBody ReactionDTO f) {
			
		
		    ReactionDTO responseReact = new ReactionDTO();
		    responseReact=iReaction.addreaction( idUser, idPost, f.getReactionType());
			return new ResponseEntity<>(responseReact, HttpStatus.OK);

		}
		
		@PutMapping("/updateReact/{idReact}")
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<ReactionDTO> updateReact(@PathVariable("idReact") long idReact, @RequestBody ReactionDTO f) {
			
		
		    ReactionDTO responseReact = new ReactionDTO();
		    responseReact=iReaction.updateReaction(idReact,f.getReactionType());
			return new ResponseEntity<>(responseReact, HttpStatus.OK);

		}
		
		
		@PostMapping("/{idPost}")
		@ResponseStatus(HttpStatus.OK)
		public int countReactsByTypeAndPost(@RequestBody ReactionDTO f,@PathVariable("idPost") long idPost) {
			
		
		    return iReaction.countReactionsByPostAndType(idPost, f.reactionType);
			
			
		}
		
		@GetMapping("/{idPost}/{idUser}")
		@ResponseStatus(HttpStatus.OK)
		public int getReactionByUserAndPost(@PathVariable("idPost") long idPost,@PathVariable("idUser") long idUser){
			
		
		    return iReaction.getReactionByIdPostAndIdUser(idUser, idPost);
			
			
		}
		
		@DeleteMapping("/deleteReact/{idReact}")
		@ResponseStatus(HttpStatus.OK)
		public void deleteReaction(@PathVariable("idReact") long idReact) {
			
		
		    iReaction.deleteReaction(idReact);
			
		}
		
		@GetMapping("/getReact/{idUser}/{idPost}")
		@ResponseStatus(HttpStatus.OK)
		public Reaction getReaction(@PathVariable("idUser") long idUser, @PathVariable("idPost") long idPost){
			
		
			return iReaction.getReactionToUpdate( idUser, idPost);
			
		}
		
		
		
	 

}
