

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ValleyTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ValleyTestC1
{
    private Valley valley;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        valley = new Valley(300, 300);
        
    }
    
    @Test
    public void deberiaAbrirViñedo(){
        valley.openVineyard("yellow", 10, 40);
        assertTrue(valley.ok());
    }
    
    @Test
    public void NoDeberiaAbrirViñedo(){
        valley.openVineyard("yellow", 10, 40);
        valley.openVineyard("yellow", 10, 40);
        assertFalse(valley.ok());
        
        valley.openVineyard("yellow", 30, 70);
        assertFalse(valley.ok());
        
        valley.openVineyard("magenta", 40, 90);
        assertFalse(valley.ok());
        
        valley.openVineyard("magenta", 30, 90);
        assertFalse(valley.ok());
        
        valley.openVineyard("magenta", 30, 30);
        assertFalse(valley.ok());
        
        valley.openVineyard("magenta", 30, 20);
        assertFalse(valley.ok());
        
        valley.openVineyard("magenta", 300, 350);
        assertFalse(valley.ok());
        
        valley.openVineyard("magenta", -20, 40);
        assertFalse(valley.ok());
        
        valley.openVineyard("v", 50, 80);
        assertFalse(valley.ok());
        
    }
    
    @Test
    public void deberiaCerrarViñedo(){
        valley.openVineyard("yellow", 10, 40);
        valley.closeVineyard("yellow");
        assertTrue(valley.ok());
        
    }
    
    @Test
    public void noDeberiaCerrarViñedo(){
        valley.openVineyard("yellow", 10, 40);
        valley.closeVineyard("v");
        assertFalse(valley.ok());
    }
    
    @Test
    
    public void deberiaHacerHueco(){
        int[] x1 = {20,10};
        int[] x2 = {100,100};
        valley.addTarp(x1,x2);
        valley.makePuncture(1,50);
        assertTrue(valley.getTarps().get(0).getPunctures().size()!=0);
        
        valley.makePuncture(1,51);
        assertTrue(valley.getTarps().get(0).getPunctures().size()==2);
        
        valley.makePuncture(1,600);
        assertTrue(valley.getTarps().get(0).getPunctures().size()==2);
    }
    
    @Test
    public void noDeberiaHacerHueco(){
        int[] x1 = {20,10};
        int[] x2 = {100,100};
        valley.addTarp(x1,x2);
        valley.makePuncture(1,500);
        assertTrue(valley.getTarps().get(0).getPunctures().size()==0);
    }
    
    @Test
    public void deberiaHaberLona(){
        int[] x1 = {50,150};
        int[] x2 = {100,100};
        int[] x3 = {10,15};
        int[] x4 = {10,90};
        valley.addTarp(x1,x2);
        valley.addTarp(x3,x4);
        assertTrue(valley.getTarps().size()==2);
    }
    
    @Test
    public void noDeberiaHaberViñedo(){
        valley.openVineyard("sdsd",50,200);
        valley.openVineyard("red",200,50);
        valley.openVineyard("rojo",60,100);
        assertFalse(valley.getVineyards().size()>0);
    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown(){
        valley = null;
    }
}
