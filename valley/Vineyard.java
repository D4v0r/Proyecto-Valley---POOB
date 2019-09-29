import shapes.*;
/**
 * Write a description of class Vineyard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vineyard
{
    // instance variables - replace the example below with your own
    private int xInicial;
    private int xFinal;
    private String name;
    private boolean isVisible;
    private boolean isWatered;
    private String color;
    private Rectangle filling;
    
    /**
     * Constructor for objects of class Vineyard
     */
    public Vineyard(String name, int xi, int xf)
    {
        this.name = name;
        xInicial = xi;
        xFinal = xf;
        isVisible = false;
        color = name;
        filling = new Rectangle();
    }
    
    public String getName(){
        return name;
    }
    
    public int getPosition(){
        return xInicial;
    }
    
    public int getWidth(){
        return xFinal-xInicial;
    }
    
    public void water(boolean action){
        isWatered = action;
    }
    
    public boolean isWatered(){
        return isWatered;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void open()
    {
        // put your code here
        int limitHeight = Valley.getHeight();
        filling.moveHorizontal(xInicial);
        filling.moveVertical(limitHeight-5);
        filling.changeSize(5, xFinal - xInicial);
        filling.changeColor(name);
    }
    
    public void close()
    {
        makeInvisible();
        filling = null;
    }
    
    public String getColor(){
        return color;
    }
    
    public void makeVisible()
    {
        filling.makeVisible();
        isVisible = true;
    }
    
    public void makeInvisible()
    {
        filling.makeInvisible();
        isVisible = false;
    }
}
