package shapes;  
import java.awt.geom.*;

/**
 * Write a description of class Line here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Line extends Shape
{
    // instance variables 
    private double x1, x2, y1, y2;
    private double slope;

    /**
     * Constructor for objects of class Line
     */
    public Line(double[] point1, double[] point2)
    {
        // initialise instance variables
        super();
        x1 = point1[0];
        x2 = point2[0];
        y1 = point1[1];
        y2 = point2[1];
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
    
    public void moveHorizontal(double distance){
        erase();
        x1 += distance;
        x2 += distance;
        draw();
    }
    
    public void moveVertical(double distance){
        erase();
        y1 += distance;
        y2 += distance;
        draw();
    }
    
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            y1 += delta;
            y2 += delta;
            draw();
        }
    }
    
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            x1 += delta;
            x2 += delta;
            draw();
        }
    }
    
    protected void draw()
    {
        if(isVisible){
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,new Line2D.Double(x1, y1, x2, y2) );
            canvas.wait(10);
        }
    }
    
    protected void erase(){
        if(isVisible){
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}