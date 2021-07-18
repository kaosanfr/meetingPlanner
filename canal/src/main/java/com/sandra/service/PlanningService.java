package com.sandra.service;

import org.springframework.stereotype.Service;

import com.sandra.entity.Planning;
import com.sandra.entity.Salle;
import com.sandra.repository.PlanningRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Data

public class PlanningService {
	
    private final PlanningRepository planningRepository;
    

	

}
