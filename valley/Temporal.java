import java.util.*;
import java.lang.*;
import javax.swing.*;
import shapes.*;
/**
 * Tarp Temporal, este tipo de lona no permite hacer huecos y su visibilidad es temporal.
 * 
 * @author Davor Cortés - David Otalora 
 * @version 1
 */
public class Temporal extends Tarp
{
    private final int timeToLive;
    
    /**
     * Constructor for objects of class Temporal
     */
    public Temporal(int []lowerEnd, int []higherEnd)
    {
        super(lowerEnd, higherEnd );
        timeToLive = 5000;
    }
    
    @Override
    public void makeVisible(){
        super.makeVisible();
        try{
            Thread.sleep(timeToLive);
        } catch (Exception e){
            // ignoring exception at the moment
        }
        makeInvisible();
    }
    
    @Override
    public void makePuncture(int x){
    }
    
    @Override
    public void patchPuncture(int x){
    }
    
    


}

