package com.vermeg.app.service;

import com.vermeg.app.dto.ProjectDTO;
import com.vermeg.app.entity.Project;
import com.vermeg.app.iserviceRest.IServiceRestProject;
import com.vermeg.app.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService implements IServiceRestProject {


    ModelMapper mapper=new ModelMapper();
    @Autowired
    ProjectRepository projectrepository;


    public ProjectDTO addproject(ProjectDTO projectdto){

        Project project = mapper.map(projectdto, Project.class);
        Project newproject= projectrepository.save(project);

        return  mapper.map(newproject, ProjectDTO.class);
    }

    @Override
    public ProjectDTO updateproject(String id, ProjectDTO projectdto) {
        Project project = mapper.map(projectdto, Project.class);
        Project foundedproject=projectrepository.findById(id).orElseGet(Project::new);

        foundedproject.setTitle(project.getTitle());
        foundedproject.setDescription(project.getDescription());
        foundedproject.setNames(project.getNames());
        foundedproject.setTypes(project.getTypes());
        foundedproject.setDownloadurl(project.getDownloadurl());

        foundedproject.setCorrectionsList(project.getCorrectionsList());
        Project newproject=projectrepository.save(foundedproject);



        return mapper.map(newproject,ProjectDTO.class);
    }

    @Override
    public ProjectDTO getprojectbyid(String id) {
        Project project=projectrepository.findById(id).orElseGet(Project::new);

        return mapper.map(project, ProjectDTO.class);
    }

    @Override
    public void deleteproject(String id) {
        Project foundedproject=projectrepository.findById(id).orElseGet(Project::new);
        projectrepository.delete(foundedproject);
    }

    @Override
    public List<ProjectDTO> getall() {
        List<Project> allp=projectrepository.findAll();
        return allp.stream().map(p->mapper.map(p, ProjectDTO.class)).collect(Collectors.toList());
    }


}

