import java.util.*;
import java.awt.geom.*;
import shapes.*;
/**
 * Write a description of class Rain here.
 * 
 * @author ( your name ) 
 * @version ( a version number or a date )
 */

public class Rain implements Comparable <Rain>
{
    private  int xPosition;
    private boolean isVisible;
    private ArrayList<Line> stream;
    /**
     * Constructor for objects of class Rain
     */
    public Rain(int xPosition){
        isVisible = false;
        this.xPosition = xPosition;
        stream = new ArrayList<>();
        
    }
    
    public void makeVisible(){
        isVisible = true;
        for(Line gotas: stream ){
            gotas.makeVisible();
        }
    }
    
    public void makeInvisible(){
        isVisible = false;
        for(Line gotas: stream){
            gotas.makeInvisible();
        }
    }
    
    public void start(){
        int limit = Valley.getHeight();
        double [] originPoint = {0, 0};
        double [] destinyPoint = {0, 0};
        double yBefore = 0;
        double yNow= 0;
        double xBefore = 0;
        double xNow = 1;
        ArrayList<Vineyard> vineyards = Valley.getVineyards();
        ArrayList<Trap> traps = Valley.getTraps();
        Trap t = null;
        while ( yNow < limit ){
            if (xBefore != xNow){
                t = collisionWith(traps, xNow);
                yNow = limit - t.rectFunction(xNow);
            }else if(yNow == limit - 5 ){
                int v = vineyardCollision(vineyards, xNow);
                if ( v != -1){
                    Valley.water(v);
                    break;
                }
            }
        }
        
    }
    
    private int vineyardCollision(ArrayList<Vineyard> vineyards, double x){
        int position = -1;
        for(Vineyard v: vineyards){
            double xi = (double) v.getPosition();
            double xf = (double) v.getWidth() - v.getPosition();
            if(x >= xi  && x <= xf){
                position = vineyards.indexOf(v);
            }           
        }
        return position;
    }

    
    private Trap collisionWith(ArrayList<Trap> traps, double xNow){
        Trap collisionWith = null;
        for (Trap t: traps){
            int x1 = t.getLowerEnd()[0];
            int x2 = t.getHigherEnd()[0];
            int y2 = t.getHigherEnd()[1];
            
            if ((x1  <= xNow && xNow <= x2) || 
                (x2  <= xNow && xNow <= x1)){
                    //Esta en el dominio x de la lona
                    if(collisionWith == null){
                        collisionWith = t;
                    } else {
                        collisionWith = (collisionWith.getHigherEnd()[1] > y2) ? 
                                         collisionWith : t;
                    }
            }
        }
        return collisionWith;
    }
    
    public int getXPosition(){
        return xPosition;
    }
    
    @Override 
    public int compareTo(Rain r){
        if (r.getXPosition() < xPosition){
            return 0;
        }else if(r.getXPosition() == xPosition){
            return 1;
        }else {
            return -1;
        }
    }

  
}
