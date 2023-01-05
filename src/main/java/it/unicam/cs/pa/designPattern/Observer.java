/**
 * 
 */
package it.unicam.cs.pa.designPattern;

import java.util.ArrayList;

import it.unicam.cs.pa.model.Row;

/**
 * <b> Responsabilità : </b> Permette di definire una relazione uno-molti tra gli oggetti
 * 		 senza rendere gli oggetti strettamente accoppiati. Observer definisce un osservatore.
 * 
 * @author edoardo
 *
 */
public interface Observer {
	
	/**
	 * 	Questo metodo verrà chiamato quando sarà verificato un cambiamento
	 * 		nello stato di un observable.
	 * @param arg - "Tavolo" da gioco da aggiornare
	 */
	public void update(ArrayList<Row> arg);

}
