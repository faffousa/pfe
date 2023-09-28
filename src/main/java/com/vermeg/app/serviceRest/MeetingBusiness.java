package com.vermeg.app.serviceRest;


import java.util.List;
import java.util.stream.Collectors;

import com.vermeg.app.iserviceRest.IserviceMeeting;
import com.vermeg.app.dto.MeetingDTO;
import com.vermeg.app.entity.Meeting;
import com.vermeg.app.repository.MeetingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vermeg.app.dto.*;
import com.vermeg.app.entity.*;
import com.vermeg.app.iserviceRest.*;
import com.vermeg.app.repository.*;


@Service
public class MeetingBusiness implements IserviceMeeting {
	@Autowired
	private MeetingRepository meetingRepository;
	   @Autowired
       private ModelMapper mapper;
	   
	@Override
	public Meeting addMeeting(Meeting m) {
		return meetingRepository.save(m);
	}

	@Override
	public List<MeetingDTO> getMeetingsByFormation(long idFormation) {

		
		List<Meeting> meetingList = meetingRepository.findAllByIdFormation(idFormation);
		
		return meetingList.stream().map(elem -> mapper.map(elem, MeetingDTO.class)).collect(Collectors.toList());
		
	}
	
	@Override
	public List<MeetingDTO> getMeetingsByUser(long idUser) {

		
		List<Meeting> meetingList = meetingRepository.findAllByIdInstructor(idUser);
		
		return meetingList.stream().map(elem -> mapper.map(elem, MeetingDTO.class)).collect(Collectors.toList());
		
	}
}
