/**
 * 
 */
package it.unicam.cs.pa.test.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.controller.Controller;
import it.unicam.cs.pa.controller.Coordinator;
import it.unicam.cs.pa.controller.Initializatior;
import it.unicam.cs.pa.model.Game;
import it.unicam.cs.pa.rules.SetRules;
import it.unicam.cs.pa.view.MainView;
import it.unicam.cs.pa.view.ViewInterface;

/**
 * @author edoardo
 *
 */
public class CoordinatorTest {

    @Before
    public void setUp() throws Exception {
    }
    
    // TESTO IL COSTRUTTORE
    @Test
    public void testCoordinator() {
    	ViewInterface view = new MainView();
    	
    	Controller controller = new Controller(view);
    	
    	Game game = new Game(controller);
    	
    	Coordinator cordinatore = new Coordinator(game,view);
    	
    	assertNotNull(cordinatore);
    }
  
    // TESTO IL NEXT TURN E IL GET TURNO
    @Test
    public void testNextTurnAndGetTurno() {
		Game   game				= null;
		ViewInterface view		= null;
		Controller controller 	= null;
		Initializatior init		= null;
		Coordinator	cordinatore = null;
		
		view 		= new MainView();
		
		if((SetRules.getInstance() == null))
			SetRules.getInstance(view);
		
		controller 	= new Controller(view);
		game 		= new Game(controller);
		init		= new Initializatior(controller, game);
		cordinatore = new Coordinator(game,view);
		
		
		game.addObserver(view);
		game.setMainKey(init.getMainKey());
		
		view.print("@@@@@@@@@@ INSERISCI UNA RIGA VALIDA!!!! ALTRIMENTI QUESTO TEST NON FUNZIONA");
		assertEquals(true,cordinatore.nextTurn());
		
		view.print("@@@@@@@@@@ INSERISCI UNA RIGA NON VALIDA!!!! ALTRIMENTI QUESTO TEST NON FUNZIONA");
		assertEquals(false,cordinatore.nextTurn());
		
		assertEquals(1,cordinatore.getTurno());
    }

	
}
