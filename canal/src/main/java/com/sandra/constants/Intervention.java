package com.sandra.constants;

public enum Intervention  {
	
		REUNION("REUNION"), 
	    NETTOYAGE("NETTOYAGE"), 
	    LIBRE("LIBRE");
 
	    private final String intervention;
	 
		Intervention(String type) {
	        this.intervention = type;
	    }
	 
	    public String getIntervention() {
	        return intervention;
	    }
}
