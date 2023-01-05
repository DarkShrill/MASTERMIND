/**
 * 
 */
package it.unicam.cs.pa.test.designPattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.unicam.cs.pa.designPattern.Observable;
import it.unicam.cs.pa.model.Row;
import it.unicam.cs.pa.view.MainView;
import it.unicam.cs.pa.view.ViewInterface;

/**
 * @author edoardo
 *
 */
public class ObservableTest {

    @Before
    public void setUp() throws Exception {
    }
    
    // TEST IL COSTRUTTORE
    @Test
    public void testObservable() {
    	Observable<ViewInterface> obs = new Observable<ViewInterface>();
    	
    	assertNotNull(obs);
    }
    
    // TEST AGGIUNTA OBSERVER
    @Test
    public void testAddObserver() {
    	Observable<ViewInterface> obs = new Observable<ViewInterface>();
    	ViewInterface view1 = new MainView();
    	ViewInterface view2 = new MainView();
    	
    	obs.addObserver(view1);
    	obs.addObserver(view2);
    	
    	assertEquals(2,obs.countObservers());
    	
    }
    
    // TEST ELIMINAZIONE DI UN OBSERVER
    @Test
    public void testDeleteObserver() {
    	Observable<ViewInterface> obs = new Observable<ViewInterface>();
    	ViewInterface view1 = new MainView();
    	ViewInterface view2 = new MainView();
    	ViewInterface view3 = new MainView();
    	
    	obs.addObserver(view1);
    	obs.addObserver(view2);
    	obs.addObserver(view3);
    	
    	obs.deleteObserver(view2);
    	
    	assertEquals(2,obs.countObservers());
    }
    
    // TEST ELIMINAZIONE TUTTI GLI OBSERVER
    @Test
    public void testDeleteAllObserver() {
    	Observable<ViewInterface> obs = new Observable<ViewInterface>();
    	ViewInterface view1 = new MainView();
    	ViewInterface view2 = new MainView();
    	
    	obs.addObserver(view1);
    	obs.addObserver(view2);
    	
    	obs.deleteObservers();
    	assertEquals(0, obs.countObservers());
    }
    
    // TEST PER VEDERE SE FUNZIONA LA CONTA DEGLI OBSERVER
    @Test
    public void testCountObserver() {
    	Observable<ViewInterface> obs = new Observable<ViewInterface>();
    	ViewInterface view1 = new MainView();
    	ViewInterface view2 = new MainView();
    	
    	obs.addObserver(view1);
    	obs.addObserver(view2);
    	
    	assertEquals(2, obs.countObservers());
    	
    	ViewInterface view3 = new MainView();
    	
    	obs.addObserver(view3);
    	
    	assertEquals(3, obs.countObservers());
    }
    
    // TEST PER VEDERE SE VIENE MESSO A TRUE IL FLAG DI CHANGE
    @Test
    public void testSetChange() {
    	Observable<ViewInterface> obs = new Observable<ViewInterface>();
    	obs.setChanged();
    	
    	assertEquals(true, obs.hasChanged());
    }
    
    //TEST PER VEDERE SE MESSO A FALSE IL FLAG DI CHANGE
    @Test
    public void testClearChange() {
    	Observable<ViewInterface> obs = new Observable<ViewInterface>();
    	obs.setChanged();
    	assertEquals(true, obs.hasChanged());
    	obs.clearChanged();
    	assertEquals(false, obs.hasChanged());
    	
    }
    
    // TEST PER VEDERE SE HASCHANGED FUNZIONA
    @Test
    public void testHasChanged() {
    	Observable<ViewInterface> obs = new Observable<ViewInterface>();
    	obs.setChanged();
    	assertEquals(true, obs.hasChanged());
    	obs.clearChanged();
    	assertEquals(false, obs.hasChanged());
    }
    
    // TEST PER VEDERE SE NOTIFYOBSERVER FUNZIONA
    @Test
    public void testNotifyObserver() {
    	Observable<ViewInterface> obs = new Observable<ViewInterface>();
    	
    	ViewInterface view1 = new MainView();
    	
    	obs.addObserver(view1);
    	
    	obs.setChanged();
    	
    	ArrayList<Row> list = new ArrayList<Row>();
    	
    	ArrayList<String>listForRow1 = new ArrayList<String>();
    	ArrayList<String>listForRow2 = new ArrayList<String>();
    	ArrayList<String>listForRow3 = new ArrayList<String>();
    	
    	listForRow1.add("A");
    	listForRow1.add("B");
    	listForRow1.add("C");
    	listForRow1.add("D");
    	list.add(new Row(listForRow1));
    	listForRow2.add("C");
    	listForRow2.add("C");
    	listForRow2.add("C");
    	listForRow2.add("C");
    	list.add(new Row(listForRow2));
    	listForRow3.add("X");
    	listForRow3.add("X");
    	listForRow3.add("X");
    	listForRow3.add("X");
    	list.add(new Row(listForRow3));
    	
    	obs.notifyObservers(list);
    }
}
