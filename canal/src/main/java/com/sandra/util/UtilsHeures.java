package com.sandra.util;

import java.util.HashMap;

public class UtilsHeures {
	
	public static String getNextHour(String heure) {
		HashMap<String, String> nextHours = new HashMap<String, String>();
		
		nextHours.put("08h-09h", "09h-10h");
		nextHours.put("09h-10h", "10h-11h");
		nextHours.put("10h-11h", "11h-12h");
		nextHours.put("11h-12h", "12h-13h");
		nextHours.put("12h-13h", "13h-14h");
		nextHours.put("13h-14h", "14h-15h");
		nextHours.put("14h-15h", "15h-16h");
		nextHours.put("15h-16h", "16h-17h");
		nextHours.put("16h-17h", "17h-18h");
		nextHours.put("17h-18h", "18h-19h");
		nextHours.put("18h-19h", "19h-20h");
		
		return nextHours.get(heure);
	}

}
