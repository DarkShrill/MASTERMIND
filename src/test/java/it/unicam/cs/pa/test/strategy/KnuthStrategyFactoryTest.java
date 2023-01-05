/**
 * 
 */
package it.unicam.cs.pa.test.strategy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;


import it.unicam.cs.pa.strategy.KnuthStrategyFactory;
import it.unicam.cs.pa.strategy.StrategyFactory;


/**
 * @author edoardo
 *
 */
public class KnuthStrategyFactoryTest {

    @Before
    public void setUp() throws Exception {
    }
    
    // TESTO IL COSTRUTTORE
    @Test
    public void testKnuthStrategyFactory() {
    	StrategyFactory strategia = new KnuthStrategyFactory();
    	assertNotNull(strategia);
    }
    
    // TESTO IL METODO PER RITORNARE IL GIOCATORE CREATO
    @Test
    public void testCreateStrategy() {
    	StrategyFactory strategia = new KnuthStrategyFactory();
    	assertNotNull(strategia.createStrategy());
    }
}
