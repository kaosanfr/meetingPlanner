package com.sandra.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "TYPEREUNION")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString


public class TypeReunion {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(columnDefinition = "BINARY(16)")
    private Long id;
		
	private String nom;

	private boolean ecran;
	
	private boolean pieuvre;
	
	private boolean webcam;
	
	private boolean tableau;

}
