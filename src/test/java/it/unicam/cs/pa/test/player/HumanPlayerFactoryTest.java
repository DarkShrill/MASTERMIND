/**
 * 
 */
package it.unicam.cs.pa.test.player;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.player.HumanPlayerFactory;
import it.unicam.cs.pa.player.PlayerFactory;
import it.unicam.cs.pa.player.TypeGamePlayer;

/**
 * @author edoardo
 *
 */
public class HumanPlayerFactoryTest {

    @Before
    public void setUp() throws Exception {
    }
    
    // TESTO IL COSTRUTTORE
    @Test
    public void testHumanPlayerFactory() {
    	PlayerFactory player = new HumanPlayerFactory();
    	assertNotNull(player);
    }
    
    // TESTO IL METODO PER RITORNARE IL GIOCATORE CREATO
    @Test
    public void testCreatePlayer() {
    	PlayerFactory player = new HumanPlayerFactory();
    	assertNotNull(player.createPlayer("GINNY", TypeGamePlayer.CODEBREAKER));
    }
	
}
