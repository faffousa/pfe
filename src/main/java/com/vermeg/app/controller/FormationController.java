package com.vermeg.app.controller;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;

import com.vermeg.app.dto.FormationDTO;
import com.vermeg.app.dto.FormationStudentsStatsDto;
import com.vermeg.app.dto.FormationTeacherDTO;
import com.vermeg.app.dto.InscriptionappDTO;
import com.vermeg.app.entity.Formation;
import com.vermeg.app.iserviceRest.IServiceFormation;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.vermeg.app.dto.*;
import com.vermeg.app.entity.*;
import com.vermeg.app.iserviceRest.*;
import com.vermeg.app.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;




@CrossOrigin(origins ="http://localhost:4200/")
@RequestMapping(value = "/api/Formations")
@RestController

public class FormationController {
	@Autowired
	private IServiceFormation iFormation;
	 @Autowired
     private ModelMapper mapper;
	 
	@PostMapping("/{idUserr}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<FormationDTO> createFormation(@RequestParam("formation") String formationDTO  , @PathVariable("idUserr") Long idUserr) throws IOException {

		System.out.println("formationDTO" + formationDTO);
		Formation formation= new ObjectMapper().readValue(formationDTO, Formation.class);
	    FormationDTO responseFormation = mapper.map(formation, FormationDTO.class);

	    responseFormation=iFormation.addformation(responseFormation, idUserr);
		return new ResponseEntity<>(responseFormation, HttpStatus.OK);

	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<FormationDTO> getAllFormations(){
		return iFormation.getList();
	}
	
	@GetMapping("creator/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<FormationDTO> getFormationBuCreator(@PathVariable("id") Long id){
		return iFormation.getFormtionByIdCreator(id);
	}	
	
	@GetMapping("student/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Formation> getFormationsByStudent(@PathVariable(name = "id") Long id){
		return iFormation.getFormationByIdStudent(id);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public FormationDTO getFormationById(@PathVariable(name = "id") Long id){
		return iFormation.getFormationById(id);
	}
	
	
	@GetMapping("coursA/{test}/user/{id}")
	@ResponseStatus(HttpStatus.OK)
	public  List<FormationDTO> getFormationByTest(@PathVariable(name = "test") Boolean test,@PathVariable(name = "id") Long id){
		return iFormation.getFormtionByTest(test, id);
	}
	
	
	
	
	@PutMapping(path = "/{idFormation}")
    public ResponseEntity<FormationDTO> updateFormation(@PathVariable(name = "idFormation") Long idFormation, @RequestBody  @Valid  FormationDTO formation) {
		
		FormationDTO formationResponse = iFormation.updateFormation(idFormation,formation);
		
        return new ResponseEntity<FormationDTO>(formationResponse, HttpStatus.CREATED);
    }
	
	@PutMapping(path = "Quiz/{idFormation}/{idQuiz}")
    public ResponseEntity<FormationDTO> addQuiz(@PathVariable(name = "idFormation") Long idFormation, @PathVariable(name = "idQuiz") String idQuiz) {
		
		FormationDTO formationResponse = iFormation.addQuiz(idFormation,idQuiz);
		
        return new ResponseEntity<FormationDTO>(formationResponse, HttpStatus.CREATED);
    }
	
	
	
	@DeleteMapping(path = "/{idFormation}")
	public void deleteCourse(@PathVariable(name = "idFormation") Long idFormation) {
		iFormation.deleteFormation(idFormation);
		
	}
	
	@GetMapping("/getStatUser/{idUser}")
	@ResponseStatus(HttpStatus.OK)
	public List<InscriptionappDTO> getStats(@PathVariable(name = "idUser") Long idUser){
		return iFormation.getStats(idUser);
	}
	
	@GetMapping("/getFormationByInstructorId/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<FormationTeacherDTO> getFormationByInstructorId(@PathVariable("id") Long id){
		return iFormation.getFormtionByIdInstructor(id);
	}
	
	@GetMapping("/getStatsFormationStudents/{idFormation}")
	@ResponseStatus(HttpStatus.OK)
	public List<FormationStudentsStatsDto> getStatsFormationStudents(@PathVariable(name = "idFormation") Long idFormation){
		return iFormation.getStatsFormationStudents(idFormation);
	}
}
