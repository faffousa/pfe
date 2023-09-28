package com.vermeg.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MacroskillsDTO {

    private String name;
    private float totalprogress;

    @JsonIgnore
    private List<MicroskillsDTO> microskills;

    public MacroskillsDTO()
    {super();}
    @JsonIgnore
    public List<MicroskillsDTO> getmicroskills() {
        return microskills;
    }

    @JsonProperty
    public void setmicroskills(List<MicroskillsDTO> microskills) {
        this.microskills = microskills;
    }


}
