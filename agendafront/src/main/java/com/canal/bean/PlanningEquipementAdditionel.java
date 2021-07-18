package com.canal.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString


public class PlanningEquipementAdditionel {

    private Long id;
	
	private String nom;
	
    private Salle salle;
    
    private String heure;
}
