package com.sandra.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sandra.entity.Salle;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
	
    @Query(value = "SELECT p FROM Salle p "
    		+ "WHERE capacite70 >= ?1 "
    		+ "ORDER BY capacite70")
    Collection<Salle> findSallesPourReunion( long personnes   );


}
