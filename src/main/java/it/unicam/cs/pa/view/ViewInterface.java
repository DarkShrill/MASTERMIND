/**
 * 
 */
package it.unicam.cs.pa.view;

import java.util.ArrayList;

import it.unicam.cs.pa.designPattern.Observer;
import it.unicam.cs.pa.model.Game;

/**
 *  <b>Responsabilità : </b> Definisce il contratto che dovrà essere rispettato da qualsiasi tipo di interfaccia grafica che vogliamo implementare. <br>
 * 
 * @author edoardo
 *
 */
public interface ViewInterface extends Observer{

	/**
	 *  Questo metodo permette di ritornare il codice tentato dell'utente
	 * @return una lista con il codice tentato dall'utente
	 */
	public ArrayList<String> getDataFromPlayer();
	
	/**
	 * Ritorna il valore selezionato dall'utente per inizializzare le informazioni principali 
	 * 	della partita.
	 * @param maxRange - rappresenta il massimo numero che l'utente puo inserire da console.
	 * @return <code>int</code> - numero digirato dall'utente
	 */
	public int getDataFromConsole(int maxRange);
	
	/**
	 * Ritorna una stringa passata dall'utente.
	 * @return <code>String</code> - stringa passata dall'utente
	 */
	public String getStringFromConsole();
	
	/**
	 *  Questo metodo permette la rappresentazione dei vari messaggi durante la partita.
	 * @param str - Messaggio
	 */
	public void print(String str);
	
	/**
	 * Messaggio iniziale che descrive all'utente come si svolgerà il gioco.
	 */
	public void info();
	
	/**
	 * Messaggio iniziale che permette l'inizio della partita da parte del Decodificatore.
	 */
	public void startGameMessage();
	
	/**
	 * Permette di mostrare a video qual è la combinazione che doveva essere indovinata.
	 * 		Viene mostrata solo a fine partita, sia se il vincitore è il codemaker sia se il
   	 *		vincitore è il codebreaker.
	 */
	public void printTheSolution(Game game);
}
