import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class valleyContestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class valleyContestTest
{
    private valleyContest va;
    /**
     * Default constructor for test class valleyContestTest
     */
    public valleyContestTest(){
        va = new valleyContest();
    }

    @Test
    public void deberiaAbrirHuecos(){
        int []vineyard= new int[]{20,40,3};
        int [][]traps=new int[][]{{30,20,10,30},{50,20,10,50},{10,60,40,80}};
        assertTrue(va.solve(vineyard,traps)==2);
    }
    
    @Test
    public void noDeberiaAbrirHuecos(){
        int []vineyard= new int[]{10,25,1};
        int [][]traps=new int[][]{{25,10,50,55}};
        assertTrue(va.solve(vineyard,traps)==0);
    }
    
    @Test
    public void deberiaAbrirUnHueco(){
        int []vineyard= new int[]{20,40,2};
        int [][]traps=new int[][]{{30,20,10,30},{50,20,10,50}};
        assertTrue(va.solve(vineyard,traps)==1);
    }
    
    @Test
    public void noDeberiaHacerHuecosSiNoHayLonas(){
        int []vineyard= new int[]{10,25,1};
        int [][]traps=new int[][]{{25,10,50,55}};
        assertTrue(va.solve(vineyard,traps)==0);
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

