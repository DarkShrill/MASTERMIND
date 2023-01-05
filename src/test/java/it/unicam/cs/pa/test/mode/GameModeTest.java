/**
 * 
 */
package it.unicam.cs.pa.test.mode;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unicam.cs.pa.exception.BadFileException;
import it.unicam.cs.pa.mode.GameMode;
import it.unicam.cs.pa.rules.SetRules;
import it.unicam.cs.pa.view.MainView;
import it.unicam.cs.pa.view.ViewInterface;

/**
 * @author edoardo
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameModeTest {

    @Before
    public void setUp() throws Exception {
    	
    }
    
    // TESTO IL COSTRUTTORE
    @Test
    public void testGameMode() {
    	List<String> list = new ArrayList<String>();
    	list.add("A");
    	list.add("B");
    	list.add("C");
    	
    	GameMode gameMode = new GameMode("TEST", 3, list, 5);
    	
    	assertNotNull(gameMode);
    	
    }
    
    // TESTO IL RITORNO DEL NOME DELLA MODALITA' DI GIOCO
    @Test
    public void testGetName() {
    	List<String> list = new ArrayList<String>();
    	list.add("A");
    	list.add("B");
    	list.add("C");
    	
    	GameMode gameMode = new GameMode("TEST", 3, list, 5);
    	
    	assertEquals("TEST", gameMode.getName());
    }
    
    // TESTO IL RITORNO DEL NUMERO DI TENTATIVI CHE SI POSSONO ESEGUIRE
    @Test
    public void testAGetSlots() {
    	List<String> list = new ArrayList<String>();
    	list.add("A");
    	list.add("B");
    	list.add("C");
    	
    	GameMode gameMode = new GameMode("TEST", 3, list, 12);
    	
    	assertEquals(12, gameMode.getSlots());
    }
    
    // TESTO IL RITORNO DEI COLORI DISPONIBILI
    @Test
    public void testAGetAvaiableColors() {
    	List<String> list = new ArrayList<String>();
    	list.add("A");
    	list.add("B");
    	list.add("C");
    	
    	GameMode gameMode = new GameMode("TEST", 3, list, 5);
    	
    	assertEquals(list, gameMode.getAvaiableColors());
    }
    
    // TESTO IL RITORNO DEI COLORI DISPONIBILI SOTTO FORMA DI STRINGA
    @Test
    public void testAGetAvaiableColorsToString() {
    	List<String> list = new ArrayList<String>();
    	list.add("F");
    	list.add("I");
    	list.add("W");
    	
    	GameMode gameMode = new GameMode("PIPPO", 3, list, 5);
    	assertEquals("[F, I, W]", gameMode.getAvaiableColorsToString());
    }
    
    // TESTO IL RITORNO DEL NUMERO DI TENTATIVI CHE SI POSSONO ESEGUIRE PRENDENDO I DATI DAL FILE
    @Test
    public void testGetSlots2() {
    	List<String> list = new ArrayList<String>();
    	list.add("A");
    	list.add("B");
    	list.add("C");
    	
    	ViewInterface view = new MainView();
    	
    	SetRules.getInstance(view);
    	
    	GameMode gameMode = new GameMode("TEST", 3, list, 5);
    	
    	if((gameMode.getSlots() == 12)||(gameMode.getSlots() == 8)||(gameMode.getSlots() == 5)) {
    		
    	}else
    	{
    		fail();
    	}
    }
    
    // TESTO IL RITORNO DEI COLORI DISPONIBILI PRENDENDO I DATI DAL FILE
    @Test
    public void testBGetAvaiableColors2() {
    	List<String> list = new ArrayList<String>();
    	list.add("R");
    	list.add("B");
    	list.add("G");
    	list.add("Y");
    	list.add("V");
    	list.add("O");
    	list.add("C");
    	list.add("W");
    	
    	ViewInterface view = new MainView();
    	
    	SetRules.getInstance(view);
    	
    	try {
    		SetRules.getInstance(view).setDifficulty(0); //EASY MODE
    	}catch (BadFileException e) {
		}
    	
    	GameMode gameMode = new GameMode("TEST", 3, list, 5);
    	
    	assertEquals(list, gameMode.getAvaiableColors());
    }
    
    // TESTO IL RITORNO DEI COLORI DISPONIBILI SOTTO FORMA DI STRINGA PRENDENDO I DATI DAL FILE
    @Test
    public void testGetAvaiableColorsToString2() {
    	List<String> list = new ArrayList<String>();
    	list.add("F");
    	list.add("I");
    	list.add("W");
    	
    	ViewInterface view = new MainView();
    	
    	SetRules.getInstance(view);

    	GameMode gameMode = new GameMode("PIPPO", 3, list, 5);
    	
    	assertEquals("R , B , G , Y , V , O , C , W ", gameMode.getAvaiableColorsToString());
    }
}
