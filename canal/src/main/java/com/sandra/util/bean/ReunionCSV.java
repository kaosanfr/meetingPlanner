package com.sandra.util.bean;

import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class ReunionCSV {
	
	@CsvBindByPosition(position = 0)
	private String nomReunion;
	
	@CsvBindByPosition(position = 1)
    private String typeReunion;
    
	@CsvBindByPosition(position = 2)
    private int nombrePersonnesConvoques;
    
	@CsvBindByPosition(position = 3)
    private String heure;
    
	@CsvBindByPosition(position = 4)
    private String salle;

}
