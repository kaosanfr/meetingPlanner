package com.sandra.repository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sandra.entity.PlanningEquipementAdditionel;

@Repository
public interface PlanningEquipementAdditionalRepository extends JpaRepository<PlanningEquipementAdditionel, Long> {
	

	@Query(value="SELECT p.id, p.heure, p.nom, p.salle FROM PlanningEquipementAdditionel p "
			+ " where p.heure= ?1" 
			+ " and substring(p.nom, 1, 5)= ?2")
	Collection<PlanningEquipementAdditionel> findAvailableEquipment(String heure, String expression);	

}
