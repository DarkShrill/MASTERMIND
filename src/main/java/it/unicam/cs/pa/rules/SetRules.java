/**
 * 
 */
package it.unicam.cs.pa.rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unicam.cs.pa.exception.BadFileException;
import it.unicam.cs.pa.exception.FileOpenErrorException;
import it.unicam.cs.pa.mode.GameDifficulty;
import it.unicam.cs.pa.mode.GameMode;
import it.unicam.cs.pa.view.ViewInterface;



/**
 * <b> Responsabilità : </b> Definisce le regole 
 * 
 * @author edoardo
 *
 */
public class SetRules implements Rules{

	/**
	 * Istanza della classe
	 */
	private static SetRules 		instance = null;
	
	/**
	 * Numero di colori
	 */
	private int 				numberOfColor;		
	
	/**
	 * Lista contente i colori disponibili
	 */
	private List<String> 	colors = new ArrayList<String>();
	
	/**
	 * Numero di tentativi massimi
	 */
	private int 				numberOfTry;
	
	/**
	 * Lista contente i nomi delle varie difficoltà lette
	 */
	private List<String> 	mode = new ArrayList<String>();
	
	/**
	 * Numero del file selezionato dall'utente
	 */
	private int 				numberOfFileSelected;
	
	/**
	 * Lista di tutti i file contenuti della cartella "rulesData/"
	 */
	private String[] 			listOfFile;
	
	/**
	 * Interfaccia grafica
	 */
	private ViewInterface	view;
	

	/**
	 * Costruttore 
	 * @param passedView - Interfaccia Grafica
	 */
	private SetRules(ViewInterface passedView) {
		
		BufferedReader 	fileIn = null;
		String		   	str;
		String			pathReaded;
		
		view = passedView;
		
		//LEGGO TUTTI I FILE ALL'INTERNO DELLA CARTELLA "/rulesData"
		
		readAndSelectFile();
		
		pathReaded = listOfFile[numberOfFileSelected];

		try {
			fileIn = openFileFromSting(pathReaded);
		}catch (FileOpenErrorException e) {
			view.print("\n\n"  + e.getMessage() + "\n\n");
			System.exit(-1);
		}
		
		try {
			while((str = fileIn.readLine()) != null) {
				//AGGIUNGO TUTTE LE MODALITA CHE LEGGO NEL FILE
				if(str.contains("LABEL=")){
					str = (String) str.substring(6,str.length());
					mode.add(str);
				}
			}
		}catch (Exception e) {
		
		}
	}
	
	/**
	 * Questo metodo mi permette di leggere tutti i file presenti nella cartella "/rulesData/"
	 */
	private void readAndSelectFile() {
		File			path;
		
		path = new File("rulesData/");
		listOfFile = path.list(); //creo un array di stringhe e lo riempio con la lista dei files presenti nella directory
		view.print("\n ~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~°~ \n\n");
		view.print("A SEGUIRE, TROVERETE TUTTI I FILE AGGIUNTI ALLA CARTELLA \"/rulesData\"\n");
		for (int i=0;i<listOfFile.length;i++){
			view.print(i + ") " + listOfFile[i]+"\n");
		}

		userSelectFile();
	}

	/**
	 * Questo metodo mi permette di far selezionare all'utente un file tra quelli presenti nella cartella "/rulesData/"
	 */
	private void userSelectFile() {
		view.print("\nDIGITARE IL NUMERO DI DEL FILE PER SELEZIONARE LE REGOLE DEL GIOCO : \n");
		view.print("---> ");

		numberOfFileSelected = view.getDataFromConsole(listOfFile.length - 1);	
	}

	/**
	 * Mi permette di creare l'oggetto SetRules
	 * @param view - Interfaccia Grafica
	 */
	private static void createInstance(ViewInterface view) {
		if(instance == null) {
			instance = new SetRules(view);
		}
	}
	
	/**
	 * Mi ritorna l'istanza dell'oggetto
	 * @param view - Interfaccia Grafica
	 * @return <code>SetRules</code> - istanza
	 */
	public static SetRules getInstance(ViewInterface view) {
		createInstance(view);
		return instance;
	}
	
	/**
	 * Mi ritorna l'istanza dell'oggetto
	 * @return <code>SetRules</code> - istanza
	 */
	public static SetRules getInstance() {
		if(instance != null) {
			return instance;
		}
		return null;
	}
	
	/**
	 * Ritorna la lista con i nomi delle varie difficoltà lette da file
	 * @return lista con i nomi delle varie difficoltà
	 */
	public List<String> getMode() {
		return mode;
	}
	
	@Override
	public List<String> getAvaiableColors() {
		return this.colors;
	}


	@Override
	public int getNumberOfSlots() {
		return this.numberOfTry;
	}

	/**
	 * Permette di settare la difficolta del gioco a seconda del numero del file selezionato dall'utente
	 * @param data - indice che indica il numero del file scelto
	 * @return <code>GameDifficulty</code>
	 * @throws BadFileException
	 */
	public GameDifficulty setDifficulty(int data) throws BadFileException {
		
		String			path;
		BufferedReader 	fileIn = null;
		
		path = listOfFile[numberOfFileSelected];
		 
		if(colors.size() != 0)
			colors.clear();
		if(numberOfColor != 0)
			numberOfColor = 0;
		
		try {
			fileIn = openFileFromSting(path);
		}catch (FileOpenErrorException e) {
			view.print("\n\n"  + e.getMessage() + "\n\n");
		}

		try {
			readFileAndSetData(fileIn,data);
		}catch (Exception e) {
		}

		// CONTROLLO SE HO LETTO UN FILE COMPLETAMENTE SBAGLIATO
		if((numberOfColor == 0) || (colors == null))
		{
			//HO UN FILE SBALLATO.
			throw new BadFileException("\n\n################################################################################\n\n"
										+ " 			HAI SELEZIONATO UN FILE 'RULES' (/rulesData)\n"
										+ "			NON VALIDO PER QUESTO GIOCO.\n"
										+ "			IL GIOCO VERRA' CHIUSO.\n\n"
										+ "################################################################################\n\n");
		}
		// CONTROLLO SE TUTTI I DATI CHE HO LETTO SONO COERENTI E CORRETTI
		if(numberOfColor != colors.size())
		{
			//HO UN FILE SBALLATO.
			throw new BadFileException("\n\n################################################################################\n\n"
										+ " 			HAI SELEZIONATO UN FILE 'RULES' (/rulesData)\n"
										+ "			NON VALIDO PER QUESTO GIOCO.\n"
										+ "			IL GIOCO VERRA' CHIUSO.\n\n"
										+ "################################################################################\n\n");
		}
		
		return new GameMode(getMode().get(data),numberOfColor,colors,numberOfTry);
	}	
	
	/**
	 * Permette di leggere i dati provenienti dal file selezionato dall'utente e permette di per poter settare 
	 * 		le varie regole del gioco lette a seconda della LABEL scelta dall'utente.
	 * @param fileIn - dati provenienti dal file di testo
	 * @param data - indice che identifica quale file è stato selezionato dall'utente
	 * @throws Exception
	 * @throws IOException
	 */
	private void readFileAndSetData(BufferedReader fileIn,int data) throws Exception, IOException {
		
		String			str;
		
		while((str = fileIn.readLine()) != null) {
			//AGGIUNGO TUTTE LE MODALITA CHE LEGGO NEL FILE
			if(str.contains("LABEL=" + getMode().get(data))){
				str = fileIn.readLine();
				
				//************	NUMBER OF COLORS  ***************
				str = (String) str.subSequence(17, str.length());
				numberOfColor = Integer.parseInt(str);
				str = fileIn.readLine();
				
				//************	AVAIABLE COLORS  ***************
				str = (String) str.subSequence(16, str.length());
				for(int c = 0; c < str.length(); c+=2) 
					colors.add(str.substring(c,c+1));
				str = fileIn.readLine();
				
				//************	NUMBER OF TRY  ***************
				str = (String) str.subSequence(14, str.length());
				numberOfTry = Integer.parseInt(str);
				
				
				break;
			}

		}
	}
	
	/**
	 * Permette di aprire un file e ricavarmi i dati da un path passato come parametro formale
	 * @param path - Path del file
	 * @return <code>bufferReader</code> - dati provenienti dal file
	 * @throws FileOpenErrorException
	 */
	private BufferedReader openFileFromSting(String path) throws FileOpenErrorException {
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader("rulesData/" + path));
		}catch (FileNotFoundException e) {
			throw new FileOpenErrorException("NON SONO RIUSCITO AD APRIRE IL FILE\n"
												+ "##1) IL GIOCO VERRA' TERMINATO\n"
												+ "##2) CAMBIA IL PERCORSO DEL FILE");
		}
		return file;
	}
	

	
}



