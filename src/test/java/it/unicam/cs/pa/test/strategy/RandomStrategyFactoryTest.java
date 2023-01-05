/**
 * 
 */
package it.unicam.cs.pa.test.strategy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.strategy.RandomStrategyFactory;
import it.unicam.cs.pa.strategy.StrategyFactory;

/**
 * @author edoardo
 *
 */
public class RandomStrategyFactoryTest {

    @Before
    public void setUp() throws Exception {
    }
    
    // TESTO IL COSTRUTTORE
    @Test
    public void testRandomStrategyFactory() {
    	StrategyFactory strategia = new RandomStrategyFactory();
    	assertNotNull(strategia);
    }
    
    // TESTO IL METODO PER RITORNARE IL GIOCATORE CREATO
    @Test
    public void testCreateStrategy() {
    	StrategyFactory strategia = new RandomStrategyFactory();
    	assertNotNull(strategia.createStrategy());
    }
    
}
