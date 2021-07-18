package com.sandra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sandra.constants.Intervention;
import com.sandra.entity.Planning;
import com.sandra.entity.Reunion;
import com.sandra.entity.Salle;
import com.sandra.entity.TypeReunion;
import com.sandra.repository.PlanningEquipementAdditionalRepository;
import com.sandra.repository.PlanningRepository;
import com.sandra.repository.ReunionRepository;
import com.sandra.repository.SalleRepository;
import com.sandra.repository.TypeReunionRepository;
import com.sandra.service.ReunionService;
import com.sandra.util.InsertEquipement;

@SpringBootApplication
public class CanalApplication implements CommandLineRunner {
	
	private Log log = LogFactory.getLog(CanalApplication.class);
	
	
	@Autowired
	TypeReunionRepository typeReunionRepository;
	
	@Autowired
	SalleRepository salleRepository;
		
	@Autowired
	PlanningRepository planningRepository;

	@Autowired
	PlanningEquipementAdditionalRepository equipementAdditionalRepository;
	
	@Autowired
	ReunionRepository reunionRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CanalApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		persistTypeReunions();
		persistSalles();
		//persistPlanning();
		persistPlanningEquipement();
		persistReunion();
		
		log.debug("debut");
		
		ReunionService rs = new ReunionService(reunionRepository, salleRepository, planningRepository, equipementAdditionalRepository, typeReunionRepository, entityManager);
		rs.assignerSalles();
	}

	public void persistReunion() {
		
		TypeReunion trVC = typeReunionRepository.fetchTyeReunionByName("VC");
		
		reunionRepository.save(
				new Reunion( null, "Reunion1", trVC, 8, "09h-10h", null)
				);

		reunionRepository.save(
				new Reunion( null, "Reunion2", trVC, 6, "09h-10h", null)
				);
		
		TypeReunion trRC = typeReunionRepository.fetchTyeReunionByName("RC");
		
		reunionRepository.save(
				new Reunion( null, "Reunion3", trRC, 4, "11h-12h", null)
				);
		
		TypeReunion trRS = typeReunionRepository.fetchTyeReunionByName("RS");
		
		reunionRepository.save(
				new Reunion( null, "Reunion4", trRS, 2, "11h-12h", null)
				);
		
		TypeReunion trSPEC = typeReunionRepository.fetchTyeReunionByName("SPEC");
		
		reunionRepository.save(
				new Reunion( null, "Reunion5", trSPEC, 9, "11h-12h", null)
				);
		
		reunionRepository.save(
				new Reunion( null, "Reunion6", trRC, 7, "09h-10h", null)
				);
		
		reunionRepository.save(
				new Reunion( null, "Reunion7", trVC, 9, "08h-09h", null)
				);
		
		reunionRepository.save(
				new Reunion( null, "Reunion8", trSPEC, 10, "08h-09h", null)
				);
		
		reunionRepository.save(
				new Reunion( null, "Reunion9", trSPEC, 5, "09h-10h", null)
				);
		
		reunionRepository.save(
				new Reunion( null, "Reunion10", trRS, 4, "09h-10h", null)
				);
		
		reunionRepository.save(
				new Reunion( null, "Reunion11", trRC, 8, "09h-10h", null)
				);

		reunionRepository.save(
				new Reunion( null, "Reunion12", trVC, 12, "11h-12h", null)
				);

		reunionRepository.save(
				new Reunion( null, "Reunion13", trSPEC, 5, "11h-12h", null)
				);
		
		reunionRepository.save(
				new Reunion( null, "Reunion14", trVC, 3, "08h-09h", null)
				);
		
		reunionRepository.save(
				new Reunion( null, "Reunion15", trSPEC, 2, "08h-09h", null)
				);

		reunionRepository.save(
				new Reunion( null, "Reunion16", trVC, 12, "08h-09h", null)
				);
		
		reunionRepository.save(
				new Reunion( null, "Reunion17", trVC, 6, "10h-11h", null)
				);
		
		reunionRepository.save(
				new Reunion( null, "Reunion18", trRS, 2, "11h-12h", null)
				);

		reunionRepository.save(
				new Reunion( null, "Reunion19", trRS, 4, "09h-10h", null)
				);

		reunionRepository.save(
				new Reunion( null, "Reunion20", trRC, 7, "09h-10h", null)
				);

	}

	
	public void persistTypeReunions() {
		typeReunionRepository.save(
				new TypeReunion(null, "VC", true, true, true, false)
				);
		
		typeReunionRepository.save(
				new TypeReunion(null, "SPEC", false, false, false, true)
				);

		typeReunionRepository.save(
				new TypeReunion(null, "RS", false, false, false, false)
				);

		typeReunionRepository.save(
				new TypeReunion(null, "RC", true, true, false, true)
				);

		typeReunionRepository.save(
				new TypeReunion(null, "CLEAN", false, false, false, false)
				);
		
	}
	
	public void persistSalles() {
		salleRepository.save(
				new Salle(null, "E1001", 23, Math.round( (23  * 0.7) ), false, false, false, false)
				);

		salleRepository.save(
				new Salle(null, "E1002", 10, Math.round( (10  * 0.7) ), true, false, false, false)
				);

		salleRepository.save(
				new Salle(null, "E1003", 8, Math.round( (8  * 0.7) ), false, true, false, false)
				);
		
		salleRepository.save(
				new Salle(null, "E1004", 4, Math.round( (4  * 0.7) ), false, false, false, true)
				);
		salleRepository.save(
				new Salle(null, "E2001", 4, Math.round( (4  * 0.7) ), false, false, false, false)
				);		

		salleRepository.save(
				new Salle(null, "E2002", 15, Math.round( (15  * 0.7) ), true, false, true, false)
				);		
		salleRepository.save(
				new Salle(null, "E2003", 7, Math.round( (7  * 0.7) ), false, false, false, false)
				);		
		salleRepository.save(
				new Salle(null, "E2004", 9, Math.round( (9  * 0.7) ), false, false, false, true)
				);		
		salleRepository.save(
				new Salle(null, "E3001", 13, Math.round( (13  * 0.7) ), true, true, true, false)
				);		
		salleRepository.save(
				new Salle(null, "E3002", 8, Math.round( (8  * 0.7) ), false, false, false, false)
				);
		salleRepository.save(
				new Salle(null, "E3003", 9, Math.round( (9  * 0.7) ), true, true, false, false)
				);		
		salleRepository.save(
				new Salle(null, "E3004", 4, Math.round( (4  * 0.7) ), false, false, false, false)
				);
	}
	
	public void persistPlanning() {
		planningRepository.save(
				new Planning(null, null, null, "08h-09h", Intervention.LIBRE.getIntervention() )
				);		
		planningRepository.save(
				new Planning(null, null, null, "09h-10h", Intervention.LIBRE.getIntervention() )
				);		
		planningRepository.save(
				new Planning(null, null, null, "10h-11h", Intervention.LIBRE.getIntervention() )
				);		
		planningRepository.save(
				new Planning(null, null, null, "11h-12h", Intervention.LIBRE.getIntervention() )
				);		
		planningRepository.save(
				new Planning(null, null, null, "12h-13h", Intervention.LIBRE.getIntervention() )
				);		
		planningRepository.save(
				new Planning(null, null, null, "13h-14h", Intervention.LIBRE.getIntervention() )
				);		
		planningRepository.save(
				new Planning(null, null, null, "14h-15h", Intervention.LIBRE.getIntervention() )
				);		
		planningRepository.save(
				new Planning(null, null, null, "15h-16h", Intervention.LIBRE.getIntervention() )
				);		
		planningRepository.save(
				new Planning(null, null, null, "16h-17h", Intervention.LIBRE.getIntervention() )
				);		
		planningRepository.save(
				new Planning(null, null, null, "17h-18h", Intervention.LIBRE.getIntervention() )
				);		
		planningRepository.save(
				new Planning(null, null, null, "18h-19h", Intervention.LIBRE.getIntervention() )
				);		
		planningRepository.save(
				new Planning(null, null, null, "19h-20h", Intervention.LIBRE.getIntervention() )
				);		

	}
	
	public void persistPlanningEquipement() {
		InsertEquipement.persistPlanningEquipement(equipementAdditionalRepository);
	}
	
}
