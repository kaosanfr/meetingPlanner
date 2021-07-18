package com.sandra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sandra.entity.TypeReunion;

@Repository
public interface TypeReunionRepository extends JpaRepository<TypeReunion, Long> {

    @Query(value = "SELECT p FROM TypeReunion p WHERE nom = ?1")
    TypeReunion fetchTyeReunionByName(String name);

}
