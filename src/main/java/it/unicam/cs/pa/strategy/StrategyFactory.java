/**
 * 
 */
package it.unicam.cs.pa.strategy;


/**
 *  <b> Responsabilità : </b> Definisce un'interfaccia funzionale con la quale possiamo creare le strategie.
 * 
 * @author edoardo
 *
 */
@FunctionalInterface
public interface StrategyFactory {

	/**
	 * Permette di creare una strategia
	 * @return <code>StrategyInterface</code>
	 */
	StrategyInterface createStrategy();
	
}
