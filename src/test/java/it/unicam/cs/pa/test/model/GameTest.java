/**
 * 
 */
package it.unicam.cs.pa.test.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unicam.cs.pa.controller.Controller;
import it.unicam.cs.pa.controller.Coordinator;
import it.unicam.cs.pa.controller.Initializatior;
import it.unicam.cs.pa.model.Game;
import it.unicam.cs.pa.model.Row;
import it.unicam.cs.pa.player.Player;
import it.unicam.cs.pa.player.TypePlayer;
import it.unicam.cs.pa.view.MainView;
import it.unicam.cs.pa.view.ViewInterface;

/**
 * @author edoardo
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameTest {

	ViewInterface view = new MainView();
	Controller controller = new Controller(view);
	Game gioco = new Game(controller);

	Initializatior init		= new Initializatior(controller, gioco);
	Coordinator cordinatore = new Coordinator(gioco,view);

	@Before
    public void init() throws Exception {
    }

    // TESTO IL COSTRUTTORE
    @Test
    public void testGame() {

    	assertNotNull(gioco);
    	testAddRow();
    	gioco.setMainKey(init.getMainKey());
    	testGetMainKey();
    	testGetMode();
    	testGetSizeOfGameBoard();
    	testIsSolved();
    	testGetPlayer1();
    	if(testGetPlayer2().getTypeOfPlayer() == TypePlayer.PC)
    		testGetStrategy();
    	testGetGameBoard();
    }
    
    // TESTO L'AGGIUNTA DI UNA RIGA

    public void testAddRow() {

    	
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("E");
    	list.add("R");
    	list.add("T");
    	list.add("T");
    	
    	ArrayList<String> list2 = new ArrayList<String>();
    	list2.add("R");
    	list2.add("R");
    	list2.add("T");
    	list2.add("T");
    	
    	Row mainKey = new Row(list);
    	Row key = new Row(list2);
    	
    	boolean status = gioco.addRow(key, mainKey);
    	assertEquals(false, status);
    }
    
    // TESTO IL RITORNO DELLA MAINKEY DEL GIOCO
    
    public void testGetMainKey() {
    	assertNotNull(gioco.getMainKey());
    }
    
    // TESTO IL RITORNO LA MODALITA' SCELTA PER IL GIOCO
    
    public void testGetMode() {
    	assertNotNull(gioco.getMode());
    }
    
    // TESTO IL RITORNO DELLA GRANDEZZA DELLA MIA GAMEBOARD
   
    public void testGetSizeOfGameBoard() {
    	assertNotNull(gioco.getSizeOfGameBoard());
    }
    
    
    public void testIsSolved() {
    	assertEquals(false, gioco.isSolved());
    }
    
    // TESTO IL RITORNO DEL GIOCATORE 1
    
    public void testGetPlayer1(){
    	assertNotNull(gioco.getPlayer1());
    }
    
    // TESTO IL RITORNO DEL GIOCATORE 2
    
    public Player testGetPlayer2() {
    	assertNotNull(gioco.getPlayer2());
    	return gioco.getPlayer2();
    }
    
    // TESTO IL RITORNO DELLA STRATEGIA DEL GIOCO
   
    public void testGetStrategy() {
    	assertNotNull(gioco.getStrategy());
    }
    
    // TESTO IL RITORNO DELLA GAMEBOARD
   
    public void testGetGameBoard() {
    	assertNotNull(gioco.getGameBoard());
    }
    
}
