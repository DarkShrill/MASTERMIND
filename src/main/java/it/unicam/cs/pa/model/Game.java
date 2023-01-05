/**
 * 
 */
package it.unicam.cs.pa.model;

import java.util.ArrayList;

import it.unicam.cs.pa.controller.Controller;
import it.unicam.cs.pa.mode.GameDifficulty;
import it.unicam.cs.pa.player.Player;
import it.unicam.cs.pa.rules.SetRules;
import it.unicam.cs.pa.strategy.StrategyInterface;
import it.unicam.cs.pa.view.ViewInterface;
import it.unicam.cs.pa.designPattern.Observable;

/**
 * <b> Responsabilità : </b> Creare la parte computazionale del nostro gioco.<br> <br>
 * 				-Permette di aggiungere al nostro tavolo da gioco nuove righe inserite dall'utente se rispettano
 * 				  determinati criteri<br><br>
 * 
 * @author edoardo
 *
 */
public class Game extends Observable<ViewInterface>{
	
	/**
	 * Difficoltà del gioco
	 */
	private GameDifficulty mode;
	
	/**
	 * "Tavolo" da gioco
	 */
	private ArrayList<Row> gameBoard;

	/**
	 * Con questa variabile, vogliamo rappresentare la riga da codificare, durante il
	 * 	gioco.
	 */
	private Row mainKey;
	
	/**
	 * Giocatore 1
	 */
	private Player player1;
	
	/**
	 * Giocatore 2
	 */
	private Player player2;
	
	/**
	 * Strategia del giocatore PC
	 */
	private StrategyInterface strategy;
	
	/**
	 *  Controllore del gioco che prende dati in input dalla vista e li trasforma in comandi per il modello
	 */
	private Controller controller;
	

	/**
	 * Costruttore del gioco.
	 */
	public Game(Controller controller) {
		super();
		
		this.gameBoard = new ArrayList<Row>(0);
		
		this.controller 	= controller;
	}
	
	/**
	 * Questo metodo permette di analizzare la riga inserita dall'utente e valutare,
	 * 	se la riga inserita ha al suo interno valori non ammessi. Ad esempio se i colori disponibili sono A,B,C,D e 
	 * 	l'utente inserisce Z,A,B,C questo metodo ritornerà <code>FALSE</code>
	 * @param row - Riga da analizzare
	 * @return boolean  - <code>true</code> se la <code>ROW</code> inserita è una <code>ROW</code> valida, altrimenti <code>FALSE</code>
	 */
	public boolean checkCorrectRow(Row row) {
		for(int c1 = 0; c1 < row.getHoles().size(); c1++) {
			if((!SetRules.getInstance().getAvaiableColors().contains(row.getHoles().get(c1)))||(row.size() != 4)){
				// INSERITO NELLA MAINKEY UN COLORE NON VALIDO
				return false;
			}
		}
		return true;
	}


	
	/**
	 * Analizza la <code>ROW</code> inserita e decide se aggiungerla al tavolo da gioco.
	 * @param actualyRow - <code>ROW</code> da inserire
	 * @param key - <code>ROW</code> da decodificare (chiave)
	 * @return boolean - <code>true</code> se la <code>ROW</code> è stata aggiunta correttamente altrimenti <code>false</code>.
	 */
	public boolean addRow(Row actualyRow, Row key) {
		if((actualyRow.size() != 4)||(this.checkCorrectRow(actualyRow) == false)) { 
			this.controller.getView().print("HAI INSERITO UNA RIGA NON VALIDA.\n");
			return false;
		}
		
		
		actualyRow.check(key);
		this.gameBoard.add(actualyRow);
		this.setChanged();
		this.notifyObservers(this.gameBoard);
		return true;
	}

	/**
	 * Ritorna la <code>Row</code> da decodificare (chiave)
	 * @return <code>Row</code> - chiave da decodificare
	 */
	public Row getMainKey() {
		return this.mainKey;
	}
	
	/**
	 * Ritorna la difficoltà selezionata del gioco
	 * @return <code>GameDifficulty</code> - difficoltà
	 */
	public GameDifficulty getMode() {
		return this.mode;
	}
	
	/**
	 * Ritorna la grandezza del tavolo da gioco
	 * @return <code>int</code> - grandezza tavolo da gioco
	 */
	public int getSizeOfGameBoard() {
		return this.gameBoard.size();
	}
	
	/**
	 * Permette di capire se la chiave è stata risolta o meno.
	 * @return <code>boolean</code> - <code>true</code> se la <code>ROW</code> da decodificare (chiave) è stata decodificata,altrimenti
	 * 				<code>false</code>
	 */
	public boolean isSolved() {
		if(this.gameBoard.size() == 0) {
			return false;
		}
		return this.gameBoard.get(this.gameBoard.size() - 1).getNumberOfCorrectPosition() == 4 ? true : false; 
	}
	
	/**
	 * Ritorna il giocatore 1
	 * @return <code>Player</code> - giocatore 1
	 */
	public Player getPlayer1() {
		return this.player1;
	}
	
	/**
	 * Ritorna il giocatore 2
	 * @return <code>Player</code> - giocatore 2
	 */
	public Player getPlayer2() {
		return this.player2;
	}
	
	/**
	 * Ritorna la strategia
	 * @return <code>StrategyInterface</code> - strategia
	 */
	public StrategyInterface getStrategy() {
		return strategy;
	}
	
	/**
	 * Ritorna il tavolo da gioco
	 * @return tavolo da gioco
	 */
	public ArrayList<Row> getGameBoard() {
		return gameBoard;
	}

	/**
	 * Permette di settare la difficoltà del gioco
	 * @param difficolta - difficoltà del gioco
	 */
	public void setMode(GameDifficulty difficolta) {
		this.mode = difficolta;
	}

	/**
	 * Permette di settare i giocatori.
	 * @param player1 - giocatore 1
	 * @param player2 - giocatore 2
	 */
	public void setPlayer(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	/**
	 * Perette di settare la strategia
	 * @param stra - strategia
	 */
	public void setStrategy(StrategyInterface stra) {
		this.strategy = stra;
	}

	/**
	 * Permette di settare la chiave da decodificare.
	 * @param mainKey - chiave da decodificare
	 */
	public void setMainKey(Row mainKey) {
		this.mainKey = mainKey;
	}
	
}
