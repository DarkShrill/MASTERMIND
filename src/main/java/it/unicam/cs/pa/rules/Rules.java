package it.unicam.cs.pa.rules;
/**
 * 
 * @author edoardo
 *
 */

import java.util.List;

/**
 *  <b> Responsabilità : </b>  Definisce il contratto che dovrà essere rispettato da quasiasi classe in grado di fare il SET delle regole. <br>
 * @author edoardo
 *
 */
public interface Rules {					
	
	/**
	 * Permette di ritornare i colori disponibili
	 * @return lista dei colori disponibili
	 */
	public List<String> getAvaiableColors();
	
	/**
	 * Ritorna il numero di tentativi che è possibile fare
	 * @return <code>int</code> - numero di tantativi
	 */
	public int getNumberOfSlots();

}
