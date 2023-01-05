/**
 * 
 */
package it.unicam.cs.pa.exception;

/**
 *<b> Responsabilità : </b> Permette di laciare un eccezione generale.
 * @author edoardo
 *
 */
public class GeneralException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GeneralException(String message){
		super(message);
	}
	
}
