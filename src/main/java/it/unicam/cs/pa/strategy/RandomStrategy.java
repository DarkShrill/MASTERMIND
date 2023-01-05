/**
 * 
 */
package it.unicam.cs.pa.strategy;

import java.util.ArrayList;
import java.util.Random;

import it.unicam.cs.pa.mode.GameDifficulty;
import it.unicam.cs.pa.model.Row;

/**
 *  <b>Responsabilità : </b> Definisce la strategia RANDOM
 * 
 * @author edoardo
 *
 */
public class RandomStrategy implements StrategyInterface{


	
	@Override
	public Row getStrategy(GameDifficulty difficult,Row Key) {

		ArrayList<String> keyColors = new ArrayList<String>();
		Random random = new Random();
		
		for(int i = 0; i < 4; i++){	
			keyColors.add((difficult.getAvaiableColors().get(random.nextInt(difficult.getAvaiableColors().size()))).toString());
		}
		
		return new Row(keyColors);
	}

}
