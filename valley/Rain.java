import java.util.*;
import java.awt.geom.*;
import shapes.*;
/**
 * Write a description of class Rain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Rain implements Comparable <Rain>
{
    private double xPosition;
    private double yPosition;
    private boolean isVisible;
    private ArrayList<Rectangle> stream;
    private Line line;
    
    /**
     * Constructor for objects of class Rain
     */
    public Rain(int xPosition){
        stream = new ArrayList<>();
        isVisible = false;
        this.xPosition = xPosition;
        yPosition=0;
        stream = new ArrayList<>();
        double[] point1 = {xPosition,0};
        double[] point2 = {xPosition,10};
    }
    
    public void makeVisible(){
        isVisible = true;
        for(Rectangle gota: stream ){
            gota.makeVisible();
        }
    }
    
    public void makeInvisible(){
        isVisible = false;
        for(Rectangle gota: stream){
            gota.makeInvisible();
        }
    }
    
    /**
     * 
     */
    public void start(int x){
        int xNow=x;
        boolean noChoco=true;
        ArrayList<Vineyard> vineyards = Valley.getVineyards();
        ArrayList<Trap> traps = Valley.getTraps();
        while (yPosition<Valley.getHeight() && noChoco){
            Rectangle gota = new Rectangle();
            gota.changeColor("blue");
            gota.moveHorizontal(xPosition);
            gota.changeSize(5,5);
            if (yPosition >= Valley.getHeight()-10){
                int vineyard = vineyardCollision(vineyards, yPosition, xPosition);
                if( vineyard == -1){
                    noChoco = true;
                } else {
                    Valley.water(vineyard);
                    noChoco = false;
                }
            }
            double rta[] = collisionWith(traps,xNow);
            if (rta[0]==2.0){
                yPosition++;
            }
            else if (rta[0]==1.0){
                yPosition+=Math.abs(rta[1]);
                if (rta[1]<0){
                    xPosition++;
                }else{
                    xPosition--;
                }
            } else{
                yPosition++;
            }
            gota.setXposition(xPosition);
            gota.setYposition(yPosition);
            stream.add(gota);
        }
    }
    
    /**
     * 
     */
    private int vineyardCollision(ArrayList<Vineyard> vineyards, double y, double x){
        int vineyard = -1;
        for (Vineyard v : vineyards){
            if (v.getPositionY()>=y && x>=v.getPosition() && x<=v.getWidth()){
                vineyard = vineyards.indexOf(v);
            }
        }
        return vineyard;
    }

    /**
     * 
     */
    private double[] collisionWith(ArrayList<Trap> traps,double xNow){
        boolean colision=false;
        boolean colisionP=false;
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
            if (yPosition>=(Valley.getHeight()-y-10) && ((xPosition>=x0 && xPosition<=x1)||(xPosition>=x1 && xPosition<=x0))){
                colision=true;
                if (traps.get(pos).collisionPuncture((int)xPosition)){
                    colisionP=true;
                }
            }
            pos++;
        }
        if (colisionP){
            rta[0]=2.0;
        }
        else if (colision){
            rta[0] = 1.0;
            rta[1] = m;
        }
        return rta;
    }
    
    /**
     * 
     */
    public double getXPosition(){
        return xPosition;
    }
    
    @Override 
    public int compareTo(Rain r){
        if (r.getXPosition() < xPosition){
            return 1;
        }else if(r.getXPosition() == xPosition){
            return 0;
        }else {
            return -1;
        }
    }

  
}

