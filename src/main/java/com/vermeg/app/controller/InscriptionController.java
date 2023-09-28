package com.vermeg.app.controller;



import java.util.List;

import com.vermeg.app.dto.FormationDTO;
import com.vermeg.app.dto.InscriptionDTO;
import com.vermeg.app.iserviceRest.IServiceInscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vermeg.app.dto.*;

import com.vermeg.app.iserviceRest.*;
import com.vermeg.app.auth.entity.User;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@CrossOrigin
@RequestMapping(value = "/api/Inscriptions")
@RestController
public class InscriptionController {
	@Autowired
	private IServiceInscription iInscription;
	@PostMapping("/{id}/Formation/{idFormation}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<InscriptionDTO> createInscription(@RequestBody InscriptionDTO inscription , @PathVariable("id") Long id, @PathVariable("idFormation") Long idFormation){
		inscription=iInscription.addInscription(inscription, id, idFormation);
		return new ResponseEntity<>(inscription, HttpStatus.OK);

	}
	
	@GetMapping("/{idFormation}")
	@ResponseStatus(HttpStatus.CREATED)
	public List<User>  getInscriptionUserr(@PathVariable("idFormation") Long idFormation){
		return iInscription.getListInscription(idFormation);

	}

	@GetMapping("/getAllFormationsByUser/{idUser}")
	@ResponseStatus(HttpStatus.CREATED)
	public List<FormationDTO> getAllFormationsByUser(@PathVariable("idUser") Long idUser)
	
	{
		return iInscription.getAllFormationsByUser(idUser);
		
	}
	
	@GetMapping("/checkFormationByUser/{idFormation}/{idUser}")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean checkFormationByUser(@PathVariable("idFormation") Long idFormation , @PathVariable("idUser") Long idUser)
	
	{
		return iInscription.checkFormationByUser(idFormation , idUser);
		
	}
 
}
