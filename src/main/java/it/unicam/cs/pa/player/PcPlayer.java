/**
 * 
 */
package it.unicam.cs.pa.player;

/**
 * <b> Responsabilità : </b> Definisce un giocatore di tipo PC con le caratteristiche di
 * 							un qualsiasi tipo di giocatore : <br>
 * 	<br><b>*</b> NOME <br>
 *  <br><b>*</b> TIPO DI GIOCATORE (PC / PLAYER) <br>
 *  <br><b>*</b> TIPOLOGIA DI GIOCATORI (CODEMAKER / CODEBREAKER) <br>
 * 
 * @author edoardo
 *
 */
public class PcPlayer implements Player{

	/**
	 * Nome del giocatore
	 */
	private String 					name;
	
	/**
	 * Tipo di giocatore
	 */
	private final TypePlayer 		TYPE_OF_PLAYER = TypePlayer.PC;
	
	/**
	 * Tipologia di giocatore
	 */
	private final TypeGamePlayer 	TYPE_GAME_PLAYER;
	
	@Override
	public String getNameOfPlayer() {
		return this.name;
	}

	@Override
	public TypePlayer getTypeOfPlayer() {
		return TYPE_OF_PLAYER;
	}

	public PcPlayer(String name,TypeGamePlayer type) {
		this.name = name;
		this.TYPE_GAME_PLAYER = type;
	}

	@Override
	public TypeGamePlayer getTypeGamePlayer() {
		return this.TYPE_GAME_PLAYER;
	}

}
