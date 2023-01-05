/**
 * 
 */
package it.unicam.cs.pa.strategy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unicam.cs.pa.exception.RegistrySysntaxException;



/**
 * <b> Responsabilità : </b> Definisce  "l'archivio" che contiene tutti i tipi di strategia che possono essere istanziati, presi dal file di testo '/strategyDataCreation/strategyData.txt'<br>
 * 
 * @author edoardo
 *
 */
public class StrategyArchive {

	/**
	 * Istanza della mia classe
	 */
	private static StrategyArchive 			instance;
	
	/**
	 * Mappa contenente l'associazione nome - Strategia
	 */
	private Map<String,StrategyFactory> 	archive;
	
	/**
	 * Lista delle strategie che si possono selezionare
	 */
	private List<String>					nameTypeOfStrategy;
	
	/**
	 * Costuttore
	 */
	private StrategyArchive() {
		this.archive = new HashMap<String,StrategyFactory>();
		this.nameTypeOfStrategy = new ArrayList<String>();
		
		
		this.inizializate();
	}

	/**
	 * Inizializza il mio archivio prendendo i dati dal path '/strategyDataCreation/strategyData.txt'
	 */
	private void inizializate(){
		try {
			this.loadStrategy(new File("strategyDataCreation/strategyData.txt"));
		} catch (IOException e) {
		}
	}

	/**
	 * Ritorna l'istanza del mio oggetto
	 * @return <code>StrategyArchive</code>
	 */
	public static StrategyArchive getInstance() {
		if(instance == null) {
			instance = new StrategyArchive();
		}
		return instance;
	}
	
	/**
	 * 
	 * Ritorna la strategia a seconda del nome inserito
	 * @param nameTypeOfStrategy - Nome della strategia
	 * @return <code>StrategyFactory</code> - Strategia
	 */
	public StrategyFactory getStrategy(String nameTypeOfStrategy) {
		return this.archive.get(nameTypeOfStrategy);
	}
	
	/**
	 * Permette di caricare le strategie passando il file in cui sono contenuti
	 * @param file
	 * @throws IOException
	 */
	public void loadStrategy(File file)throws IOException{
		List<String> data = Files.readAllLines(file.toPath(),Charset.defaultCharset());
		data.stream().forEach(l -> this.register(l));
	}

	/**
	 * Permette di registrare una strategia
	 * @param registryLine - contenente il nome della strategia e il nome della classe separato da un solo spazio
	 */
	private void register(String registryLine ) {
		try {
			String[] elements = registryLine.split(" ");		
			if (elements.length == 2) {
				this.register( elements[0], elements[1] );
				return ;
			} 
			throw new RegistrySysntaxException("\n\n########################################################################\n\n"
												+ "		ERRORE NEL FILE CONTENUTO IN /strategyDataCreation \n"
												+ "		QUALCOSA E' STATO SCRITTO IN MODO SBAGLIATO"
												+ "\n	 		...  RICONTROLLA ... \n\n"
												+ "########################################################################\n\n");
			} catch (RegistrySysntaxException e) {
				e.printStackTrace();
				System.exit(-1);
			}catch (Exception e) {
				
			}
	}

	/**
	 * Permette di registrare a seconda del nome e della classe, una nuova strategia
	 * @param name - nome della strategia
	 * @param className - nome della classe
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 */
	private void register(String name, String className) throws IOException,ClassNotFoundException,NoSuchMethodException,InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException{
		Class<? extends StrategyFactory> clazz = Class.forName(className).asSubclass(StrategyFactory.class);
		this.register(name,clazz.getConstructor().newInstance());
	}

	/**
	 * Aggiungo al mio archivio delle strategie una nuova strategia
	 * @param name - Nome strategia
	 * @param newInstance - Strategia
	 */
	private void register(String name, StrategyFactory newInstance) {
		this.archive.put(name, newInstance);
		this.nameTypeOfStrategy.add(name);
	}

	/**
	 * Permette di ritornare la lista che contiene il nome delle strategie.
	 * @return lista dei nomi delle strategie
	 */
	public List<String> getNameTypeOfStrategy(){
		return this.nameTypeOfStrategy;
	}
	
	
}
