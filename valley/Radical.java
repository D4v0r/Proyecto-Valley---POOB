
/**
 * Write a description of class Radical here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Radical extends Tarp
{

    /**
     * Constructor for objects of class Radical
     */
    public Radical(int []lowerEnd, int []higherEnd)
    {
        super(lowerEnd, higherEnd );
        changeColor("yellow");
    }

    public void makePuncture(int x){
        remove();
    }
}
