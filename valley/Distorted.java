import java.util.*;
import shapes.*;
/**
 * Write a description of class distorted here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Distorted extends Rain
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class distorted
     */
    public Distorted(int xPosition)
    {
        super(xPosition);
        changeColor("yellow");
    }

    public void start(int x){
        int xNow=getHeightValley()-(newUbication(x));
        if (xNow!=getHeightValley()){
            setxPosition(xNow+10-getxPosition());
        }
        boolean noChoco=true;
        while (stillFalling() && noChoco){
            Rectangle gota = new Rectangle();
            gota.changeColor(getColor());
            gota.changeSize(5,5);
            if (vineyardCollision(getVineyards(),getyPosition(),getxPosition())){
                noChoco=false;
            }
            double rta[] = collisionWith(getTarps(),xNow);
            if (rta[0]==1.0){
                setyPosition(Math.abs(rta[1]));
                if (rta[1]<0){
                    setxPosition(1);
                }else{
                    setxPosition(-1);;
                }
            } else{
                setyPosition(1);
            }
            gota.setXposition(getxPosition());
            gota.setYposition(getyPosition());
            addStream(gota);
        }
    }
    
    /**
     * retorna si colisiono con un viñedo
     * @param
     * @return 
     */
    private boolean vineyardCollision(ArrayList<Vineyard> vineyards, double y, double x){
        boolean colision=false;
        for (Vineyard v : vineyards){
            if (v.getPositionY()>=y && x>=v.getPosition() && x<=v.getWidth()){
                colision=true;
            }
        }
        return colision;
    }

    /**
     * retorna si colisiono con una lona
     */
    private double[] collisionWith(ArrayList<Tarp> traps,double xNow){
        boolean colision=false;
        double[] rta={0,0};
        double m=0.0;
        double b=0.0;
        int pos=0;
        while (!colision && pos<traps.size()){
            int x0 = traps.get(pos).getLowerEnd()[0];
            int y0 = traps.get(pos).getLowerEnd()[1];
            int x1 = traps.get(pos).getHigherEnd()[0];
            int y1 = traps.get(pos).getHigherEnd()[1];
            m = (double) (y1-y0)/(x1-x0);
            b = y0-(m*x0);
            double y = (double) (m*xNow)+b;
            if (getyPosition()>=(getHeightValley()-y) && ((getxPosition()>=x0 && getxPosition()<=x1)||(getxPosition()>=x1 && getxPosition()<=x0))){
                colision=true;
            }
            pos++;
        }
        if (colision){
            rta[0] = 1.0;
            rta[1] = m;
        }
        return rta;
    }
    
    /**
     * Reubica la posicion en x de la lluvia tal que colisione con una lona
     * pero no con un viñedo
     * @param x la posicion actual de la lona
     * @return la nueva posicion en x
     */
    private int newUbication(int x){
       int newUbication=9999;
       for (int i=0;i<getTarps().size();i++){
           if (x>=getTarps().get(i).getLowerEnd()[0] && x<=getTarps().get(i).getHigherEnd()[0]){
               if (!colitionVineyard(getTarps().get(i))){
                   newUbication=(getTarps().get(i).getHigherEnd()[0]/2)+5;
               } else{
                   newUbication=getTarps().get(i).getHigherEnd()[0]+5;
               }
           }
       }
       if (newUbication!=9999){
           for (int j=0;j<getVineyards().size();j++){
               if (x>=getVineyards().get(j).getPosition() && x<=getVineyards().get(j).getWidth()){
                   newUbication=getVineyards().get(j).getPosition()+getVineyards().get(j).getWidth()+5;
               }
           }
        }
       return newUbication;
    }
    
    private boolean colitionVineyard(Tarp t){
        boolean rta=false;
        for (int j=0;j<getVineyards().size();j++){
            if (t.getLowerEnd()[0]>=getVineyards().get(j).getPosition() && t.getLowerEnd()[0]<=getVineyards().get(j).getWidth()){
                if (t.getHigherEnd()[0]>=getVineyards().get(j).getPosition() && t.getHigherEnd()[0]<=getVineyards().get(j).getWidth()){
                    rta=true;
                }
            }
        }
        return rta;
    }
}
