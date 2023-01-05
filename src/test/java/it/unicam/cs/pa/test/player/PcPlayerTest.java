/**
 * 
 */
package it.unicam.cs.pa.test.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.player.HumanPlayer;
import it.unicam.cs.pa.player.PcPlayer;
import it.unicam.cs.pa.player.Player;
import it.unicam.cs.pa.player.TypeGamePlayer;
import it.unicam.cs.pa.player.TypePlayer;

/**
 * @author edoardo
 *
 */
public class PcPlayerTest {

    @Before
    public void setUp() throws Exception {
    }
    
    // TESTO IL COSTRUTTORE
    @Test
    public void testPcPlayer() {
    	Player giocatore = new PcPlayer("DARIO",TypeGamePlayer.CODEMAKER);
    	assertNotNull(giocatore);
    }
    
    // TESTO IL RITORNO DEL NOME DEL GIOCATORE
    @Test
	public void testGetNameOfPlayer() {
    	Player giocatore = new PcPlayer("MARCO", TypeGamePlayer.CODEMAKER);
    	
    	assertEquals("MARCO", giocatore.getNameOfPlayer());
    }
    
    // TESTO IL RITORNO DEL TIPO DI GIOCATORE
    @Test
    public void testGetTypeOfPlayer() {
    	Player giocatore = new PcPlayer("LUCA", TypeGamePlayer.CODEBREAKER);
    	assertEquals(TypePlayer.PC, giocatore.getTypeOfPlayer());
    }
    
    // TESTO IL RITORNO DELLA TIPOLOGIA DI GIOCARTORE DELLA PARTITA (CODEMAKER/CODEBREAKER)
    @Test
    public void testGetTypeGamePlayer() {
    	Player giocatore = new PcPlayer("ALFREDO", TypeGamePlayer.CODEBREAKER);
    	assertEquals(TypeGamePlayer.CODEBREAKER, giocatore.getTypeGamePlayer());
    }
}
