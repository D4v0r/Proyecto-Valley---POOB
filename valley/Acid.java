import java.util.*;
import shapes.*;
/**
 * Write a description of class Acid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Acid extends Rain
{

    /**
     * Constructor for objects of class Acid
     */
    public Acid(int xPosition)
    {
        super(xPosition);
        changeColor("orange");
    }
    
    public void start(int x){
        x+=5;
        while (stillFalling()){
            Rectangle gota = new Rectangle();
            gota.changeColor(getColor());
            gota.moveHorizontal(getxPosition()+5);
            gota.changeSize(5,5);
            setyPosition(1);
            gota.setYposition(getyPosition());
            addStream(gota);
        }
        for (int i=0;i<getTarps().size();i++){
            if (x>=getTarps().get(i).getLowerEnd()[0] && x<=getTarps().get(i).getHigherEnd()[0]){
                getTarps().get(i).makePuncture(x);
            }
        }
    }

}
