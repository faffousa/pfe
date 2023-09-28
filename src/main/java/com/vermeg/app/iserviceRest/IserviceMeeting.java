package com.vermeg.app.iserviceRest;

import java.util.List;

import com.vermeg.app.dto.*;
import com.vermeg.app.entity.*;
import com.vermeg.app.dto.MeetingDTO;
import com.vermeg.app.entity.Meeting;


public interface IserviceMeeting {
	public Meeting addMeeting(Meeting m);
	
	public List<MeetingDTO> getMeetingsByFormation(long idFormation);
	
	public List<MeetingDTO> getMeetingsByUser(long idUser);

}
