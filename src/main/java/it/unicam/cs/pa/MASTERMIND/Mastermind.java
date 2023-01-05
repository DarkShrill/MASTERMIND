package it.unicam.cs.pa.MASTERMIND;

import it.unicam.cs.pa.controller.Controller;
import it.unicam.cs.pa.controller.Coordinator;
import it.unicam.cs.pa.controller.Initializatior;
import it.unicam.cs.pa.model.Game;
import it.unicam.cs.pa.player.TypeGamePlayer;
import it.unicam.cs.pa.rules.Rules;
import it.unicam.cs.pa.rules.SetRules;
import it.unicam.cs.pa.view.MainView;
import it.unicam.cs.pa.view.ViewInterface;

/**
 * 
 * Main del gioco
 * 
 * @author edoardo
 *
 */
public class Mastermind {
	


	public static void main(String[] args) {
		Rules  regole			= null;
		Game   game				= null;
		ViewInterface view		= null;
		Controller controller 	= null;
		Initializatior init		= null;
		Coordinator	cordinatore = null;
		String response 		= "";
		
		view 		= new MainView();
		
		view.info();
		
		regole = SetRules.getInstance(view);
		
		controller 	= new Controller(view);
		game 		= new Game(controller);
		init		= new Initializatior(controller, game);
		cordinatore = new Coordinator(game,view);
		
		game.addObserver(view);
		game.setMainKey(init.getMainKey());
		
		view.startGameMessage();
		view.print(game.getMode().getAvaiableColorsToString());
		
		while(game.getSizeOfGameBoard() < game.getMode().getSlots()&&!game.isSolved()) { 
			
			view.print("\n\n\n\n###################################################\n");
			view.print("#                                                 #\n");
			view.print("#            DECODIFICATORE --> TURNO "+ cordinatore.getTurno() + "           #\n");
			view.print("#                                                 #\n");
			view.print("###################################################\n\n\n\n");

			
			if(cordinatore.nextTurn()) {
				response += "\n\nINSERISCI LA PROSSIMA IPOTETICA SOLUZIONE. \nI COLORI DISPONIBILI SONO : " + game.getMode().getAvaiableColorsToString() + "\n"; 
				view.print(response + "\n");
				response = "";
			}
			
		}
		view.printTheSolution(game);
		view.print("\n\n\n\n\n\n###################################################\n");
		
		
		if(game.isSolved()) {
			if(game.getPlayer1().getTypeGamePlayer() == (TypeGamePlayer.CODEBREAKER)) {
				view.print("	THE WINNER IS : " + game.getPlayer1().getNameOfPlayer() + "\n");
			}else {
				view.print("	THE WINNER IS : " + game.getPlayer2().getNameOfPlayer() + "\n");;
			}
		}
		else{
			if(game.getPlayer1().getTypeGamePlayer() == (TypeGamePlayer.CODEMAKER)) {
				view.print("	THE WINNER IS : " + game.getPlayer1().getNameOfPlayer() + "\n");
			}else {
				view.print("	THE WINNER IS : " + game.getPlayer2().getNameOfPlayer() + "\n");;
			}
		}
		
		view.print("\n		### THE GAME IS END ###\n");
	}
	
	
	
	

}

	

