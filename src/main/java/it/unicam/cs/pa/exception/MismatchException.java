/**
 * 
 */
package it.unicam.cs.pa.exception;

/**
 *<b> Responsabilità : </b> Permette di laciare un eccezione nel caso in cui inseriamo
 *		dei dati non corretti. Ad esempio se si sta attendendo un INT ma ci viene passato 
 *		una STRING.
 * 
 * @author edoardo
 *
 */
public class MismatchException extends GeneralException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MismatchException(String message) {
		super(message);
	}

}
