/**
 * 
 */
package it.unicam.cs.pa.controller;

import java.util.ArrayList;

import it.unicam.cs.pa.mode.GameDifficulty;
import it.unicam.cs.pa.model.Game;
import it.unicam.cs.pa.model.Row;
import it.unicam.cs.pa.player.Player;
import it.unicam.cs.pa.player.TypeGamePlayer;
import it.unicam.cs.pa.player.TypePlayer;
import it.unicam.cs.pa.strategy.StrategyInterface;
import it.unicam.cs.pa.view.ViewInterface;

/**
 * <b>Responsabilità : </b> Coordina il turno della partita
 * 
 * @author edoardo
 *
 */
public class Coordinator {

	/**
	 * Array con all'interno i riferimenti dei giocatori della partita
	 */
	private Player[] players;
	
	/**
	 * Turno attuale 
	 */
	private int turno;

	/**
	 * Strategia del giocatore @PC selezionata dall'utente
	 */
	private StrategyInterface strategy;
	
	/**
	 * Difficolta del gioco selezionata dall'utente
	 */
	private GameDifficulty difficulty;
	
	/**
	 * Il nostro gioco (Parte computazionale)
	 */
	private Game game;
	
	/**
	 * La vista
	 */
	private ViewInterface view;
	
	/**
	 * Costruttore del mio coordinatore.
	 * @param game - gioco
	 * @param view - interfaccia grafica
	 */
	public Coordinator(Game game, ViewInterface view) {
		this.game		= game;
		this.players 	= new Player[2];
		this.players[0] = this.game.getPlayer1();
		this.players[1] = this.game.getPlayer2();
		this.turno		= 0;
		this.strategy	= this.game.getStrategy();
		this.difficulty = this.game.getMode();
		this.view		= view;
	}

	/**
	 * Questo metodo permette di eseguire il turno di una partita
	 * @return <code>Boolean</code> - true se è avvenuto con successo il turno successivo, altrimenti false
	 */
	public boolean nextTurn() {
		
		
		//VERIFICO SE HO ALMENO UN GIOCATORE CHE DEVE DECODIFICARE IL CODICE E' DEL TIPO 'PC'
		if((this.players[1].getTypeOfPlayer() == TypePlayer.PC)&&(this.players[1].getTypeGamePlayer() == TypeGamePlayer.CODEBREAKER)) {
			
			Row rowData = this.strategy.getStrategy(this.difficulty,this.game.getMainKey());
			
			if(this.game.addRow(rowData, game.getMainKey())) {
				this.turno++;
				return true;
			}
			
		}else {

			ArrayList<String> inputPlayerCodeBraker = this.view.getDataFromPlayer();
			if(this.game.addRow(new Row(new ArrayList<String>(inputPlayerCodeBraker)),game.getMainKey())){
				this.turno++;
				return true;
			}
			
		}
		
		return false;

	}

	/**
	 * Ritorna il numero del turno attuale
	 * @return <code>int</code> - turno
	 */
	public int getTurno() {
		return this.turno;
	}
	
	
	
}
