/**
 * 
 */
package it.unicam.cs.pa.test.player;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.player.HumanPlayer;
import it.unicam.cs.pa.player.Player;
import it.unicam.cs.pa.player.TypeGamePlayer;
import it.unicam.cs.pa.player.TypePlayer;

/**
 * @author edoardo
 *
 */
public class HumanPlayerTest {

    @Before
    public void setUp() throws Exception {
    }

    // TESTO IL COSTRUTTORE
    @Test
    public void testHumanPlayer() {
    	Player giocatore = new HumanPlayer("BUBUBU", TypeGamePlayer.CODEMAKER);
    	assertNotNull(giocatore);
    }
    
    // TESTO IL RITORNO DEL NOME DEL GIOCATORE
    @Test
	public void testGetNameOfPlayer() {
    	Player giocatore = new HumanPlayer("MARCO", TypeGamePlayer.CODEMAKER);
    	
    	assertEquals("MARCO", giocatore.getNameOfPlayer());
    }
    
    // TESTO IL RITORNO DEL TIPO DI GIOCATORE
    @Test
    public void testGetTypeOfPlayer() {
    	Player giocatore = new HumanPlayer("LUCA", TypeGamePlayer.CODEBREAKER);
    	assertEquals(TypePlayer.PLAYER, giocatore.getTypeOfPlayer());
    }
    
    // TESTO IL RITORNO DELLA TIPOLOGIA DI GIOCARTORE DELLA PARTITA (CODEMAKER/CODEBREAKER)
    @Test
    public void testGetTypeGamePlayer() {
    	Player giocatore = new HumanPlayer("ALFREDO", TypeGamePlayer.CODEBREAKER);
    	assertEquals(TypeGamePlayer.CODEBREAKER, giocatore.getTypeGamePlayer());
    }
}
