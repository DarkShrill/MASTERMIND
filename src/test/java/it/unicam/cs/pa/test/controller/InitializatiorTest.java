/**
 * 
 */
package it.unicam.cs.pa.test.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.controller.Controller;
import it.unicam.cs.pa.controller.Initializatior;
import it.unicam.cs.pa.model.Game;
import it.unicam.cs.pa.view.MainView;
import it.unicam.cs.pa.view.ViewInterface;

/**
 * @author edoardo
 *
 */
public class InitializatiorTest {

    @Before
    public void setUp() throws Exception {
    }
    
    // TESTO IL COSTRUTTORE e del GETMAINKEY
    @Test
    public void testCoordinator() {
    	ViewInterface view = new MainView();
    	
    	Controller controller = new Controller(view);
    	
    	Game game = new Game(controller);
    	
		Initializatior init		= new Initializatior(controller, game);
    	assertNotNull(init);
    	
    	assertNotNull(init.getMainKey());
    }
    
}
