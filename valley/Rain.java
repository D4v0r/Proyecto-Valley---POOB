import java.util.*;
import java.awt.geom.*;
import shapes.*;
/**
 * Write a description of class Rain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Rain implements Comparable <Rain>{
    private double xPosition;
    private double yPosition;
    private boolean isVisible;
    private ArrayList<Rectangle> stream;
    private Valley valley;
    private String color;
    
    /**
     * Constructor for objects of class Rain
     */
    public Rain(int xPosition){
        stream = new ArrayList<>();
        isVisible = false;
        this.xPosition = xPosition;
        yPosition=0;
        stream = new ArrayList<>();
        color = "blue";
        valley = Valley.getValley();
        double[] point1 = {xPosition,0};
        double[] point2 = {xPosition,10};
    }
    
    /**
     * hace visible la lluvia si es posible
     */
    public void makeVisible(){
        isVisible = true;
        for(Rectangle gota: stream ){
            gota.makeVisible();
        }
    }
   
    /**
     * hace invisible la lluvia si es posible
     */
    public void makeInvisible(){
        isVisible = false;
        for(Rectangle gota: stream){
            gota.makeInvisible();
        }
    }
    
    /**
     * Empieza a correr el agua
     * @param x siendo la posicion de la lluvia
     */
    public void start(int x){
        int xNow=x;
        boolean noChoco=true;
        ArrayList<Vineyard> vineyards = valley.getVineyards();
        ArrayList<Tarp> tarps = valley.getTarps();
        while (yPosition<valley.getHeight() && noChoco){
            Rectangle gota = new Rectangle();
            if (yPosition >= valley.getHeight()-10){
                int vineyard = vineyardCollision(vineyards, yPosition, xPosition);
                if( vineyard == -1){
                    noChoco = true;
                } else {
                    valley.water(vineyard);
                    noChoco = false;
                }
            }
            rainOnTarp(tarps,xNow);
            gota = doRectangle();
            gota.changeColor(color);
            stream.add(gota);
        }
    }
    
    /**
     * Crea un rectangulo que sera una parte de la lluvia
     */
    private Rectangle doRectangle(){
        Rectangle rect = new Rectangle();
        rect.changeColor("blue");
        rect.moveHorizontal(xPosition);
        rect.changeSize(5,5);
        rect.setXposition(xPosition);
        rect.setYposition(yPosition);
        return rect;
    }
    
    /**
     * 
     * @param tarps lista de lonas, la posicion de el agua,
     */
    private void rainOnTarp(ArrayList<Tarp> tarps, int xNow){
        double rta[] = collisionWith(tarps,xNow);
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
    }
    
    
    /**
     * Verifica la colision contra un viñedo
     * @param vineyards la lista de los viñedos, 
     * 
     */
    private int vineyardCollision(ArrayList<Vineyard> vineyards, double y, double x){
        int vineyard = -1;
        int vineyardPositionY = valley.getHeight() - 10; 
        for (Vineyard v : vineyards){
            if (vineyardPositionY >=y && x>=v.getPosition() && x<=v.getWidth()){
                vineyard = vineyards.indexOf(v);
            }
        }
        return vineyard;
    }

    /**
     * Verifica la colision de la lluvia con alguna lona
     * @param tarps la lista de lonas, 
     * 
     */
    private double[] collisionWith(ArrayList<Tarp> tarps,double xNow){
        boolean colisionTarp=false;
        boolean colisionWithPuncture=false;
        double m=0.0;
        double b=0.0;
        int pos=0;
        boolean estaEnRangoX;
        boolean estaEnRangoY;
        while (!colisionTarp && pos<tarps.size()){
            int x0 = tarps.get(pos).getLowerEnd()[0];
            int y0 = tarps.get(pos).getLowerEnd()[1];
            int x1 = tarps.get(pos).getHigherEnd()[0];
            int y1 = tarps.get(pos).getHigherEnd()[1];
            m = (double) (y1-y0)/(x1-x0);
            b = y0-(m*x0);
            double y = (double) (m*xPosition)+b;
            estaEnRangoX= estaEnRangoX(x0,x1);
            estaEnRangoY= estaEnRangoY(y0,y1);
            if (yPosition>=(valley.getHeight()-y-10) && estaEnRangoX && estaEnRangoY){
                colisionTarp=true;
                if (tarps.get(pos).collisionPuncture((int)xPosition)){
                    colisionWithPuncture=true;
                }
            }
            pos++;
        }
        return returnValues(colisionWithPuncture,colisionTarp, m);
    }
    
    /**
     * retorna los valores (si colisiono) para actuar 
     * @param colisionWithPuncture si choco algun hueco , colisionTarp si choco alguna lona, pendiente la pendiente
     * @return double[] una lista con los valores para que actue la lluvia
     */
    private double[] returnValues(boolean colisionWithPuncture, boolean colisionTarp, double pendiente){
        double[] values={0,0};
        if (colisionWithPuncture){
            values[0]=2.0;
        } else if (colisionTarp){
            values[0]=1.0;
            values[1]=pendiente;
        }
        return values;
    }
    
    /**
     * verifica si la lluvia esta en una rango x de alguna lona
     * @param int x0,x1 posiciones de la lona
     * @return boolean si esta en el rango
     */
    private boolean estaEnRangoX(int x0,int x1){
        boolean estaEnRangoX =false;
        if (x0<x1){
            estaEnRangoX=(xPosition>=x0 && xPosition<=x1);
        }else{
            estaEnRangoX=(xPosition>=x1 && xPosition<=x0);
        }
        return estaEnRangoX;
    }
    
    /**
     * verifica si la lluvia esta en una rango y de alguna lona
     * @param int y0,y1 las posiciones de la lona
     * @return boolean si estan en el rango
     */
    private boolean estaEnRangoY(int y0, int y1){
        boolean estaEnRangoY=false;
        if (y0<y1){
            estaEnRangoY=(valley.getHeight()-yPosition>=y0 && valley.getHeight()-yPosition<=y1);
        } else{
            estaEnRangoY=(valley.getHeight()-yPosition>=y1 && valley.getHeight()-yPosition<=y0);
        }
        return estaEnRangoY;
    }
    
    
    /**
     * retorna la posicion en x
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