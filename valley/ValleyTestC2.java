

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
    public void consultaTraps(){
        int[][][] esperado =  {{{50,100},{100,200},{70}},{{150,100},{200,200},{170, 190}}};
        int [][] datosT1 = {{50,100},{100,200},{70}};
        int [][] datosT2 = {{150,100},{200,200},{170, 190}};
        valley.addTrap(datosT1[0], datosT1[1]);
        valley.makePuncture(1, datosT1[2][0]);
        valley.addTrap(datosT2[0], datosT2[1]);
        valley.makePuncture(2, datosT2[2][0]);
        valley.makePuncture(2, datosT2[2][1]);
        assertEquals(valley.traps(), esperado);
        
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
