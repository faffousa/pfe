package com.vermeg.app.iserviceRest;

import com.vermeg.app.dto.MacroskillsDTO;
import com.vermeg.app.dto.MicroskillsDTO;
import com.vermeg.app.dto.SkillsDTO;
import com.vermeg.app.entity.Microskills;
import com.vermeg.app.entity.Type;

import java.util.List;

public interface IServiceSkills {

    public SkillsDTO getskillbyid(String id);
    public List<MacroskillsDTO> getmacroskills(String id, Type type);
    public List<MicroskillsDTO> getmicroskills(String iduser, String name, Type type);
    public List<List<Microskills>> getallmicroskills(String iduser, Type type);
}
