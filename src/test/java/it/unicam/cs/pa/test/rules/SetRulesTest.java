/**
 * 
 */
package it.unicam.cs.pa.test.rules;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.player.PlayerArchive;
import it.unicam.cs.pa.rules.SetRules;
import it.unicam.cs.pa.view.MainView;
import it.unicam.cs.pa.view.ViewInterface;

/**
 * @author edoardo
 *
 */
public class SetRulesTest {

    @Before
    public void setUp() throws Exception {
    }
    
	// TESTO IL RITORNO DELLE VARIE MODALITA LETTE
    @Test
    public void testGetMode() {
    	ViewInterface view = new MainView();
    	assertNotNull(SetRules.getInstance(view).getMode());
    }
    
    // TESTO IL RITORNO DELL'INSTANZA
    @Test
    public void testGetInstace() {
    	ViewInterface view = new MainView();
    	assertNotNull(SetRules.getInstance(view));
    }
    
    // TESTO IL RITORNO DEI COLORI DISPONIBILI A SECONDA DEL FILE SCELTO
    @Test
    public void testGetAvaiableColors() {
    	ViewInterface view = new MainView();
    	assertNotNull(SetRules.getInstance(view).getAvaiableColors());
    }
    
    // TESTO IL RITORNO DEL NUMERO DI TANTATIVI LETTO DAL FILE SCELTO
    @Test
    public void testGetNumberOfSlots() {
    	ViewInterface view = new MainView();
    	assertNotNull(SetRules.getInstance(view).getNumberOfSlots());
    }
    
    // TESTO IL SET DELLA DIFFICOLTA'
    @Test
    public void testSetDisfficulty() {
    	ViewInterface view = new MainView();
    	try {
    		assertNotNull(SetRules.getInstance(view).setDifficulty(0));
    	}catch (Exception e) {
		}
    }
    
}
