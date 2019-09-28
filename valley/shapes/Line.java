package shapes;  
import java.awt.geom.*;

/**
 * Write a description of class Line here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Line
{
    // instance variables 
    private double x1, x2, y1, y2;
    private double slope;
    private boolean isVisible;
    private String color;

    /**
     * Constructor for objects of class Line
     */
    public Line(double[] point1, double[] point2)
    {
        // initialise instance variables
        x1 = point1[0];
        x2 = point2[0];
        y1 = point1[1];
        y2 = point2[1];
        isVisible = false;
        color = "black";
        slope = (y2 - y1)/(x2 - x1); 
    }
    
    public double getSlope(){
        return slope;
    }
    
    public double[] getPoint1(){
        return (new double[]{x1, y1});
    }
    
    public double[] getPoint2(){
        return (new double[]{x2, y2});
    }
    
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    public void erase(){
        if(isVisible){
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    private void draw()
    {
        if(isVisible){
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,new Line2D.Double(x1, y1, x2, y2) );
            canvas.wait(10);
        }
    }
}