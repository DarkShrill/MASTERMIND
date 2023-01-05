/**
 * 
 */
package it.unicam.cs.pa.test.view;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.view.MainView;
import it.unicam.cs.pa.view.ViewInterface;



/**
 * @author edoardo
 *
 */
public class MainViewTest {

    @Before
    public void setUp() throws Exception {
    }
    
    //TESTO IL COSTUTTORE
    @Test
    public void testMainView() {
    	ViewInterface view = new MainView();
    	
    	assertNotNull(view);
    }

    // TESTO IL METODO PER PRENDERE IN INPUT I DATI DA UN GIOCATORE
    //		DURANTE LO SVOLGIMENTO DELLA PARTITA.
    @Test
    public void testGetDataFromPlayer() {
    	
    	ViewInterface view = new MainView();
    	
    	System.out.println("INSERISCI QUALCOSA ... \n");
    	
    	ArrayList<String> data = view.getDataFromPlayer();
    	
    	assertNotNull(data);
    }
    
    // TESTO IL METODO PER PRENDERE IN INPUT I DATI DA UN GIOCATORE
    //		DURANTE INIZIALIZZAZIONE DELLA PARTITA.
    @Test
    public void testGetDataFromConsole() {
    	int maxNumberTest = 4;
    	ViewInterface view = new MainView();
    	
    	System.out.println("INSERISCI UN NUMERO DA 1 a "+maxNumberTest+"\n");
    	
    	int data = view.getDataFromConsole(maxNumberTest);
    	
    	assertNotNull(data);
    	
    	if((data > maxNumberTest)||(maxNumberTest < 1)) {
    		fail();
    	}
    }
    
    // TESTO IL METODO PER PRENDERE IN INPUT I DATI DA UN GIOCATORE
    //		DURANTE INIZIALIZZAZIONE DELLA PARTITA.
    @Test
    public void getStringFromConsole() {
    	ViewInterface view = new MainView();
    	
    	System.out.println("INSERISCI QUALCOSA...\n");
    	
    	String data = view.getStringFromConsole();
    	
    	assertNotNull(data);

    }
	
}
