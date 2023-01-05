/**
 * 
 */
package it.unicam.cs.pa.player;

/**
 *
 * <b> Responsabilità : </b> Permette di creare un giocatore di tipo <code>PcPlayer</code> <br>
 * 
 * 
 * @author edoardo
 *
 */
public class PcPlayerFactory implements PlayerFactory{

	@Override
	public Player createPlayer(String name,TypeGamePlayer type) {
		return new PcPlayer(name,type);
	}

}
