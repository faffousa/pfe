package com.vermeg.app.iserviceRest;

import java.util.List;

import com.vermeg.app.dto.*;
import com.vermeg.app.entity.*;
import com.vermeg.app.dto.PhaseDTO;


public interface IServicePhase {
	public PhaseDTO addPhase(PhaseDTO f, long idSection);
	
	public List<PhaseDTO>getList();
	public List<PhaseDTO>getPhaseByIdSection(long id);
	public PhaseDTO getPhaseById(long idPhase); 
	public PhaseDTO updatePhase(long idPhase ,PhaseDTO requestPhase); 
	
	public void deletePhase(long idPhase); 

}
