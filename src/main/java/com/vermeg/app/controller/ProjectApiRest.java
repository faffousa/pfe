package com.vermeg.app.controller;

import com.vermeg.app.dto.ProjectDTO;
import com.vermeg.app.iserviceRest.IServiceRestProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( "/api/Project" )
@RestController
public class ProjectApiRest {

    @Autowired
    private IServiceRestProject iproject;


    @PostMapping("/add_project")
    public ResponseEntity<ProjectDTO> addproject(@RequestBody ProjectDTO project) {

        ProjectDTO projectresponse= iproject.addproject(project);
        return new ResponseEntity<>(projectresponse, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete_project/{id}")
    public void delete(@PathVariable("id") String id) {

        iproject.deleteproject(id);

    }

    @PutMapping("/update_project/{id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable("id") String id, @RequestBody ProjectDTO projectdto) {

        ProjectDTO projectreponse=  iproject.updateproject(id,projectdto);
        return new ResponseEntity<>(projectreponse,HttpStatus.CREATED);
    }

    @GetMapping("/get_projectbyid/{id}")
    public ProjectDTO showprojectbyid(@PathVariable("id") String id) {

        return  iproject.getprojectbyid(id);

    }
}


