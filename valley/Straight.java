import java.util.*;
import java.awt.geom.*;
import shapes.*;
/**
 * Write a description of class Straight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Straight extends Rain
{
    
    /**
     * Constructor for objects of class Straight
     */
    public Straight(int xPosition)
    {
        super(xPosition);
        changeColor("red");
    }

    public void start(int x){
        int xNow=(newUbication(x));
        while (stillFalling()){
            Rectangle gota = new Rectangle();
            gota.changeColor(getColor());
            gota.moveHorizontal(xNow);
            gota.changeSize(5,5);
            setyPosition(1);
            gota.setYposition(getyPosition());
            addStream(gota);
        }
    }
    
    /**
     * Reubica la posicion en x de la lluvia tal que no colisione con una lona
     * @param x la posicion actual de la lona
     * @return la nueva posicion en x
     */
    private int newUbication(int x){
        int newUbication=0;
        int i;
        if (getVineyards().size()>0){
            for (i=0; i<getVineyards().size();i++){
                if (x>=getVineyards().get(i).getPosition() && x<=getVineyards().get(i).getWidth()){
                    newUbication=getVineyards().get(i).getPosition();
                    for (int j=0;j<getTarps().size();j++){
                        if (x>=getTarps().get(j).getLowerEnd()[0] && x<=getTarps().get(j).getHigherEnd()[0]){
                            newUbication=getTarps().get(j).getLowerEnd()[0]+5;
                        }
                    }
                }
            }
        } else{
           for (int j=0;j<getTarps().size();j++){
               if (x>=getTarps().get(j).getLowerEnd()[0] && x<=getTarps().get(j).getHigherEnd()[0]){
                   newUbication=getTarps().get(j).getHigherEnd()[0]+5;
               }
           } 
        }
        return newUbication;
    }
}
