package com.canal.bean;

import lombok.Data;

@Data
public class Reunion {

	private String nomReunion;
	private TypeReunion typeReunion;
	private long nombrePersonnesConvoques;
	private String heure;
	private Salle salle;
}
