/**
 * 
 */
package it.unicam.cs.pa.test.strategy;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.mode.GameDifficulty;
import it.unicam.cs.pa.mode.GameMode;
import it.unicam.cs.pa.model.Row;
import it.unicam.cs.pa.rules.SetRules;
import it.unicam.cs.pa.strategy.KnuthStrategy;
import it.unicam.cs.pa.strategy.StrategyInterface;
import it.unicam.cs.pa.view.MainView;
import it.unicam.cs.pa.view.ViewInterface;

/**
 * @author edoardo
 *
 */
public class KnuthStrategyTest {

    @Before
    public void setUp() throws Exception {
    }

    // TESTO IL COSTRUTTORE
    @Test
    public void testKnutStrategy() {
    	StrategyInterface strategia = new KnuthStrategy();
    	
    	assertNotNull(strategia);
    }
    
    //TESTO IL METODO PER IL RITORNO DELLA STRATEGIA 
    @Test
    public void testGetStrategy() {
    	StrategyInterface strategia = new KnuthStrategy();
    	ViewInterface view = new MainView();
    	try {
    		SetRules.getInstance(view).setDifficulty(0);//EASY
    	}catch (Exception e) {
		}
    	GameDifficulty difficolta = new GameMode("EASY", SetRules.getInstance(view).getAvaiableColors().size() - 1, SetRules.getInstance(view).getAvaiableColors(), SetRules.getInstance(view).getNumberOfSlots());
		ArrayList<String> list = new ArrayList<String>();
		list.add("W");
		list.add("O");
		list.add("W");
		list.add("W");
		Row key = new Row(list);
    	assertNotNull(strategia.getStrategy(difficolta,key));
    }
}
