
/**
 * Write a description of class Flexible here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flexible extends Tarp
{

    /**
     * Constructor for objects of class Flexible
     */
    public Flexible(int []lowerEnd, int []higherEnd)
    {
        super(lowerEnd, higherEnd );
        changeColor("red");
    }
    
    public void makePuncture(int x){
        double y = rectFunction((double) x);
        double limitHeight = (double) getHeightValley();
        if (getPunctures().size()!=0){
            patchPuncture((int)getPunctures().get(0).getXPosition());
        }
        clearPunctures();
        Puncture p = new Puncture(x , limitHeight - y);
        addPunctures(p);
        p.open();
        if(getisVisible()){
            p.makeVisible();
        }
    }

}
