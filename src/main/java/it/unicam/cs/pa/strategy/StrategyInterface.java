/**
 * 
 */
package it.unicam.cs.pa.strategy;

import it.unicam.cs.pa.mode.GameDifficulty;
import it.unicam.cs.pa.model.Row;

/**
*
* <b> Responsabilit√† : </b> Definisce il contratto che dovr√† essere rispettato da qualsiasi tipo di strategia. <br>
* 
* 
* @author edoardo
*
*/
public interface StrategyInterface {

	/**
	 * Ritorna la riga risultante a senconda della strategia applicata
	 * @param difficult - difficolta
	 * @param Key - Chiave da decodificare
	 * @return <code>Row</code> - riga risultante a seconda della strategia applicata
	 */
	public Row getStrategy(GameDifficulty difficult,Row Key);
	
}
