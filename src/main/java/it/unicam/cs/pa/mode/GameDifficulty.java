package it.unicam.cs.pa.mode;

import java.util.List;

/**
 * <b> Responsabilità : </b> Definisce le caratteristiche principali della difficoltà del gioco.
 * 
 * @author edoardo
 *
 */

public interface GameDifficulty {
	
	/**
	 * 	Ritorna il nome del livello di difficoltà
	 * @return <code>String</code> nome
	 */
	public String getName();	
	
	/**
	 * Ritorna il numero di caselle a seconda della difficoltà
	 * @return <code>int</code> Numero caselle
	 */
	public int getSlots();
	
	/**
	 * Ritorna la lista dei colori disponibili
	 * @return lista contenente i colori disponibili
	 */
	public List<String> getAvaiableColors();
	
	/**
	 * Ritorna la lista dei colori disponibili sotto forma di stringa
	 * @return <code>String</code> colori disponibili
	 */
	public String getAvaiableColorsToString();

}
