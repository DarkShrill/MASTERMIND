/**
 * 
 */
package it.unicam.cs.pa.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.model.Row;

/**
 * @author edoardo
 *
 */
public class RowTest {

	
	@Before
    public void init() throws Exception {
    }
	
	// TESTO IL COSTRUTTORE DELLA MIA ROW
	@Test
	public void testRow() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		
		Row riga = new Row(list);
		
		assertNotNull(riga);
	}
	
	@Test
	public void testCheck() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		
		Row riga = new Row(list);
		
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("D");
		list2.add("E");
		list2.add("F");
		list2.add("G");
		
		Row riga2 = new Row(list2);
		
		assertEquals(true,riga.equals(riga.check(riga2)));
	}
	
	@Test
	public void testSize() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		
		Row riga = new Row(list);
		
		assertEquals(4, riga.size());
	}
	
	@Test
	public void testGetNumberCorrectposition() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		
		Row riga = new Row(list);
		
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("D");
		list2.add("Y");
		list2.add("Y");
		list2.add("Y");
		
		Row riga2 = new Row(list2);
		
		riga.check(riga2);
		
		assertEquals(1,riga.getNumberOfCorrectPosition());
	}
	
	@Test
	public void testGetNumberCorrectColor() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		
		Row riga = new Row(list);
		
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("W");
		list2.add("G");
		list2.add("D");
		list2.add("Y");
		
		Row riga2 = new Row(list2);
		
		riga.check(riga2);
		
		assertEquals(2,riga.getNumberOfCorrectColor());
	}
}
