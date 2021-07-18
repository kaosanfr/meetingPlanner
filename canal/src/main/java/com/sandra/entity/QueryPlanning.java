package com.sandra.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class QueryPlanning {
	@Id 
	String id;
    
	String nom; 
}
