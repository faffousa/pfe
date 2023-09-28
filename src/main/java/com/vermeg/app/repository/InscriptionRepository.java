package com.vermeg.app.repository;

import java.util.List;

import com.vermeg.app.entity.Inscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InscriptionRepository extends MongoRepository<Inscription, Integer> {

	public  List<Inscription> findByIdEtudiant(long idStudent ) ;
	public  List<Inscription> findByIdFormation(long idStudent ) ;
	public List<Inscription> findAllByIdEtudiant(long idStudent);
	public boolean existsByIdFormationAndIdEtudiant( long idFormation , long idStudent);
	public int countByIdFormation(long idFormation);

	
	 

}
