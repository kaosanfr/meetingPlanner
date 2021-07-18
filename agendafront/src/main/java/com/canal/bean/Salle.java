package com.canal.bean;

import lombok.Data;

@Data
public class Salle {


	
	private long id;
	private String nom;
	private int capaciteMax;
	private int capacite70;
	private boolean ecran;
	private boolean pieuvre;
	private boolean webcam;
	private boolean tableau;

}
