package com.vermeg.app.repository;

import java.util.List;

import com.vermeg.app.entity.Formation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vermeg.app.dto.*;
import com.vermeg.app.entity.*;





@Repository
public interface FormationRepository extends MongoRepository<Formation, Integer> {
	//this repository contain all operations of mongodb
	public  Formation findByIdFormation(long idFormation);
	public List<Formation> findByIdUserr(Long idCreator);
	
	public List<Formation> findByTestAndIdUserr(Boolean test,Long id);
	
	

}
