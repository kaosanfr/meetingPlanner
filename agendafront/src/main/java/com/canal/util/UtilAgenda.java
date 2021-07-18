package com.canal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.canal.bean.PlanningEquipementAdditionel;
import com.canal.bean.Reunion;
import com.canal.bean.ReunionDisplay;

public class UtilAgenda implements Constants {
	public static Collection<ReunionDisplay> linkReunionPlanningEquipe(List<Reunion> liste, List<PlanningEquipementAdditionel> listeEquipe) {
		Collection<ReunionDisplay> listeDisplay = new ArrayList<ReunionDisplay>();

		
		liste.forEach(
			r -> {
				
				ReunionDisplay display = new ReunionDisplay();
				display.setHeure(r.getHeure());
				display.setNombrePersonnesConvoques(r.getNombrePersonnesConvoques());
				display.setNomReunion(r.getNomReunion());
				display.setTypeReunion(r.getTypeReunion());
				display.setSalle(r.getSalle());
			
				List<PlanningEquipementAdditionel> equipeReunion = listeEquipe.stream().filter(equipe -> {
					if (r.getSalle() != null && equipe.getSalle() != null && equipe.getHeure().equals(r.getHeure()) &&
						equipe.getSalle().getId() == r.getSalle().getId()) {
						return true;
					}
					else {
						return false;
					}
				}).collect(Collectors.toList());
				
				StringBuffer sb = new StringBuffer();
				boolean ecranAdded = equipeReunion.stream().filter(e -> (e.getNom().indexOf(ECRAN) != -1 ? true: false) ).findAny()
                .map(v -> true)
                .orElse(false);

				if (ecranAdded) {  
					sb.append("ecran").append(" ") ;
				}
				
				boolean pieuvreAdded = equipeReunion.stream().filter(e -> (e.getNom().indexOf(PIEUVRE) != -1 ? true: false) ).findAny()
		                .map(v -> true)
		                .orElse(false);
				if (pieuvreAdded) {
					sb.append("pieuvre").append(" ");
				}
				
				boolean webcamAdded = equipeReunion.stream().filter(e -> (e.getNom().indexOf(WEBCAM) != -1 ? true: false) ).findAny()
		                .map(v -> true)
		                .orElse(false);
				if (webcamAdded) {
					sb.append("webcam").append(" ");
				}
				
				boolean tableauAdded = equipeReunion.stream().filter(e -> (e.getNom().indexOf(TABLEAU) != -1 ? true: false) ).findAny()
		                .map(v -> true)
		                .orElse(false);
				if (tableauAdded) {
					sb.append("tableau").append(" ");
				}
				
				display.setEquipementAdditionnel(sb.toString());
				
				listeDisplay.add(display);
			}
		);

		return listeDisplay;

	}

}
