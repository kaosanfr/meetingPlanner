package com.sandra.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sandra.entity.Planning;
import com.sandra.entity.Salle;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Long> {
	
    @Query(value = "SELECT p FROM Planning p WHERE salle = ?1 and heure = ?2")
    Collection<Planning> findPlanningForSalle(Salle salle, String heure);
	
    @Query(value = "SELECT p FROM Planning p WHERE heure = ?1 and intervention=?2 and p.salle is null")
    Planning findPlanningForHeureAndLibre( String heure, String intervention);

    @Query(value = "SELECT p FROM Planning p WHERE heure = ?1 and intervention=?2")
    Planning findPlanningForSalleANettoyer( String heure, String intervention );
    
}
