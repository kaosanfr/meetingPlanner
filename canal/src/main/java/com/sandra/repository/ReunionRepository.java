package com.sandra.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sandra.entity.Reunion;

@Repository
public interface ReunionRepository extends JpaRepository<Reunion, Long> {

	
	@Query(value="SELECT * FROM Reunion", nativeQuery=true)
	Collection<Reunion> findAvailableReunions();
	
	@Query(value="SELECT * FROM Reunion r order by  substring(r.heure, 1, 2) asc, r.nom_reunion desc", nativeQuery=true)
	Collection<Reunion> findReunionsOrdered();	
	
}
