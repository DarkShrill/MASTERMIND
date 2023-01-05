/**
 * 
 */
package it.unicam.cs.pa.strategy;


/**
 * <b> Responsabilità : </b> Permette di creare la strategia di gioco "RANDOM" per tipo di giocatore <code>PC</code> 
 * 
 * @author edoardo
 *
 */
public class RandomStrategyFactory implements StrategyFactory{

	@Override
	public StrategyInterface createStrategy() {
		return new RandomStrategy();
	}

}
