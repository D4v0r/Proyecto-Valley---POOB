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

    public int getPositionY(){
        return (int)filling.getPositionY();
    }
    
    /**
     * retorna el nombre 
     */
    public String getName(){
        return name;
    }
    
    /**
     * retorna la posicion inicial
     */
    public int getPosition(){
        return xInicial;
    }
    
    /**
     * retorna el ancho
     */
    public int getWidth(){
        return xFinal-xInicial;
    }
    
    /**
     * cambia el valor si fue regado
     * @param boolean action si se rego o no
     */
    public void water(boolean action){
        isWatered = action;
    }
    
    /**
     * retorna si fue regado
     * @return 
     */
    public boolean isWatered(){
        return isWatered;
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void open(int limit)
    {
        // put your code here
        int limitHeight = limit;
        filling.moveHorizontal(xInicial);
        filling.moveVertical(limit-5);
        filling.changeSize(5, xFinal - xInicial);
        filling.changeColor(name);
    }
    
    /**
     * cierra el viñedo
     */
    public void close()
    {
        makeInvisible();
        filling = null;
    }
    
    /**
     * retorna el color
     */
    public String getColor(){
        return color;
    }
    
    /**
     * hace visible le viñedo si es posible
     */
    public void makeVisible()
    {
        filling.makeVisible();
        isVisible = true;
    }
    
    /**
     * hace invisible el viñedo si es posible
     */
    public void makeInvisible()
    {
        filling.makeInvisible();
        isVisible = false;
    }
}
