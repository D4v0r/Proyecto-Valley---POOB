

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ValleyTestC4.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ValleyTestC4
{
    private Valley valley;
    
    /**
     * Default constructor for test class ValleyTestC4
     */
    public ValleyTestC4(){
        valley = new Valley(400, 400);
    }

    @Test
    public void deberiaAtravesarLona(){
        int []vineyard= new int[]{100,200};
        int []x0=new int[]{100,200};
        int []x1=new int[]{400,250};
        valley.addTarp(x0,x1);
        valley.openVineyard("red",20,400);
        valley.startRain("acid",250);
        assertFalse(valley.getVineyards().get(0).isWatered());
    }
    
    @Test
    public void deberiaDespararecerLona(){
        int []x0=new int[]{100,200};
        int []x1=new int[]{400,250};
        valley.addTarp("radical",x0,x1);
        assertTrue(valley.getTarps().get(0).getPunctures().size()==0);
    }
    
    @Test
    public void noDeberiaTenerHueco(){
        int []x0=new int[]{100,200};
        int []x1=new int[]{400,250};
        valley.addTarp("hard",x0,x1);
        valley.makePuncture(1,200);
        assertTrue(valley.getTarps().get(0).getPunctures().size()==0);
    }
    
    @Test
    public void noDeberiaRegar(){
        int []x0=new int[]{100,200};
        int []x1=new int[]{400,250};
        valley.addTarp(x0,x1);
        valley.openVineyard("red",70,200);
        valley.startRain("Distorted",200);
        assertFalse(valley.getVineyards().get(0).isWatered());
    }
    
    @Test
    public void deberiaEsquivar(){
        int []x0=new int[]{100,200};
        int []x1=new int[]{400,250};
        valley.addTarp(x0,x1);
        valley.makePuncture(1,200);
        valley.openVineyard("red",180,240);
        valley.startRain("sTraight",200);
        assertFalse(valley.getVineyards().get(0).isWatered());
    }
    
    @Test
    public void deberiaTenerUnHueco(){
        int []x0=new int[]{100,200};
        int []x1=new int[]{400,250};
        valley.addTarp("flexible",x0,x1);
        valley.makePuncture(1,200);
        valley.makePuncture(1,240);
        valley.makePuncture(1,260);
        assertTrue(valley.getTarps().get(0).getPunctures().size()==1);
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
