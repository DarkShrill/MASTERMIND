package it.unicam.cs.pa.player;
/**
 *
 * <b> Responsabilit√† : </b> Definisce il contratto che dovr√† essere rispettato da qualsiasi tipo di giocatore. <br>
 * 
 * 
 * @author edoardo
 *
 */
public interface Player {
	
	/**
	 * Ritorna il nome del giocatore
	 * @return String - nome giocatore
	 */
	public String getNameOfPlayer();
	
	/**
	 * Ritorna il tipo di giocatore
	 * @return <code>TypePlayer</code> - tipo di giocatore
	 */
	public TypePlayer getTypeOfPlayer();
	
	/**
	 * Ritorna la tipologia di giocatore
	 * @return <code>TypeGamePlayer</code> - tipologia del giocatore.
	 */
	public TypeGamePlayer getTypeGamePlayer();

}
