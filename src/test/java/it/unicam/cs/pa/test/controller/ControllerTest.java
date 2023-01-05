/**
 * 
 */
package it.unicam.cs.pa.test.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;


import it.unicam.cs.pa.controller.Controller;
import it.unicam.cs.pa.view.MainView;
import it.unicam.cs.pa.view.ViewInterface;

/**
 * @author edoardo
 *
 */
public class ControllerTest {

    @Before
    public void setUp() throws Exception {
    }
    
    //TESTO IL COSTRUTTORE
    @Test
    public void testController() {
    	ViewInterface view = new MainView();
    	
    	Controller controller = new Controller(view);
    	
    	assertNotNull(controller);
    }
    
    //TESTO IL METODO PER RITORNARE UNA VIEW
    @Test 
    public void testGetView() {
    	ViewInterface view = new MainView();
    	
    	Controller controller = new Controller(view);
    	
    	ViewInterface secondView;
    	
    	secondView = controller.getView();
    	
    	assertNotNull(secondView);
    }
    
    // TESTO IL METODO PER PRENDERE IN INPUT I DATI DA UN GIOCATORE
    //		DURANTE LO SVOLGIMENTO DELLA PARTITA.
    @Test
    public void testGetDataFromPlayer() {
    	ViewInterface view = new MainView();
    	
    	Controller controller = new Controller(view);
    	
    	System.out.println("INSERISCI QUALCOSA ... \n");
    	
    	ArrayList<String> data = controller.getDataFromPlayer();
    	
    	assertNotNull(data);
    }
    
    // TESTO IL METODO PER PRENDERE IN INPUT I DATI DA UN GIOCATORE
    //		DURANTE INIZIALIZZAZIONE DELLA PARTITA.
    @Test
    public void testGetDataFromConsole() {
    	int maxNumberTest = 4;
    	ViewInterface view = new MainView();
    	
    	Controller controller = new Controller(view);
    	
    	System.out.println("INSERISCI UN NUMERO DA 1 a "+maxNumberTest+"\n");
    	
    	int data = controller.getDataFromConsole(maxNumberTest);
    	
    	assertNotNull(data);
    	
    	if((data > maxNumberTest)||(maxNumberTest < 1)) {
    		fail();
    	}
    }
    
    // TESTO IL METODO PER PRENDERE IN INPUT I DATI DA UN GIOCATORE
    //		DURANTE INIZIALIZZAZIONE DELLA PARTITA.
    @Test
    public void testGetStringFromConsole() {
    	ViewInterface view = new MainView();
    	
    	Controller controller = new Controller(view);
    	
    	System.out.println("INSERISCI QUALCOSA...\n");
    	
    	String data = controller.getStringFromConsole();
    	
    	assertNotNull(data);

    }
    
}
