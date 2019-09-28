import shapes.*;
/**
 * Write a description of class Puncture here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Puncture
{
    private double xPosition;
    private double yPosition;
    private String color;
    private boolean isVisible; 
    private Circle point;

    /**
     * Constructor for objects of class Puncture
     */
    public Puncture(double x, double y){
        xPosition=x;
        yPosition=y;
        color="red";
        isVisible=false;
        point = new Circle();
    }
    
    public double getXPosition(){
        return xPosition;
    }
    
    public void open(){
        point.setXposition(xPosition - Math.sqrt(10));
        point.setYPosition(yPosition - Math.sqrt(10));
        point.changeColor(color);
        point.changeSize(10);
        
    }
    
    public void close(){
        makeInvisible();
        point = null;
    }

    public void makeVisible(){
        isVisible=true;
        point.makeVisible();
    }
    
    public void makeInvisible(){
        isVisible=false;
        point.makeInvisible();
    }
    
}
