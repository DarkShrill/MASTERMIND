/**
 * 
 */
package it.unicam.cs.pa.strategy;


/**
 * <b> Responsabilit√† : </b> Permette di creare la strategia di gioco "KNUTH_ALGO" per tipo di giocatore <code>PC</code> 
 * 
 * @author edoardo
 *
 */
public class KnuthStrategyFactory implements StrategyFactory{

	@Override
	public StrategyInterface createStrategy() {
		return new KnuthStrategy();
	}

}
