package com.vermeg.app.iserviceRest;

import java.util.List;

import com.vermeg.app.dto.*;
import com.vermeg.app.entity.*;
import com.vermeg.app.dto.SectionDTO;


public interface IServiceSection {
	public SectionDTO addSection(SectionDTO f, long idFormation);
	
	public List<SectionDTO>getList();
	public List<SectionDTO>getSectionByIdFormation(long id);
	public SectionDTO getSectionById(long idSection); 
	public SectionDTO updateSection(long idSection ,SectionDTO requestSection); 
	
	public void deleteSection(long idSection); 

}
