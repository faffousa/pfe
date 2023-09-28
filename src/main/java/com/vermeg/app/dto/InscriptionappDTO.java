package com.vermeg.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InscriptionappDTO {
	
	private Long id ;
	private int nbrEtudiants ;
	private Long idFormation ;
	private String nameFormation;
	
}
