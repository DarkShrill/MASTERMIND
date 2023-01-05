/**
 * 
 */
package it.unicam.cs.pa.exception;

/**
 * <b> Responsabilità : </b> Lancia un eccezione nel caso in cui non sia rispettato il formato
 * 			dei file personificabili (/rulesData/ -- /playerDataCreation/ -- /strategyDataCreation/)
 * @author edoardo
 *
 */
public class RegistrySysntaxException extends GeneralException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistrySysntaxException(String message) {
		super(message);
	}

}
