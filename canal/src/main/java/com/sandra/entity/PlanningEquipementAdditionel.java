package com.sandra.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "PLANNING_EQUIPE")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString


public class PlanningEquipementAdditionel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(columnDefinition = "BINARY(16)")
    private Long id;
	
	private String nom;
	
    @OneToOne
    @JoinColumn(name = "salle_id")    
    private Salle salle;
    
    private String heure;
}
