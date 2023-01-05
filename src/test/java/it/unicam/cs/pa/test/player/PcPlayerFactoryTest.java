/**
 * 
 */
package it.unicam.cs.pa.test.player;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.player.PcPlayerFactory;
import it.unicam.cs.pa.player.PlayerFactory;
import it.unicam.cs.pa.player.TypeGamePlayer;

/**
 * @author edoardo
 *
 */
public class PcPlayerFactoryTest {

    @Before
    public void setUp() throws Exception {
    }
    
    // TESTO IL COSTRUTTORE
    @Test
    public void testPcPlayerFactory() {
    	PlayerFactory player = new PcPlayerFactory();
    	assertNotNull(player);
    }
    
    // TESTO IL METODO PER RITORNARE IL GIOCATORE CREATO
    @Test
    public void testCreatePlayer() {
    	PlayerFactory player = new PcPlayerFactory();
    	assertNotNull(player.createPlayer("ROBERTO", TypeGamePlayer.CODEBREAKER));
    }
}
