/**
 * 
 */
package it.unicam.cs.pa.controller;

import java.util.ArrayList;

import it.unicam.cs.pa.view.ViewInterface;

/**
 * <b>Responsabilità:</b> Prendere input dalla view.
 * 
 * @author edoardo
 *
 */
public class Controller {

	/**
	 * MainView del Gioco
	 */
	private ViewInterface view;
	
	/**
	 * Costruttore del controller
	 * 
	 * @param view - Interfaccia Grafica 
	 */
	public Controller(ViewInterface view){
		this.view = view;
	}
	
	/**
	 * Ritorna la View corrente
	 * @return view - Interfaccia Grafica
	 */
	public ViewInterface getView() {
		return this.view;
	}
	
	/**
	 * Prendo i dati dall'utente e restituisco una lista con i dati inseriti dall'utente
	 * @return lista dei dati inseriti dall'utente
	 */
	public ArrayList<String> getDataFromPlayer(){
		return this.view.getDataFromPlayer();
	}
	
	/**
	 * Ritorna il valore selezionato dall'utente per inizializzare le informazioni principali 
	 * 	della partita.
	 * @param maxRange rappresenta il massimo numero che l'utente puo inserire da console.
	 * @return int - numero inserito dall'utente
	 */
	public int getDataFromConsole(int maxRange) {
		return this.view.getDataFromConsole(maxRange);
	}
	
	/**
	 * Ritorna una stringa passata dall'utente.
	 * @return String - stringa inserita dall'utente
	 */
	public String getStringFromConsole() {
		return this.view.getStringFromConsole();
	}
	
}
