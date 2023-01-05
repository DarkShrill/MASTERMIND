/**
 * 
 */
package it.unicam.cs.pa.exception;

/**
 * <b> Responsabilità : </b> Permette di laciare un eccezione del caso in cui il file preso in cosiderazione
 * 			da una classe non rispetti determinati parametri.
 * 
 * @author edoardo
 *
 */
public class BadFileException extends GeneralException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadFileException(String message) {
		super(message);
	}

}
