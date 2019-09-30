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
        ArrayList<Vineyard> vineyards = Valley.getVineyards();
        ArrayList<Trap> traps = Valley.getTraps();
        while (yPosition<Valley.getHeight() && noChoco){
            Rectangle gota = new Rectangle();
            if (yPosition >= Valley.getHeight()-10){
                int vineyard = vineyardCollision(vineyards, yPosition, xPosition);
                if( vineyard == -1){
                    noChoco = true;
                } else {
                    Valley.water(vineyard);
                    noChoco = false;
                }
            }
            rainOnTrap(traps,xNow);
            gota = doRectangle();
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
     * @param traps lista de lonas, la posicion de el agua,
     */
    private void rainOnTrap(ArrayList<Trap> traps, int xNow){
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
    }
    
    
    /**
     * Verifica la colision contra un viñedo
     * @param vineyards la lista de los viñedos, 
     * 
     */
    private int vineyardCollision(ArrayList<Vineyard> vineyards, double y, double x){
        int vineyard = -1;
        int vineyardPositionY = Valley.getHeight() - 10; 
        for (Vineyard v : vineyards){
            if (vineyardPositionY >=y && x>=v.getPosition() && x<=v.getWidth()){
                vineyard = vineyards.indexOf(v);
            }
        }
        return vineyard;
    }

    /**
     * Verifica la colision de la lluvia con alguna lona
     * @param traps la lista de lonas, 
     * 
     */
    private double[] collisionWith(ArrayList<Trap> traps,double xNow){
        boolean colisionTrap=false;
        boolean colisionWithPuncture=false;
        double m=0.0;
        double b=0.0;
        int pos=0;
        boolean estaEnRangoX;
        boolean estaEnRangoY;
        while (!colisionTrap && pos<traps.size()){
            int x0 = traps.get(pos).getLowerEnd()[0];
            int y0 = traps.get(pos).getLowerEnd()[1];
            int x1 = traps.get(pos).getHigherEnd()[0];
            int y1 = traps.get(pos).getHigherEnd()[1];
            m = (double) (y1-y0)/(x1-x0);
            b = y0-(m*x0);
            double y = (double) (m*xPosition)+b;
            estaEnRangoX= estaEnRangoX(x0,x1);
            estaEnRangoY= estaEnRangoY(y0,y1);
            if (yPosition>=(Valley.getHeight()-y-10) && estaEnRangoX && estaEnRangoY){
                colisionTrap=true;
                if (traps.get(pos).collisionPuncture((int)xPosition)){
                    colisionWithPuncture=true;
                }
            }
            pos++;
        }
        return returnValues(colisionWithPuncture,colisionTrap, m);
    }
    
    /**
     * retorna los valores (si colisiono) para actuar 
     * @param colisionWithPuncture si choco algun hueco , colisionTrap si choco alguna lona, pendiente la pendiente
     * @return double[] una lista con los valores para que actue la lluvia
     */
    private double[] returnValues(boolean colisionWithPuncture, boolean colisionTrap, double pendiente){
        double[] values={0,0};
        if (colisionWithPuncture){
            values[0]=2.0;
        } else if (colisionTrap){
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
            estaEnRangoY=(Valley.getHeight()-yPosition>=y0 && Valley.getHeight()-yPosition<=y1);
        } else{
            estaEnRangoY=(Valley.getHeight()-yPosition>=y1 && Valley.getHeight()-yPosition<=y0);
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