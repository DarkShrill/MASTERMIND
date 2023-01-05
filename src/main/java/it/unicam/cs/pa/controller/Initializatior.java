/**
 * 
 */
package it.unicam.cs.pa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.unicam.cs.pa.exception.BadFileException;
import it.unicam.cs.pa.mode.GameDifficulty;
import it.unicam.cs.pa.model.Game;
import it.unicam.cs.pa.model.Row;
import it.unicam.cs.pa.player.Player;
import it.unicam.cs.pa.player.PlayerArchive;
import it.unicam.cs.pa.player.TypeGamePlayer;
import it.unicam.cs.pa.player.TypePlayer;
import it.unicam.cs.pa.rules.SetRules;
import it.unicam.cs.pa.strategy.StrategyArchive;
import it.unicam.cs.pa.strategy.StrategyInterface;

/**
 * <b> Responsabilità : </b> Inizializzare la partita con tutte le informazioni definite dall'utente
 * 
 * @author edoardo
 *
 */
public class Initializatior {

	/**
	 * Giocatore 1
	 */
	private Player player1;
	
	/**
	 * Giocatore 2
	 */
	private Player player2;
	
	/**
	 * Difficoltà della partita
	 */
	private GameDifficulty difficolta;
	
	/**
	 * Controller che ci permette la comunicazione con l'utente attraverso la view.
	 */
	private Controller IOComunication;
	
	/**
	 * Con questa variabile, vogliamo rappresentare la riga da decodificare, durante il
	 * 	gioco.
	 */
	private Row mainKey;
	
	/**
	 * Strategia del giocatore PC
	 */
	private StrategyInterface strategy;
	
	/**
	 * Gioco (parte computazionale)
	 */
	private Game game;
	
	/**
	 * Costruttore dell'inizializzatore del gioco.
	 * @param control - controller che permette la comunicazione con l'utente attraverso la view.
	 * @param game - Gioco
	 */
	public Initializatior(Controller control, Game game) {
		this.IOComunication = control;
		this.game = game;
		this.getPlayer();
		this.setDifficulty();
		this.createGame();
		
		game.setMode(this.difficolta);
		game.setPlayer(this.player1,this.player2);
		if(this.strategy != null)
			game.setStrategy(this.strategy);
		
	}
	
	/**
	 * Questo metodo ci permette di creare la partita definendo chi è il <code>CODEMAKER</code> e chi il <code>CODEBREAKER</code> a seconda della
	 * 		scelta che è stata fatta dall'utente.
	 */
	private void createGame() {

		if((this.player1.getTypeOfPlayer() == TypePlayer.PC)&&(this.player2.getTypeOfPlayer() == TypePlayer.PC)) {
			// CREO GIOCO CON HUMAN vs HUMAN
			PcVsPc();
		}else if((this.player1.getTypeOfPlayer() == TypePlayer.PLAYER)&&(this.player2.getTypeOfPlayer() == TypePlayer.PLAYER)) {
			// CREO GIOCO CON HUMAN vs HUMAN
			humanVsHuman();
		}else if(((this.player1.getTypeOfPlayer() == TypePlayer.PC)&&(this.player2.getTypeOfPlayer() == TypePlayer.PLAYER))||((this.player2.getTypeOfPlayer() == TypePlayer.PC)&&(this.player1.getTypeOfPlayer() == TypePlayer.PLAYER))) {
			// CREO GIOCO CON PC vs HUMAN
			pcVsHuman();
		}
	}
	
	/**
	 * Questo metodo permette di creare il codice da decodificare al PC e sarà sempre il PC a decodificarlo.  
	 */
	private void PcVsPc() {
		ArrayList<String> keyColors = new ArrayList<String>();
		Random random = new Random();
		//PC ---> CODEMAKER
		for(int i = 0; i < 4; i++){	
			keyColors.add((this.difficolta.getAvaiableColors().get(random.nextInt(this.difficolta.getAvaiableColors().size()))).toString());
		}
		this.mainKey = new Row(keyColors);
	}
	
	/**
	 * Questo metodo permette di creare il codice da decodificare o al Pc o all'Human, a seconda di quello che l'utente ha scelto.  
	 */
	private void pcVsHuman() {
		ArrayList<String> keyColors = new ArrayList<String>();
		Random random = new Random();
		
		if(this.player1.getTypeOfPlayer() == TypePlayer.PC) {
			//PC ---> CODEMAKER
			for(int i = 0; i < 4; i++){	
				keyColors.add((this.difficolta.getAvaiableColors().get(random.nextInt(this.difficolta.getAvaiableColors().size()))).toString());
			}
			this.mainKey = new Row(keyColors);
			
		}else {
			//PC ---> CODEBREAKER
			this.IOComunication.getView().print("INSERISCI LA SEQUENZA DI COLORI CHE DOVRA' ESSERE CODIFICATA. \nQUESTI SONO I COLORI DISPONIBILI SU CUI POTRAI SCEGLIERE: \n" + this.difficolta.getAvaiableColorsToString()+"\n");
			this.setMainKey();
		}
		
	}
	
	/**
	 * Questo metodo permette di far creare il codice da decodificare all'utente e sarà un altro utente a effettuare
	 * 	la decodifica del codice.
	 */
	private void humanVsHuman() {
		this.IOComunication.getView().print("ORA E'IL TURNO DI "+ this.player1.getNameOfPlayer() + " CHE SARA' IL CODIFICATORE, QUINDI SENZA FARTI VEDERE, INSERISCI LA SEQUENZA DI COLORI. \nQUESTI SONO I COLORI DISPONIBILI SU CUI POTRAI SCEGLIERE: \n" + this.difficolta.getAvaiableColorsToString() + "\n");
		this.setMainKey();
	}
	
	/**
	 * Questo metodo permette all'utente di inserire della chiave da decodificare.
	 */
	private void setMainKey() {
		
		ArrayList<String> keyColors = new ArrayList<String>();
		
		do {
			keyColors = this.IOComunication.getDataFromPlayer();
			if(keyColors != null) {
				if(this.game.checkCorrectRow(new Row(keyColors))) {
					this.mainKey = new Row(keyColors);
					return;
				}else {
					this.IOComunication.getView().print("\n\n\n\n\n############################################################################\n\n"
													+ "			HAI INSERITO DEI VALORI NON VALIDI, RIPROVA ...\n\n"
													+ "############################################################################\n\n\n");
				}
					
			}else {
				this.IOComunication.getView().print("HAI INSERITO VOLORI NULLI,PERFAVORE RIPROVA ... \n");
			}
		}while(true);
	}
	
	/**
	 * Permette di far selezionare all'utente la difficoltà del gioco.
	 */
	private void setDifficulty() {

		this.IOComunication.getView().print("\nSCEGLI LA DIFFICOLTA DI GIOCO :\n");	//			 1) EASY;\n				 2) NORMAL;\n				 3) HARD;");
		
		for(int c1 = 0; c1 < SetRules.getInstance(this.IOComunication.getView()).getMode().size(); c1++) {
			this.IOComunication.getView().print("				 " + c1 + ") " + SetRules.getInstance(this.IOComunication.getView()).getMode().get(c1)+"\n");
		}

		//ATTENDO CHE L'UTENTE MI INDICHI LA DIFFICOLTA' CON CUI VUOLE GIOCARE
		int data = this.IOComunication.getDataFromConsole(SetRules.getInstance(this.IOComunication.getView()).getMode().size() - 1);
		
		try {
			this.difficolta = SetRules.getInstance(this.IOComunication.getView()).setDifficulty(data);
		}catch (BadFileException e) {
			this.IOComunication.getView().print("\n\n"  + e.getMessage() + "\n\n");
			System.exit(-1);
		}

	}

	/**
	 * Questo metodo permette mostrare e creare i giocatori della partita e la strategia (solo se è stato scelto il giocatore <code>PC</code>) a seconda della scelta dell'utente.
	 */
	private void getPlayer() {
		this.IOComunication.getView().print("\nQUESTA E' LA LISTA COMPLETA DI TUTTI I TIPI DI GIOCATORI POSSIBILI : \n");
		List<String>	playerType = null;

		playerType = PlayerArchive.getInstance().getNameTypeOfPlayer();
		
		for(int c1 = 0; c1 < playerType.size(); c1++)
			this.IOComunication.getView().print(" 				" + c1 + ") " + playerType.get(c1) + "\n");
		
		
		
		this.player1 = createPlayer(1,playerType,TypeGamePlayer.CODEMAKER);
		this.player2 = createPlayer(2,playerType,TypeGamePlayer.CODEBREAKER);
		
		if(this.player2.getTypeOfPlayer() == TypePlayer.PC) {
			// IL PC E' IL CODEBRAKER
			this.setStrategy();
		}
		
	}
	
	/**
	 * Questo metodo permette di mostrare l'elenco delle strategie che possono essere adottate dal giocatore <code>PC</code>. Il tipo di strategia viene scelta	
	 * 		dall'utente
	 */
	private void setStrategy() {
		List<String>	strategyType = new ArrayList<String>();
		this.IOComunication.getView().print("\nSELEZIONA LA STRATEGIA CHE VUOI CHE IL PC ADOTTI. ECCO QUELLE DISPONIBILI : \n");
		strategyType = StrategyArchive.getInstance().getNameTypeOfStrategy();
		
		for(int c1 = 0; c1 < strategyType.size(); c1++)
			this.IOComunication.getView().print(" 				" + c1 + ") " + strategyType.get(c1) + "\n");
		
		this.strategy = createStrategy(strategyType);
	}
	
	/**
	 * Permette all'utente di decidere quale strategia far adottare al Computer per
	 * 	poter risolvere la <code>ROW</code> da decodificare.
	 * @param strategyType Lista delle startegie disponibili
	 * @return <code>StrategyInterface</code> - ritorna la strategia scelta dall'utente
	 */
	private StrategyInterface createStrategy(List<String> strategyType) {
		this.IOComunication.getView().print("\nSCEGLI IL TIPO DI STATEGIA TRA LE SEGUENTI : \n");
		this.IOComunication.getView().print("---> ");

		int data = this.IOComunication.getDataFromConsole(strategyType.size() - 1);
		
		return StrategyArchive.getInstance().getStrategy(strategyType.get(data)).createStrategy();
	}
	
	/**
	 * Permette all'utente di decidere i giocatori da creare
	 * @param i - indice progressivo che identifica un giocatore da un altro.
	 * @param playerType - tipi di giocatori disponibili (ad esempio : -HUMAN, -PC, ecc.)
	 * @param type - tipologia di giocatori (CODEMAKER / CODEBREAKER)
	 * @return <code>Player</code> -  ritorna il giocatore a seconda del tipo e tipologia di giocatore
	 * 			inserita dall'utente.
	 */
	private Player createPlayer(int i, List<String> playerType,TypeGamePlayer type) {

		this.IOComunication.getView().print("\nSCEGLI IL TIPO DEL GIOCATORE "+ i +" CHE SARA' IL "+ type.toString() + " : \n");
		this.IOComunication.getView().print("---> ");

		int data = this.IOComunication.getDataFromConsole(playerType.size() - 1);
		
		this.IOComunication.getView().print("\nSCEGLI IL NOME DEL GIOCATORE "+ i +" : \n");
		this.IOComunication.getView().print("---> ");
		
		String name = this.IOComunication.getStringFromConsole();
		
		return PlayerArchive.getInstance().getPlayer(playerType.get(data)).createPlayer(name,type);
	}
	
	/**
	 * Ritorna la chive da decodificare
	 * @return <code>Row</code> - chiave da decodificare
	 */
	public Row getMainKey() {
		return this.mainKey;
	}
	
}
