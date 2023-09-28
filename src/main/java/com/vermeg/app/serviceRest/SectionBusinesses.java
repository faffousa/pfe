package com.vermeg.app.serviceRest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.vermeg.app.iserviceRest.IServiceSection;
import com.vermeg.app.dto.SectionDTO;
import com.vermeg.app.entity.Section;
import com.vermeg.app.repository.SectionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vermeg.app.dto.*;
import com.vermeg.app.entity.*;
import com.vermeg.app.auth.service.SequenceGeneratorService;
import com.vermeg.app.iserviceRest.*;
import com.vermeg.app.repository.*;



@Service
public class SectionBusinesses implements IServiceSection {

          @Autowired
          SectionRepository sectionRepository;
          @Autowired
          private ModelMapper mapper;
          @Autowired
          private  SequenceGeneratorService sequenceGeneratorService;
		
		@Override
		public List<SectionDTO> getList() {
			List<Section> sections = sectionRepository.findAll();
			return sections.stream().map(section -> mapper.map(section, SectionDTO.class))
					.collect(Collectors.toList());
			
		}
		
		@Override
		public SectionDTO getSectionById(long idSection) {
			Section section=sectionRepository.findByIdSection(idSection);
			return mapper.map(section, SectionDTO.class);
			 
			
		}
		@Override
		public SectionDTO updateSection(long idSection, SectionDTO requestSection) {
			  Section section = mapper.map(requestSection, Section.class);
				Section section2=sectionRepository.findByIdSection(idSection);
				
		         section.setIdSection(idSection);
		         section.setIdformation(section2.getIdformation());
		        
		         section.setDate(section2.getDate());
				Section newSection = sectionRepository.save(section);
				return mapper.map(newSection, SectionDTO.class);
						
				 
		}
		@Override
		public void deleteSection(long idSection) {
			Section section=sectionRepository.findByIdSection(idSection);
			
			sectionRepository.delete(section);
			
			
		}
		

		@Override
		public List<SectionDTO> getSectionByIdFormation(long id) {
			List<Section> sections =sectionRepository.findByIdformation(id);
			return sections.stream().map(section -> mapper.map(section, SectionDTO.class))
					.collect(Collectors.toList());
		}

		@Override
		public SectionDTO addSection(SectionDTO f, long idFormation) {
			Section section = mapper.map(f, Section.class);
			 section.setIdSection(sequenceGeneratorService.generateSequence(Section.SEQUENCE_NAME));
			 section.setIdformation(idFormation);
				section.setDate(new Date());
				Section newSection=sectionRepository.save(section);
				return  mapper.map(newSection, SectionDTO.class);
				
		}
		
		
		


}
