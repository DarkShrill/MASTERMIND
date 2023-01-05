
package it.unicam.cs.pa.view;

import java.util.ArrayList;
import java.util.Scanner;

import it.unicam.cs.pa.exception.MismatchException;
import it.unicam.cs.pa.model.Game;
import it.unicam.cs.pa.model.Row;

/**
 * <b>Responsabilità : </b> Definisce l'interfaccia grafica "CONSOLE"
 * 
 * @author edoardo
 *
 */
public class MainView implements ViewInterface{

	/**
	 * OGNI VIEW DEVE IMPLEMENTARE SIA @VIEWINTERFACE CHE @OBSERVER QUESTO PERCHE
	 * 	GRAZIE AL METODO UPDATE OGNI VIEW AGGIORNA LA PROPRIA GRAFICA OGNI VOLTA CHE
	 * 	ABBIAMO UN CAMBIAMENTO(AGGIUNTA DI UNA RIGA) NELLA BOARDGAME.
	 */
	
	/**
	 * Mi permette di ottenere tutto quello che l'utente inserisce sulla console
	 */
	private static final Scanner scan = new Scanner(System.in);

	/**
	 * Questo metodo permette di aggiornare la view ogni volta che viene aggiunta una riga.
	 * 
	 */
	@Override
	public void update(ArrayList<Row> arg) {
		String str		= "\n";
		ArrayList<Row> gameBoard =  arg;
		
	
		for(int i = 0; i < gameBoard.size(); i++) {
			for(int c1 = 0; c1 < 4; c1 ++) {
				str += "| " + gameBoard.get(i).getHoles().get(c1).toUpperCase() +" |";
			}
			str += "  ---> (" + gameBoard.get(i).getNumberOfCorrectColor() + " colori corretti ma in posizione sbagliata , " + gameBoard.get(i).getNumberOfCorrectPosition() + " colori nella posizione giusta)\n";
		}
		
		System.out.println(str);
	}

	@Override
	public ArrayList<String> getDataFromPlayer() {
		this.print("---> ");
		
		ArrayList<String> colorFromPlayer 	= new ArrayList<String>();
		String str 							= "";
		String[] split						= null;
		
		str = scan.next();
		split = str.split(",");
		
		for (String string : split) {
			colorFromPlayer.add(string.toUpperCase());
		}
		return colorFromPlayer;
	}
	
	@Override
	public String getStringFromConsole() {
		
		String string = scan.next();
		
		return string;
	}
	
	

	@Override
	public int getDataFromConsole(int maxRange) {
		int numberOfFileSelected		= -1;
		
		this.print("---> ");
		
		do {

			try {
				if(scan.hasNextInt()) {
					numberOfFileSelected = scan.nextInt();
					if((numberOfFileSelected >= maxRange + 1)||(numberOfFileSelected < 0)||(maxRange < 0)) {
						//STO METTENDO UN NUMERO CHE VA FUORI DAL RANGE QUINDI RIFACCIO DA CAPO.
						this.println("!!ERRORE!! \n HAI DIGITATO UN NUMERO FUORI DA QUELLI NELL'ELENCO \nDIGITA NUOVAMENTE IL NUMERO!");
					}else{
						break;
					}
				}else
					throw new MismatchException("#############################################################\n\n		HAI INSERITO UNA STRINGA ANZICHE' UN NUMERO\n\n#############################################################\n\n---> ");
			}catch (MismatchException e) {
				this.print("\n" + e.getMessage());
				scan.next();
			}

		}while(true);	

		return numberOfFileSelected;
	}

	@Override
	public void print(String str) {
		System.out.print(str);
	}
	
	/**
	 * Questo metodo permette di rappresentare messaggi con l'aggiunta di '\n' finale.
	 * @param <code>String</code> - string da far vedere a video
	 */
	private void println(String str) {
		System.out.println(str);
	}

	@Override
	public void info() {
		String str		= "";
		
		str += "+-----------------------------------------------------------------+\n";
		str += "|                                                                 |\n";
		str += "|     Benvenuto nel gioco di Mastermind created by Edoardo Papa   |\n";
		str += "|                                                                 |\n";
		str += "+-----------------------------------------------------------------+\n\n";
		str += "Il gioco è molto semplice e intuitivo basato sulle regole del gioco da tavolo MASTERMIND.\n\n";
		str += "Se si vogliono personalizzare le regole del gioco, \n";
		str += "basterà modificare/aggiungere a proprio piacimento i dati presenti :\n\n";
		str += "1)\"/rulesData\"  <--- aggiungendo nuovi file delle regole\n\n2)\"/playerDataCreation/data\" <--- aggiungendo all'interno del file i nuovi giocatori\n\n";
		str += "3)\"/strategyDataCreation/strategyData\" <--- aggiungendo all'interno del file le \n                nuove strategie da far utilizzare al giocatore del tipo @PC\n\n\n";
		str += "Tutti i tipi di dati che si trovano all'interno delle cartelle specificate\nprecedentemente, dovranno rispettare un formato standard definito nel file INFO.TXT\nall'interno della cartella: \"/standard/\"";
		str += "\n\n           !!! BUON DIVERTIMENTO !!!";

		this.println(str);
	}
	
	
	@Override
	public void startGameMessage() {
		for(int i = 0; i < 80; i++)
			this.print("\n");
		
		this.print("###################################################\n");
		this.print(" ... ORA E' IL TURNO DEL GIOCATORE CHE DOVRA DECODIFICARE IL CODICE ... \n");
		this.print(" 			BUON DIVERTIMENTO!						  \n");
		this.print("\n\n\n\n\n COLORI DISPONIBILI : \n");
	}

	@Override
	public void printTheSolution(Game game) {
		String str = "";
		for(int c1 = 0; c1 < 4; c1 ++) {
			str += "| " + game.getMainKey().getHoles().get(c1).toUpperCase() +" |";
		}

		
		this.print("\nThe solution was : \n" + str + "\n");
	}
}
