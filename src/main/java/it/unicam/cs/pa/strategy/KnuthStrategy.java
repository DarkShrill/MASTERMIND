/**
 * 
 */
package it.unicam.cs.pa.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import it.unicam.cs.pa.mode.GameDifficulty;
import it.unicam.cs.pa.model.Row;

/**
 * <b>Responsabilità : </b> Definisce la strategia proposta da Knuth.
 * 
 * @author edoardo
 *
 */
public class KnuthStrategy implements StrategyInterface {

	/**
	 * Numero di 'piroli' che posso inserire in una Row
	 */
    private int CODE_LENGTH;	
    
    /**
     * Numero di colori presenti a seconda del file selezionato
     */
    private int NUM_COLORS;	

    /**
     * Numero delle possibili combinazioni
     */
    private int NUM_OF_POSSIBLE_GUESSES;	
	
    /**
     * la chiave da decodificare inserita dall'utente
     */
	private int[] key;
	
	/**
	 * Codice ottenuto dall'algoritmo
	 */
	int[] guess = new int[4];
	
	/**
	 * Risultato del codice tentato dall'algoritmo [0] --> Colore giusto e posizione giusta [1] --> Colore giusto, posizione sbaglaita
	 */
	int[] score = new int[2];
	
	/**
	 * Numero di tentativi fatti dall'algoritmo
	 */
	int numOfGuesses = 0;
	
	/**
	 * Contenitore di tutte le possibili combinazioni
	 */
	private int[][] potAns; 

	
	private HashSet<Integer> usedGuess = new HashSet<>();
	private HashSet<Integer> candidate = new HashSet<>();


	@Override
	public Row getStrategy(GameDifficulty difficult, Row Key) {

		this.key 					= new int[4];
		this.CODE_LENGTH 			= 4;
		this.NUM_COLORS 			= difficult.getAvaiableColors().size();
		NUM_OF_POSSIBLE_GUESSES 	= (int)Math.pow(NUM_COLORS, 4);
		this.key 					= decriptKey(Key,difficult);
		
		if(this.guess[0] == 0) {
			this.potAns = new int[NUM_OF_POSSIBLE_GUESSES][4];
			initKnuthAlgo();
		}else {
			continueKnuthAlgo();
		}
		
		return encriptyKey(guess,difficult);
	}

	/**
	 * Serve per poter convertire i dati generati dall'algoritmo di Knuth.
	 * @param guess - Codice da convertire
	 * @param difficult - difficolta del gioco
	 * @return <code>Row</code>
	 */
	private Row encriptyKey(int[] guess,GameDifficulty difficult) {
		ArrayList<String> colorAvaiable = new ArrayList<String>(difficult.getAvaiableColors());
		ArrayList<String> localKey = new ArrayList<String>();

		
		localKey.add(colorAvaiable.get(guess[0] - 1));
		localKey.add(colorAvaiable.get(guess[1] - 1));
		localKey.add(colorAvaiable.get(guess[2] - 1));
		localKey.add(colorAvaiable.get(guess[3] - 1));
		
		
		return new Row(localKey);
	}

	/**
	 * Continuo l'eseguzione dell'algoritmo di Knuth
	 */
	private void continueKnuthAlgo() {
		
		score = compareGuess(this.key, guess); 	/* (3) */

		condense(guess, score);
		if (candidate.size() == 1) {
			for (Integer index : candidate) {
				guess = potAns[index];
			}
		} else {
			guess = nextGuess();
		}

		numOfGuesses++; 
		
	}

	/**
	 * Inizilizzo l'algoritmo di Knoth
	 */
	private void initKnuthAlgo() {

		score[0] = 0;
		score[1] = 0;
		
		generateS();	/* (1) */
		
		guess[0] = 1;	/* (2) */
		guess[1] = 1;	/* (2) */
		guess[2] = 2;	/* (2) */
		guess[3] = 2;	/* (2) */
		
		usedGuess.add(11);
		numOfGuesses++; 
		
	}

	/**
	 * Converto la chiave Key in dati utilizzabili per l'algoritmo di Knuth
	 * @param Key	- Chiave da convertire
	 * @param difficult - Difficoltà del gioco
	 * @return <code>int[]</code>
	 */
	private int[] decriptKey(Row Key,GameDifficulty difficult) {
		ArrayList<String> colorAvaiable = new ArrayList<String>(difficult.getAvaiableColors());
		int[] localKey = new int[4];
		int counter = 0;
		
		for (String i : colorAvaiable) {
			counter++;
			i = i.toUpperCase();
			for(int c1 = 0; c1 < CODE_LENGTH;c1++) {
				if(Key.getHoles().get(c1).compareTo(i) == 0) {
					localKey[c1] = counter;
				}
			}
		}
		
		return localKey;
		
	}
	
	
	
	/**
	 * Genera tutte le potenziali risposte per la risoluzione del gioco
	 * 
	 * 
	 * @effect Genera una lista di <code>MAXDECIMALVALUE</code> elementi che potrebbero essere delle
	 *         potenzioni risposte.
	 */
	private void generateS() {
		// inizializzo una variabile che utilizzero per inserire gli elementi all'interno dell'array
		int inputInt;
		// Loop per ogni potenziale risposta
		for (int i = 0; i < NUM_OF_POSSIBLE_GUESSES; i++) {
			// aggiungo i candidati
			candidate.add(i);

			inputInt = Integer.parseInt(Integer.toString(i, NUM_COLORS)) + 1111;

			for (int j = 0; j < 4; j++) {
				potAns[i][j] = Integer.valueOf(Character.digit(Integer.toString(inputInt).charAt(j), 10));
			}
		}
	}


	/**
	 * Setta tutte le soluzioni non corrette a zero 
	 * 
	 * @param inputGuess - è la nostra MainKey
	 * @param comparedResult - è il risultato della comparazione
	 */
	private  void condense(int[] inputGuess, int[] comparedResult) {
		for (int entryCheck = 0; entryCheck < NUM_OF_POSSIBLE_GUESSES; entryCheck++) {
			if (candidate.contains(entryCheck)) {
				int[] result = compareGuess(potAns[entryCheck], inputGuess);
				if (!Arrays.equals(result, comparedResult)) {
					candidate.remove(entryCheck);
				}
			}
		}
	}
	
	/**
	 * Genera la prossima ipotesi su quello che è gia stato eliminato.
	 * 
	 * @return <code>int[]</code> - la prossima ipotesi
	 */
	private  int[] nextGuess() {
		int[] next = new int[4];
		int nextIndex = 0;
		// inizializzo i contatori per il minimo degli elementi rimossi
		int potMin = 0;
		int maxScore = -1;

		for (int count = 0; count < NUM_OF_POSSIBLE_GUESSES; count++) {
			if (usedGuess.contains(count)) {
				continue;
			}
			// trovo il minimo eliminato in questo set
			potMin = nextCondenseCounter(potAns[count]);
			// Se il nuovo potenziale minimo è aggiore di quello corrente,lo sostituisco
			if (potMin > maxScore) {
				maxScore = potMin;
				next = potAns[count];
				nextIndex = count;
			}
		}
		// return la prossima ipotesi
		usedGuess.add(nextIndex);
		return next;
	}

	/**
	 * Genera il minimo numero di ipotesi eliminate per ogni potenziale input di ipotesi
	 * 
	 * @param potGuess  - la potenziale ipotesi che verrà testata
	 * @return <code>int</code> - la minima quantità di ipotesi rimosse
	 */
	private  int nextCondenseCounter(int[] potGuess) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int numElements = 0;
		for (int potRespCheck = 0; potRespCheck < NUM_OF_POSSIBLE_GUESSES; potRespCheck++) {
			if (candidate.contains(potRespCheck)) {
				int[] result = compareGuess(potAns[potRespCheck], potGuess);
				int resultIndex = result[0] * 10 + result[1];
				map.put(resultIndex, 1 + map.getOrDefault(resultIndex, 0));
				numElements++;
			}
		}
		int highestHits = 0;
		for (Integer key : map.keySet()) {
			highestHits = Math.max(map.get(key), highestHits);
		}
		// return il minimo delle ipotesi rimosse
		return numElements - highestHits;
	}
	
	/**
	 * Confronta l'ipotesi generata con la MainKey
	 * 
	 * @param correct - La mainKey
	 * @param guess - la nostra ipotesi
	 * @return <code>int[]</code> - un array formato da soli due elementi. Il primo elemento è il numero dei colori che abbiamo indovianoto
	 * 		   sia la posizione che il colore, il secondo è il numero dei colori che abbiamo indovianoto ma in posizione
	 * 		   sbagliata.
	 */
	private int[] compareGuess(int[] correct, int[] guess) {
		int[] result = new int[2];
		//Input utente sballato
		if (correct == null || guess == null || correct.length != guess.length || correct.length != 4) {
			return result;
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			if (correct[i] == guess[i]) {
				result[0]++; // Aumento il numero dei colori correnti nella posizione corretta.
			}
			map.put(correct[i], 1 + map.getOrDefault(correct[i], 0));
		}
		for (int i = 0; i < 4; i++) {
			if (map.containsKey(guess[i])) {
				result[1]++;// Aumento il numero dei colori correnti .
				int count = map.get(guess[i]);
				if (count == 1) {
					map.remove(guess[i]);
				} else {
					map.put(guess[i], count - 1);
				}
			}
		}

		//Siccome per ogni colore giusto nella posizione corretta è anche aggiunto ai colori corretti, dobbiamo sottrarre
		// il numero dei colori correnti al numero di colori correnti e nella posizione giusto per avere un corrento risultato
		// del numero di colori corretti ma nella posizione sbagliata.
		result[1] -= result[0];
		return result;
	}
	
    
}
