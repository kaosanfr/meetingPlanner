package com.sandra.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.sandra.entity.Salle;
import com.sandra.repository.SalleRepository;

import lombok.AllArgsConstructor;
import lombok.Data;


@Service
@AllArgsConstructor
@Data

public class SalleService {
	
    private final SalleRepository salleRepository;
	
	
	public Collection<Salle> findAll() {
		return salleRepository.findAll();
	}
  

}
