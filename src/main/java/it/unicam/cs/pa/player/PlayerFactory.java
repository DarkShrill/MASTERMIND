/**
 * 
 */
package it.unicam.cs.pa.player;

/**
 *  <b> Responsabilità : </b> Definisce un'interfaccia funzionale che ci permetterà di creare i giocatori.
 * 
 * @author edoardo
 *
 */
@FunctionalInterface
public interface PlayerFactory {

	/**
	 * Permette di creare un giocatore
	 * @param name - Nome del giocatore
	 * @param type - Tipologia del giocatore
	 * @return <code>Player</code> - Giocatore
	 */
	Player createPlayer(String name,TypeGamePlayer type);
	
}
