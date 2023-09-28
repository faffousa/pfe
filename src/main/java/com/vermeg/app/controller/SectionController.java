package com.vermeg.app.controller;

import java.io.IOException;
import java.util.List;

import com.vermeg.app.dto.SectionDTO;
import com.vermeg.app.entity.Section;
import com.vermeg.app.iserviceRest.IServiceSection;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.vermeg.app.dto.*;
import com.vermeg.app.entity.*;
import com.vermeg.app.iserviceRest.*;
import com.vermeg.app.repository.*;

import com.fasterxml.jackson.databind.ObjectMapper;




@CrossOrigin(origins ="http://localhost:4200/")
@RequestMapping(value = "/api/Sections")
@RestController

public class SectionController {
	@Autowired
	private IServiceSection iSection;

	 @Autowired
     private ModelMapper mapper;

	
	@PostMapping("/{idFormation}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<SectionDTO> createSection(@RequestBody SectionDTO sectionDto , @PathVariable("idFormation") long idFormation) {
		
	    SectionDTO responseSection =iSection.addSection(sectionDto,idFormation);
		return new ResponseEntity<>(responseSection, HttpStatus.OK);

	}
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<SectionDTO> updateSection(@RequestParam("imageFile") MultipartFile file,@RequestParam("user") String postDto ,@PathVariable("id") long id) throws IOException{
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		Section post= new ObjectMapper().readValue(postDto, Section.class);
		
	    SectionDTO responseSection = mapper.map(post, SectionDTO.class);
		responseSection=iSection.updateSection(id, responseSection);
		return new ResponseEntity<>(responseSection, HttpStatus.OK);

	}
	@PutMapping("/updateName/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<SectionDTO> updateSectionName(@PathVariable("id") long id,@RequestBody SectionDTO sectionDto) {
		
		SectionDTO responseSection=iSection.updateSection(id, sectionDto);
		return new ResponseEntity<>(responseSection, HttpStatus.OK);

	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<SectionDTO> getAllSections(){
		
	    return  iSection.getList();
		
	}
	
	@GetMapping("formation/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<SectionDTO> getSectionByFormation(@PathVariable("id") long id){
		
	return  iSection.getSectionByIdFormation(id);
		
		
	}	
	
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SectionDTO getSectionById(@PathVariable(name = "id") Long id){
		return iSection.getSectionById(id);
	}
	
	
	
	
	
	@DeleteMapping(path = "/{idSection}")
	public void deleteCourse(@PathVariable(name = "idSection") Long idSection) {
		iSection.deleteSection(idSection);
		
	}
}
