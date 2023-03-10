package it.unicam.cs.pa.rules;
/**
 * 
 * @author edoardo
 *
 */

import java.util.List;

/**
 *  <b> Responsabilit√† : </b>  Definisce il contratto che dovr√† essere rispettato da quasiasi classe in grado di fare il SET delle regole. <br>
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
	 * Ritorna il numero di tentativi che √® possibile fare
	 * @return <code>int</code> - numero di tantativi
	 */
	public int getNumberOfSlots();

}
