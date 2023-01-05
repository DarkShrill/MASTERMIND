/**
 * 
 */
package it.unicam.cs.pa.exception;

/**
 * <b> Responsabilità : </b> Permette di laciare un eccezione del caso in cui il file preso in cosiderazione
 * 			da una classe non riusciamo ad aprirlo.
 * @author edoardo
 *
 */
public class FileOpenErrorException extends GeneralException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileOpenErrorException(String message) {
		super(message);
	}

}
