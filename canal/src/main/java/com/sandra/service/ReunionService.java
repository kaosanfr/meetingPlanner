package com.sandra.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandra.constants.Intervention;
import com.sandra.entity.Planning;
import com.sandra.entity.PlanningEquipementAdditionel;
import com.sandra.entity.Reunion;
import com.sandra.entity.Salle;
import com.sandra.entity.TypeReunion;
import com.sandra.exception.EquipementUnavailableException;
import com.sandra.exception.ReservationException;
import com.sandra.repository.PlanningEquipementAdditionalRepository;
import com.sandra.repository.PlanningRepository;
import com.sandra.repository.ReunionRepository;
import com.sandra.repository.SalleRepository;
import com.sandra.repository.TypeReunionRepository;
import com.sandra.util.UtilsHeures;

import lombok.AllArgsConstructor;
import lombok.Data;


@Service
@AllArgsConstructor
@Data

public class ReunionService {
	
	private static final Logger logger = LogManager.getLogger(ReunionService.class);
	
    private final ReunionRepository reunionRepository;
    private final SalleRepository salleRepository;
    private final PlanningRepository planningRepository;    
    private final PlanningEquipementAdditionalRepository equipeRepository;
    private final TypeReunionRepository typeReunionRepository;    
    
	@PersistenceContext
	private EntityManager entityManager;

    
	
	
@Transactional           
    public void assignerSalles() throws Exception {
    	PlanningEquipementAdditionalService plap = new PlanningEquipementAdditionalService(entityManager);
    	
    	List<Reunion> reunionDisponibles = reunionRepository.findAll();
    	
    	// take all the reunions to assign a salle
    	for(Reunion reunion:reunionDisponibles) {
    		
    		logger.debug("Traitement de la reunion " + reunion.getNomReunion() + " à " + reunion.getHeure() + " Type Reunion=" + reunion.getTypeReunion().getNom() + " nombre personnes réunion=" + reunion.getNombrePersonnesConvoques().longValue());
    		
    		// look among the salles which one is available for the reunion
    		Collection<Salle> listeSalles = salleRepository.findSallesPourReunion(reunion.getNombrePersonnesConvoques().longValue());
    		
    		for (Salle salle:listeSalles) {
    			
    			logger.debug("Analyse de matching avec la salle " + salle.getNom());
    			
    			PlanningEquipementAdditionel ecran=null;
    			PlanningEquipementAdditionel pieuvre = null;
    			PlanningEquipementAdditionel webcam = null;
    			PlanningEquipementAdditionel tableau = null;
    			
    			// checks for the planning for the salle
    			Collection<Planning> planningT = planningRepository.findPlanningForSalle(salle, reunion.getHeure());
    	
    			
    			if (planningT == null || planningT.isEmpty()) {
    				// there is no planning available for the salle
    				
    				Planning planning = new Planning();
					planning.setHeure(reunion.getHeure());
					planning.setIntervention(Intervention.LIBRE.getIntervention());
    				
    				
        			logger.debug("Salle libre, capacite à 70%=" + salle.getCapacite70());
    				
    				if (salle.getCapacite70() >= reunion.getNombrePersonnesConvoques().longValue()) {
    					// the salle fits the number of persons
    					
    					logger.debug("Bonne capacité salle ");
    					
    					try {

	    					ecran = plap.getPlanningEcran(reunion, salle);
	    					webcam = plap.getPlanningWebcam(reunion, salle);
	    					pieuvre = plap.getPlanningPieuvre(reunion, salle);
	    					tableau = plap.getPlanningTableau(reunion, salle);    						    					
	    					
	    					String nextHour = UtilsHeures.getNextHour(reunion.getHeure());
	    					
	    					
	    					if (nextHour == null) {
	    						logger.debug("Impossible de faire la réservation pour la réunion, il n'y a pas de créneau pour le nettoyage");
	    						break;
	    					}

	    					Planning planningNettoyage = blockAgendaSalleEquipe(reunion, nextHour, salle, Intervention.NETTOYAGE.getIntervention());
	    					
	    					
	    					if (planningNettoyage == null) {
	    						throw new ReservationException("Il n'y a pas de salle disponible pour le nettoyage");
	    					}
	    					
	    					planning.setIntervention(Intervention.REUNION.getIntervention());
	    					planning.setReunion(reunion);
	    					planning.setSalle(salle);

	    					
	    					planningRepository.save(planning);
	    					
	    					if (ecran != null) {
	    						ecran.setSalle(salle);
	    						equipeRepository.save(ecran);
	    					}
	    					
	    					if (webcam != null) {
	    						webcam.setSalle(salle);
	    						equipeRepository.save(webcam);
	    					}
	    					
	    					if (pieuvre != null) {
	    						pieuvre.setSalle(salle);
	    						equipeRepository.save(pieuvre);
	    					}
	    					
	    					if (tableau != null) {
	    						tableau.setSalle(salle);
	    						equipeRepository.save(tableau);
	    					}
	    					
	    					planningRepository.save(planningNettoyage);
	    					
	    					reunion.setSalle(salle);
	    					reunionRepository.save(reunion);
	    					break;

    					}
    					catch (EquipementUnavailableException e) {
    						logger.debug("Dans cette salle et de manière générale, pas d'equipement disponible prête pour réserver la réunion");
    					}
    					catch (ReservationException r) {
    						logger.debug("La réservation a échoue");
    					}
    					catch (Exception e1) {
    						logger.debug("Probleme technique");
    						throw e1;
    					}
    					
    				}
    				else {
						logger.debug("Capacite de la salle insuffisant pour la reunion");    					
    				}
    			}
    			else {
        			logger.debug("Salle occupée, intervention ");
    			}
    		}
    	}
    }
    
    @Transactional	
	public Planning blockAgendaSalleEquipe( Reunion reunion, String heure, Salle salle, String typeIntervention) throws ReservationException {

		Planning planningForNettoyage = null;
		
		
		
		if (heure != null) {
			
			
			Collection<Planning> salleAlreadyBusy = planningRepository.findPlanningForSalle( salle, heure);
			
			if (salleAlreadyBusy != null && !salleAlreadyBusy.isEmpty()) {
				throw new ReservationException("Salle already busy at that time");
			}
				
			
			Reunion reuNetto = new Reunion();
			reuNetto.setHeure(heure);
			reuNetto.setNombrePersonnesConvoques(0);
			reuNetto.setNomReunion("NETTOYAGE");
			reuNetto.setSalle(salle);
			reuNetto.setTypeReunion(null);

			TypeReunion typeR = typeReunionRepository.fetchTyeReunionByName("CLEAN");
			
			reuNetto.setTypeReunion(typeR);
			
			reunionRepository.save(reuNetto);
			
			
			planningForNettoyage = planningRepository.findPlanningForSalleANettoyer( heure, Intervention.LIBRE.getIntervention());		
			
			if (planningForNettoyage != null) {
				planningForNettoyage.setSalle(salle);
				planningForNettoyage.setIntervention(typeIntervention);
				
				logger.debug("Salle réservé pour nettoyage");
				
			}
			else {
				planningForNettoyage =  new Planning();
				planningForNettoyage.setHeure(heure);
				planningForNettoyage.setIntervention(typeIntervention);
				planningForNettoyage.setSalle(salle);

			}
			
			planningForNettoyage.setReunion(reuNetto);
			
		}
		
		return planningForNettoyage;
	}


}
