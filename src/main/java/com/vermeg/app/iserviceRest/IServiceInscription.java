package com.vermeg.app.iserviceRest;

import java.util.List;


import com.vermeg.app.auth.entity.User;
import com.vermeg.app.dto.FormationDTO;
import com.vermeg.app.dto.InscriptionDTO;




public interface IServiceInscription {
public InscriptionDTO addInscription(InscriptionDTO f,Long idEtudiant ,Long idFormation);	
 public List<User> getListInscription(Long id);
 public List<FormationDTO> getAllFormationsByUser(Long idUser);
 public boolean  checkFormationByUser(long formationId,Long idUser  );
 
 
 
}
