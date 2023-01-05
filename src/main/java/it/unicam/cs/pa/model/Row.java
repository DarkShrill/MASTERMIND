/**
 * 
 */
package it.unicam.cs.pa.model;

import java.util.ArrayList;


/**
 * <b> Responsabilità : </b> definisce una riga contenente i vari 'piroli'
 * 
 * @author edoardo
 *
 */
public class Row{
	
	/**
	 *  Rappresenta il numero di colori corretti ma che si trovano della posizione sbagliata.
	 */
	private int numberOfCorrectColor = 0;
	
	/**
	 * Rappresenta il numero di colori correnti che si trovano nella posizione corretta.
	 */
	private int numberOfCorrectPositionColor = 0;
	
	/**
	 * Piroli all'interno della riga
	 */
	private ArrayList<String> holes;

	/**
	 * Costruttore che permette di aggiungere i piroli alla riga passandogli direttamente una lista prefatta.
	 * @param holes - lista di stringhe contenenti i colori dei piroli.
	 */
	public Row(ArrayList<String> holes) {
		this.holes = holes;
	}
	
	/**
	 * Questo metodo ci permetti di analizzare la riga this e la riga passata come 
	 * 		parametro formale e vedere quanti colori abbiamo indovinato e quanti colori
	 * 		abbiamo indovinato sia in colore che la posizione
	 * @param key - è la riga con il codice segreto da scoprire.
	 * @return la riga appena inserita.
	 */
	public Row check(Row key) {
		ArrayList<Integer> perfectMatches = new ArrayList<Integer>();
		ArrayList<Integer> colorMatches = new ArrayList<Integer>();
		
		// VADO A VEDERE SE HO QUALCHE HOLE APPARTENENTE A THIS UGUALE E NELLA 
		//	STESSA POSIZIONE DELLA KEY
		for(int i = 0; i < key.size(); i++){
			if(this.holes.get(i).equalsIgnoreCase(key.holes.get(i))){
				numberOfCorrectPositionColor++;
				perfectMatches.add(i);
			}
		}
		
		for(int i = 0; i < key.size(); i++) {
			// VERIFICO CHE IL VALORE I CHE STO PRENDENDO IN CONSIDERAZIONE
			//	NON SIA QUELLO NELLA POSIZIONE PERFETTA (numberOfCorrectPositionColor)
			if(!perfectMatches.contains(i)) {
				// VADO A VEDERE SE ALL'INTERNO DELLA NOSTRA RIGA THIS SI TROVA QUALCHE ELEMENTO DELLA
				//	KEY MA IN POSIZIONE NON CORRETTA.
				boolean colorMetched = false;
				for(int j = 0; j < this.holes.size(); j++) {
					// VADO A VEDERE SE LA RIGA THIS AD UNA DETERMINATA POSIZIONE SIA CONTENUA ALL'INTERNO
					//	DELLA KEY, INOLTRE NON DEVE ESSERE CONTENUTO L'ELEMENTO DELLA POSIZIONE J DI THIS ALL'INTERNO DEL PERFECTMATCH
					//	UNA VOLTA CHE HO TROVATO CHE UN DETEMINATO COLORE DELLA KEY E' PRESENTE IN THIS NON DEVO PIU ENTRARE DENTRO E INFINE
					//	SE NELLA MIA THIS HO PIU VOLTE LO STESSO COLORE E QUEST'ULTIMO E' STATO AGGIUNTO AL COLORMATCH ALLORA, NON LO CONSIDERO.
					if((this.holes.get(j).equalsIgnoreCase(key.holes.get(i)))&&(!perfectMatches.contains(j))&&(!colorMetched)&&(!colorMatches.contains(j))) {
						colorMatches.add(j);
						colorMetched = true;
						numberOfCorrectColor++;
					}
				}
			}
		}
		return this;
	}
	
	/**
	 * Ritorna la grandezza della riga (<code>Row</code>).
	 * @return int - grandezza della riga
	 */
	public int size() {
		return this.holes.size();
	}
	
	/**
	 * Ritorna la stringa che rappresenta la riga.
	 * @return String
	 */
	public String toString() {
		
		String str		= "\n";
		
		for(int i = 0; i < 4 ; i++) {
			str += "| " + this.holes.get(i).toUpperCase() +" |";
		}
		str += "  ---> (" + this.numberOfCorrectColor + " colori corretti ma in posizione sbagliata , " + this.numberOfCorrectPositionColor + " colori nella posizione giusta)";
		return str;
	}
	
	/**
	 * Ritorna il numero di colori che abbiamo indovinato sia il colore che la posizione
	 * @return int 
	 */
	public int getNumberOfCorrectPosition() {
		return this.numberOfCorrectPositionColor;
	}
	
	/**
	 * Ritorna il numero di colori che abbiamo indovinato il colore ma non la posizione
	 * @return int
	 */
	public int getNumberOfCorrectColor() {
		return this.numberOfCorrectColor;
	}
	/**
	 * Ritorna la lista dei piroli
	 * @return lista dei piroli
	 */
	public ArrayList<String> getHoles() {
		return holes;
	}
}
