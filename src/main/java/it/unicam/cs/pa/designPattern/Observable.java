/**
 * 
 */
package it.unicam.cs.pa.designPattern;

import java.util.ArrayList;
import java.util.List;

import it.unicam.cs.pa.model.Row;
import it.unicam.cs.pa.view.ViewInterface;

/**
 * <b>Responsabilità : </b> Permette di definire una relazione uno-molti tra gli oggetti
 * 		 senza rendere gli oggetti strettamente accoppiati. Observable è una classe che
 * 		 può essere osservata.
 * 
 * @author edoardo
 * @param <T>
 *
 **/
public class Observable<T extends ViewInterface>{

	/**
	 * Parametro che identifica se è avvenuto un cambiamento
	 */
	private boolean change;
	
	/**
	 * Lista degli Observer che saranno le viste che rispettano il contratto definito da ViewInterface
	 */
	private List<T> listOfObserver;
	
	/**
	 * Costruttore dell'Observable
	 */
	public Observable() {
		this.change = false;
		this.listOfObserver = new ArrayList<T>();
	}
	
	/**
	 * Questo metodo aggiunge un Observer nell'interna lista degli Observer
	 * @param obs - observer da aggiungere
	 **/
	public void addObserver(T obs) {
		this.listOfObserver.add(obs);
	}
	
	/**
	 * Elimina un Observer dalla lista interna degli Observer
	 * @param obs - observer da eliminare
	 **/
	public void deleteObserver(Observer obs) {
		this.listOfObserver.remove(obs);
	}
	
	/**
	 * Questo metodo Elimina tutti gli Observer presenti nella lista interna degli Observer
	 */
	public void deleteObservers() {
		while(this.listOfObserver.size() != 0) {
			this.listOfObserver.remove(0);
		}
	}
	
	/**
	 * Questo metodo permette di conoscere il numero di Observer presenti della lista
	 * @return int - numero degli Observer
	 */
	public int countObservers() {		
		return this.listOfObserver.size();
	}
	
	/**
	 * Questo metodo permette di settare (TRUE) il flag interno che indica se è avvenuto un cambiamento
	 */
	public void setChanged() {
		this.change = true;
	}
	
	/**
	 * Questo metodo permette di cancellare (FALSE) il flag interno che indica se è avvenuto un cambiamento
	 */
	public void clearChanged() {
		this.change = false;
	}
	
	/**
	 * Questo metodo ritorna il valore del flag interno che indica se è avvenuto un cambiamento
	 * @return boolean - lo stato del flag
	 */
	public boolean hasChanged() {
		return this.change;
	}
	
	/**
	 * Questo metodo controlla il flag che indica se è avvenuto un cambiamento, in caso affermativo (TRUE),
	 * 		notifica il cambiamento a tutti gli Observer contenuti nella lista interna, chiamando il metodo update() 
	 */
	public void notifyObservers(ArrayList<Row> arg) {
		if(hasChanged()) {
			for(int c1 = 0; c1 < this.countObservers(); c1++) {
				this.listOfObserver.get(c1).update(arg);
			}
		}
	}


}
