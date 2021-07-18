package com.sandra.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sandra.entity.PlanningEquipementAdditionel;
import com.sandra.entity.QueryPlanning;
import com.sandra.entity.Reunion;
import com.sandra.entity.Salle;
import com.sandra.exception.EquipementUnavailableException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Data

public class PlanningEquipementAdditionalService {
	
	@PersistenceContext
	private EntityManager entityManager;


	private static final Logger logger = LogManager.getLogger(PlanningEquipementAdditionalService.class);

	
	
	public PlanningEquipementAdditionel giveEquipementReunion(Reunion reunion, String expression) {
		PlanningEquipementAdditionel equipe = null;
		
		List<QueryPlanning> listeEquipement =   entityManager.createNativeQuery(
				"SELECT p.id, p.nom  FROM planning_equipe p "
				+ "LEFT JOIN Salle s on p.salle_id=s.id "
				+ "			 where p.heure= :heure "
				+ "				 and p.salle_id is null "		
				+ "				 and substring(p.nom, 1, 5)= :expression", QueryPlanning.class)
        .setParameter("heure", reunion.getHeure())
        .setParameter("expression", expression)
        .getResultList();
		
		
		if (listeEquipement == null || listeEquipement.isEmpty()) {
			logger.debug("Il n'y a plus de salles disponibles pour la réunion " + reunion.getNomReunion());
		} else {
			//equipe = listeEquipement.iterator().next
			
			equipe = new PlanningEquipementAdditionel();
			
			QueryPlanning retourQuery  = listeEquipement.get(0);
			
			
			equipe.setHeure(reunion.getHeure());
			equipe.setNom(retourQuery.toString());
			equipe.setId(new Long(retourQuery.getId()));
			equipe.setNom(retourQuery.getNom());

		}

		return equipe;

	}
 

	public PlanningEquipementAdditionel giveEcranReunion(Reunion reunion) {
		return giveEquipementReunion(reunion, "Ecran");
	}

	public PlanningEquipementAdditionel giveWebcamReunion(Reunion reunion) {
		return giveEquipementReunion(reunion, "Webca");
	}
	
	public PlanningEquipementAdditionel givePieuvreReunion(Reunion reunion) {
		return giveEquipementReunion(reunion, "Pieuv");
	}
	
	public PlanningEquipementAdditionel giveTableauReunion(Reunion reunion) {
		return giveEquipementReunion(reunion, "Table");
	}
	
	
	public PlanningEquipementAdditionel getPlanningEcran(Reunion reunion, Salle salle) throws EquipementUnavailableException {
    	
		PlanningEquipementAdditionel ecran=null;
		
		
		if (reunion.getTypeReunion().isEcran() ) {
			// the reunion needs an ecran
			
			if (!reunion.getTypeReunion().isEcran() == salle.isEcran()) {
				// the salle does not have an ecran
				
				ecran = this.giveEcranReunion(reunion);
				
				if (ecran == null ) {
					logger.debug("Pas d'écran disponible pour la réunion");
					throw new EquipementUnavailableException("Impossible de trouver un ecran pour la reunion");
				}
				
			}
			else {
				logger.debug("La salle a un écran");
			}
		}
		
		return ecran;
    	
    }

    
	public PlanningEquipementAdditionel getPlanningWebcam(Reunion reunion, Salle salle) throws EquipementUnavailableException {
    	
		PlanningEquipementAdditionel webcam=null;
		

		
		if (reunion.getTypeReunion().isWebcam() ) {
			// the reunion needs an ecran
			
			if (!reunion.getTypeReunion().isWebcam() == salle.isWebcam()) {
				// the salle does not have an ecran
				
				webcam = this.giveWebcamReunion(reunion);
				
				if (webcam == null ) {
					logger.debug("Pas de webcam disponible pour la réunion");
					throw new EquipementUnavailableException("Impossible de trouver un webcam pour la reunion");
				}
								
			}
			else {
				logger.debug("La salle a un webcam");
			}
		}
		
		return webcam;
    	
    }
    
    
	public PlanningEquipementAdditionel getPlanningPieuvre(Reunion reunion, Salle salle) throws EquipementUnavailableException {
    	
		PlanningEquipementAdditionel pieuvre=null;
		
		
		if (reunion.getTypeReunion().isPieuvre() ) {
			// the reunion needs an ecran
			
			if (!reunion.getTypeReunion().isPieuvre() == salle.isPieuvre()) {
				// the salle does not have an ecran
				
				pieuvre = this.givePieuvreReunion(reunion);
				
				if (pieuvre == null ) {
					logger.debug("Pas de pieuvre disponible pour la réunion");
					throw new EquipementUnavailableException("Impossible de trouver une pieuvre pour la reunion");
				}
								
			}
			else {
				logger.debug("La salle a une pieuvre");
			}
		}
		
		return pieuvre;
    	
    }

    
	public PlanningEquipementAdditionel getPlanningTableau(Reunion reunion, Salle salle)  throws EquipementUnavailableException {
    	
		PlanningEquipementAdditionel tableau=null;
		
		
		if (reunion.getTypeReunion().isTableau() ) {
			// the reunion needs an ecran
			
			if (!reunion.getTypeReunion().isTableau() == salle.isTableau()) {
				// the salle does not have an ecran
				
				tableau = this.giveTableauReunion(reunion);
				
				if (tableau == null ) {
					logger.debug("Pas de tableau disponible pour la réunion");
					throw new EquipementUnavailableException("Impossible de trouver un tableau pour la reunion");
				}
				
			}
			else {
				logger.debug("La salle a un tableau");
			}
		}
		
		return tableau;
    	
    }
    
	
}
