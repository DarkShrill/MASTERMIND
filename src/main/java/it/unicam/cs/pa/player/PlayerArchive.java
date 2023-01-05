/**
 * 
 */
package it.unicam.cs.pa.player;

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
 * <b> Responsabilità : </b> Definisce  "l'archivio" che contiene tutti i tipi di giocatori che possono essere istanziati, presi dal file di testo '/playerDataCreation/data.txt'<br>
 * 
 * 
 * @author edoardo
 *
 */
public class PlayerArchive {
	
	/**
	 * Istanza della mia classe
	 */
	private static PlayerArchive 		instance;
	
	/**
	 * Mappa che contenente tutti i giocatori disponibili. Gli elementi sono
	 * 		contraddistinti da una chiave(String) e dal suo valore (PlayerFactory)
	 */
	private Map<String,PlayerFactory> 	archive;
	
	/**
	 * Lista con i nomu dei giocatori che si possono selezionare
	 */
	private List<String>				nameTypeOfPlayer;
	
	/**
	 * Costruttore
	 */
	private PlayerArchive(){
		this.archive = new HashMap<String,PlayerFactory>();
		this.nameTypeOfPlayer = new ArrayList<String>();
		this.inizializate();
	}

	/**
	 * Inizializza il mio archivio prendendo i dati dal path  "/playerDataCreation/data.txt "
	 */
	private void inizializate(){
		try {
			this.loadPlayer(new File("playerDataCreation/data.txt"));
		} catch (IOException e) {
		}
	}

	/**
	 * Ritorna l'istanza del mio oggetto
	 * @return istanza - <code>PlayerArchive</code>
	 */
	public static PlayerArchive getInstance(){
		if(instance == null) {
			instance = new PlayerArchive();
		}
		return instance;
	}
	
	/**
	 * 
	 * Ritorna il giocatore a seconda del nome inserito
	 * @param nameTypeOfPlayer  - Nome del giocatore
	 * @return <code>PlayerFactory</code> - Giocatore
	 */
	public PlayerFactory getPlayer(String nameTypeOfPlayer) {
		return this.archive.get(nameTypeOfPlayer);
	}
	
	/**
	 * Permette di caricare i giocatori passando il file in cui sono contenuti
	 * @param file - file contenente i dati
	 * @throws IOException
	 */
	public void loadPlayer(File file) throws IOException{
		List<String> data = Files.readAllLines(file.toPath(),Charset.defaultCharset());
		data.stream().forEach(l ->this.register(l));
	}

	/**
	 * Permette di registrare un giocatore 
	 * @param registryLine - contenente il nome del giocatore e il nome della classe separato da un solo spazio
	 */
	private void register(String registryLine ) {
		try {
			String[] elements = registryLine.split(" ");		
			if (elements.length == 2) {
				this.register( elements[0], elements[1] );
				return ;
			} 
			throw new RegistrySysntaxException("\n\n########################################################################\n\n"
												+ "		ERRORE NEL FILE CONTENUTO IN /playerDataCreation \n"
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
	 * Permette di registrare a seconda del nome e della classe, un nuovo giocatore
	 * @param name - nome del giocatore
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
		Class<? extends PlayerFactory> clazz = Class.forName(className).asSubclass(PlayerFactory.class);
		this.register(name,clazz.getConstructor().newInstance());
	}

	/**
	 * Aggiungo al mio archivio di giocatori un nuovo giocatore.
	 * @param name - Nome giocatore
	 * @param newInstance - Classe del giocatore
	 */
	private void register(String name, PlayerFactory newInstance) {
		this.archive.put(name, newInstance);
		this.nameTypeOfPlayer.add(name);
	}

	/**
	 * Permette di ritornare la lista che contiene il nome dei giocatori.
	 * @return lista dei nomi dei giocatori
	 */
	public List<String> getNameTypeOfPlayer(){
		return this.nameTypeOfPlayer;
	}
	

}
