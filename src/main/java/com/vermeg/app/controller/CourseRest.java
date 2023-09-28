package com.vermeg.app.controller;

import com.vermeg.app.dto.CourseDTO;
import com.vermeg.app.iserviceRest.IServiceCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping( "/course" )
@RestController
public class CourseRest {
    @Autowired
    private IServiceCourse iCourse;

    @PostMapping(path = "/addCourse")
    public CourseDTO addCourse(@RequestBody @Valid CourseDTO courseDTO) {
        return iCourse.addCourse(courseDTO);
    }


    @GetMapping(path = "/getAllCourses")
    public List<CourseDTO> getAllCourses() {
        return iCourse.getAllCourses();
    }

    @GetMapping(path = "/getById/{id}")
    public CourseDTO getCourseById(@PathVariable(name = "id") String  id) {
        return iCourse.getCourseById(id);
    }

    @PutMapping(path = "/updateCourse/{id}")
    public CourseDTO updateCourse(@PathVariable(name = "id") String  id, @RequestBody  @Valid  CourseDTO courseDTO) {
        return iCourse.putCourseById(id,courseDTO);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteCourse(@PathVariable(name = "id") String  id) {
        iCourse.deleteCourse(id);

    }



}


