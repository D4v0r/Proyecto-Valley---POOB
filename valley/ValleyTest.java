

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
public class ValleyTest
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
        valley.openVineyard("v1", 10, 40);
        assertTrue(valley.ok());
    }
    
    @Test
    public void NoDeberiaAbrirViñedo(){
        valley.openVineyard("v1", 10, 40);
        valley.openVineyard("v1", 10, 40);
        assertFalse(valley.ok());
        
        valley.openVineyard("v1", 30, 70);
        assertFalse(valley.ok());
        
        valley.openVineyard("v2", 40, 90);
        assertFalse(valley.ok());
        
        valley.openVineyard("v2", 30, 90);
        assertFalse(valley.ok());
        
        valley.openVineyard("v2", 30, 30);
        assertFalse(valley.ok());
        
        valley.openVineyard("v2", 30, 20);
        assertFalse(valley.ok());
        
        valley.openVineyard("v2", 300, 350);
        assertFalse(valley.ok());
        
        valley.openVineyard("v2", -20, 40);
        assertFalse(valley.ok());
        
    }
    
    @Test
    public void deberiaCerrarViñedo(){
        valley.openVineyard("v1", 10, 40);
        valley.closeVineyard("v1");
        assertTrue(valley.ok());
        
    }
    
    @Test
    public void noDeberiaCerrarViñedo(){
        valley.openVineyard("v1", 10, 40);
        valley.closeVineyard("v");
        assertFalse(valley.ok());
    }
    
    @Test
    
    public void deberiaHacerHueco(){
        int[] x1 = {20,10};
        int[] x2 = {100,100};
        valley.addTrap(x1,x2);
        valley.makePuncture(1,50);
        assertTrue(valley.getTraps().get(0).getPunctures().size()!=0);
        
        valley.makePuncture(1,51);
        assertTrue(valley.getTraps().get(0).getPunctures().size()==2);
        
        valley.makePuncture(1,600);
        assertTrue(valley.getTraps().get(0).getPunctures().size()==2);
    }
    
    @Test
    public void noDeberiaHacerHueco(){
        int[] x1 = {20,10};
        int[] x2 = {100,100};
        valley.addTrap(x1,x2);
        valley.makePuncture(1,500);
        assertTrue(valley.getTraps().get(0).getPunctures().size()==0);
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
