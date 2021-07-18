package com.sandra.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandra.entity.TypeReunion;
import com.sandra.repository.TypeReunionRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Data

public class TypeReunionService {
	
    private final TypeReunionRepository typeReunionRepository;

	
    @Transactional(readOnly=true)
    public TypeReunion findTypeReunionByName(String name) {
        
        return typeReunionRepository.fetchTyeReunionByName(name);
    }


}
