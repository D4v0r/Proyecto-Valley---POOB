

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ValleyTestC2.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ValleyTestC2
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
        valley = new Valley(400, 400);
    }
    
    @Test
    public void consultaTarps(){
        int[][][] esperado =  {{{50,100},{100,200},{70}},{{150,100},{200,200},{170, 190}}};
        int [][] datosT1 = {{50,100},{100,200},{70}};
        int [][] datosT2 = {{150,100},{200,200},{170, 190}};
        valley.addTarp(datosT1[0], datosT1[1]);
        valley.makePuncture(1, datosT1[2][0]);
        valley.addTarp(datosT2[0], datosT2[1]);
        valley.makePuncture(2, datosT2[2][0]);
        valley.makePuncture(2, datosT2[2][1]);
        assertEquals(valley.tarps(), esperado);
        
    }
    
    @Test
    public void noDeberiaEstarRegado(){
        int[] x0 = {150,150};
        int[] x1 = {450,300};
        int[] x2 = {300,400};
        int[] x3 = {400,330};
        valley.addTarp(x0,x1);
        valley.addTarp(x2,x3);
        valley.openVineyard("red",300,400);
        valley.startRain(330);
        assertFalse(valley.getVineyards().get(0).isWatered());
    }
    
    @Test
    public void deberiaTenerHueco(){
        int[] x0 = {150,150};
        int[] x1 = {450,300};
        int[] x2 = {300,400};
        int[] x3 = {400,330};
        valley.addTarp(x0,x1);
        valley.addTarp(x2,x3);
        valley.makePuncture(1,225);
        valley.makePuncture(4,123);
        valley.makePuncture(2,310);
        assertTrue(valley.getTarps().get(0).getPunctures().size()==1);
    }
     
    @Test
    public void noDeberiaTenerHuecos(){
        int[] x0 = {150,150};
        int[] x1 = {450,300};
        int[] x2 = {300,400};
        int[] x3 = {400,330};
        valley.addTarp(x0,x1);
        valley.addTarp(x2,x3);
        valley.makePuncture(1,455);
        valley.makePuncture(1,123);
        valley.makePuncture(2,299);
        assertFalse(valley.getTarps().get(0).getPunctures().size()==1);
    }
    
    @Test
    public void deberiaEstarRegado(){
        int[] x0 = {150,150};
        int[] x1 = {450,300};
        int[] x2 = {300,400};
        int[] x3 = {400,330};
        valley.addTarp(x0,x1);
        valley.addTarp(x2,x3);
        valley.openVineyard("red",50,200);
        valley.startRain(330);
        assertTrue(!valley.getVineyards().get(0).isWatered());
    }    
    
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        valley = null;
    }
}
