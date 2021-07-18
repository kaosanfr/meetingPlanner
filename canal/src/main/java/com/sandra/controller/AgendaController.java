package com.sandra.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandra.entity.PlanningEquipementAdditionel;
import com.sandra.entity.Reunion;
import com.sandra.repository.PlanningEquipementAdditionalRepository;
import com.sandra.repository.ReunionRepository;

import lombok.Data;

@RestController
@RequestMapping(path="/agenda",produces="application/json")
@Data
public class AgendaController {
	@Autowired
	private ReunionRepository reunionRepository;

	@Autowired
	private PlanningEquipementAdditionalRepository equipeRepository;
	
	@GetMapping("/reunions")
	public Collection<Reunion> getAgendaofReunions() {

		
		Collection<Reunion> liste = reunionRepository.findReunionsOrdered();
	     
	     return liste;
		
	}
	
	@GetMapping("/planningEquipe")
	public Collection<PlanningEquipementAdditionel> getPlanningEquipeAdditionnel() {

		
		Collection<PlanningEquipementAdditionel> liste = equipeRepository.findAll();
	     
	     return liste;
		
	}
	
}
