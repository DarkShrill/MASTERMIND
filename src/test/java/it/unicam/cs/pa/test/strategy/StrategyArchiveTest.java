/**
 * 
 */
package it.unicam.cs.pa.test.strategy;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.strategy.StrategyArchive;
import it.unicam.cs.pa.strategy.StrategyFactory;

/**
 * @author edoardo
 *
 */
public class StrategyArchiveTest {
    
	@Before
    public void setUp() throws Exception {
    }
    
	
	   // TESTO LA CREAZIONE DELL'OGGETTO
    @Test
    public void testGetInstance() {
    	assertNotNull(StrategyArchive.getInstance());
    }
    
    // TESTO IL RITORNO DEI GIOCATORI
    @Test
	public void testGetStrategy() {
    	StrategyFactory strategia = StrategyArchive.getInstance().getStrategy("RANDOM");
    	assertNotNull(strategia);
    }
    
    // TESTO IL CARICAMENTO DEI GIOCATORI DA UN PATH DI UN FILE
    @Test
    public void testLoadStrategy() {
    	File file = new File("/strategyDataCreation/strategyData.txt");
    	try{
    		StrategyArchive.getInstance().loadStrategy(file);
    	}catch (Exception e) {
		}
    	assertNotNull(StrategyArchive.getInstance().getStrategy("RANDOM"));
    }
    
    //TESTO IL RITORNO DEL TIPO DI GIOCATORI
    @Test
    public void testGetNameTypeOfStrategy() {
    	assertEquals("[RANDOM, KNUTH]", StrategyArchive.getInstance().getNameTypeOfStrategy().toString());
    }
}
