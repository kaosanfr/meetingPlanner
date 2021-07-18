package com.canal.controller;


import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.canal.bean.PlanningEquipementAdditionel;
import com.canal.bean.Reunion;
import com.canal.bean.ReunionDisplay;
import com.canal.util.Constants;
import com.canal.util.UtilAgenda;

import lombok.Data;

@Controller
@Data
public class ReunionController implements Constants {
	
	@Autowired
	private Environment env;

	@RequestMapping(path = "/show", method = RequestMethod.GET)
	public String addBookView(Model model) {
		Reunion[] liste = null;
		PlanningEquipementAdditionel[] listeEquipe = null;
						
		String urlReunions = env.getProperty(URL_REUNIONS);
		String urlEquipe = env.getProperty(URL_EQUIPE);		
		
		RestTemplate restTemplate = new RestTemplate();		 
		//Using RestTemplate
		 
		// "users"
		ResponseEntity<Reunion[]> responseEntity = restTemplate
		                        .getForEntity(urlReunions, Reunion[].class);	
		
		if (responseEntity.getStatusCode().equals(HttpStatus.OK) ) {
			liste = responseEntity.getBody();
		}
		
		ResponseEntity<PlanningEquipementAdditionel[]> responseEntityEquipementAdditionnel = restTemplate
                .getForEntity(urlEquipe, PlanningEquipementAdditionel[].class);	
		
		if (responseEntityEquipementAdditionnel.getStatusCode().equals(HttpStatus.OK) ) {
			listeEquipe = responseEntityEquipementAdditionnel.getBody();
		}
		
		Collection<ReunionDisplay> listeReunion = UtilAgenda.linkReunionPlanningEquipe(Arrays.asList(liste), Arrays.asList(listeEquipe));
		
		model.addAttribute("reunions", listeReunion);
		return "reunions";
	}

}
