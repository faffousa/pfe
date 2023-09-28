package com.vermeg.app.repository;

import java.util.List;

import com.vermeg.app.entity.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SectionRepository extends MongoRepository<Section, Integer> {
	
	public List<Section> findByIdformation(long idFormation);
	public Section findByIdSection(long idSection);
	
}
