package com.vermeg.app.entity;



import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection="Inscription")

public class Inscription {
	
	
	private Long id ;
	private Long idEtudiant ;
	private Long idFormation ;
	private String   name;
	public Inscription() {
		super();
	}
	public Inscription(Long id, Long idEtudiant, Long idFormation, String name) {
		super();
		this.id = id;
		this.idEtudiant = idEtudiant;
		this.idFormation = idFormation;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdEtudiant() {
		return idEtudiant;
	}
	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
	public Long getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(Long idFormation) {
		this.idFormation = idFormation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Inscription [id=" + id + ", idEtudiant=" + idEtudiant + ", idFormation=" + idFormation + ", name="
				+ name + "]";
	}
	

	
}
