/**
 * 
 */
package it.unicam.cs.pa.test.player;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;



import it.unicam.cs.pa.player.PlayerArchive;
import it.unicam.cs.pa.player.PlayerFactory;

/**
 * @author edoardo
 *
 */
public class PlayerArchiveTest {

    @Before
    public void setUp() throws Exception {
    }

    // TESTO LA CREAZIONE DELL'OGGETTO
    @Test
    public void testGetInstance() {
    	assertNotNull(PlayerArchive.getInstance());
    }
    
    // TESTO IL RITORNO DEI GIOCATORI
    @Test
	public void testGetPlayer() {
    	PlayerFactory giocatore = PlayerArchive.getInstance().getPlayer("HUMAN_PLAYER");
    	assertNotNull(giocatore);
    }
    
    // TESTO IL CARICAMENTO DEI GIOCATORI DA UN PATH DI UN FILE
    @Test
    public void testLoadPlayer() {
    	File file = new File("/playerDataCreation/data.txt");
    	try{
    		PlayerArchive.getInstance().loadPlayer(file);
    	}catch (Exception e) {
		}
    	assertNotNull(PlayerArchive.getInstance().getPlayer("PC_PLAYER"));
    }
    
    //TESTO IL RITORNO DEL TIPO DI GIOCATORI
    @Test
    public void testGetNameTypeOfPlayer() {
    	assertEquals("[HUMAN_PLAYER, PC_PLAYER]", PlayerArchive.getInstance().getNameTypeOfPlayer().toString());
    }
}
