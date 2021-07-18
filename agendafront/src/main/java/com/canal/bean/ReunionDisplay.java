package com.canal.bean;

import lombok.Data;

@Data
public class ReunionDisplay {

	private String nomReunion;
	private TypeReunion typeReunion;
	private long nombrePersonnesConvoques;
	private String heure;
	private Salle salle;
	private String equipementAdditionnel;
}
