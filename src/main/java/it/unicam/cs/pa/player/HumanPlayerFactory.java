/**
 * 
 */
package it.unicam.cs.pa.player;

/**
 * /**
 * <b> Responsabilità : </b> Permette di creare un giocatore di tipo <code>HumanPlayer</code> <br>
 * 
 * 
 * @author edoardo
 *
 */
public class HumanPlayerFactory implements PlayerFactory {

	@Override
	public Player createPlayer(String name,TypeGamePlayer type) {
		return new HumanPlayer(name,type);
	}

}
