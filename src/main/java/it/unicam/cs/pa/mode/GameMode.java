package it.unicam.cs.pa.mode;

import java.util.List;

import it.unicam.cs.pa.rules.SetRules;

/**
 * <b> Responsabilità : </b> Crea la difficoltà del gioco.
 * 
 * 
 * @author edoardo
 *
 */

public class GameMode implements GameDifficulty{
	
	/**
	 * Nome della modalità di gioco
	 */
	private String 			name;
	
	/**
	 * Numero di colori disponibili
	 */
	private int				numberOfColor;
	
	/**
	 * Numero di tentativi che si posso effettuare per risolvere il codice
	 */
	private int 			numberOfTry;
	
	/**
	 * Lista di tutti i colori disponibili
	 */
	private List<String> 	colors;
	
	/**
	 * Costruttore del GameMode
	 * @param string - nome della modalità di gioco
	 * @param numberOfColor - numero di colori disponibili
	 * @param colors - lista dei colori disponibili
	 * @param numberOfTry - numero di tentativi che possono essere fatti per poter risolvere il codice
	 */
	public GameMode(String string, int numberOfColor, List<String> colors, int numberOfTry) {
		
		this.name = string;
		this.numberOfColor = numberOfColor;
		this.numberOfTry = numberOfTry;
		this.colors = colors;
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSlots() {
		if(SetRules.getInstance() != null) {
			return SetRules.getInstance().getNumberOfSlots();
		}
			
		return this.numberOfTry;
	}

	@Override
	public List<String> getAvaiableColors() {
		if(SetRules.getInstance() != null) {
			return SetRules.getInstance().getAvaiableColors();
		}
		return this.colors;
	}

	@Override
	public String getAvaiableColorsToString() {
		if(SetRules.getInstance() != null) {
			List<String> list =  SetRules.getInstance().getAvaiableColors();
			String str = "";
			for (String stringa : list) {
				str += (stringa) + " , ";
			}
			return str.substring(0, str.length() - 2);
		}
		return colors.toString();
	}

}
