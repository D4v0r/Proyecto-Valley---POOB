package shapes;

/**
 * Abstract class Shape - 
 * 
 * @author: Davor Cortés - David Otalora
 * Date: 16/10/2019
 */
public abstract class Shape
{
    // instance variables - replace the example below with your own
    String color;
    boolean isVisible;
    /**
     * Constructor de la clase.
     */
    public Shape(){
        color = "magenta";
        isVisible = false;
    }
    
    /**
     * Dibuja la figura.
     * @return
     */
    protected abstract void draw();
    
    /**
     * Borra la figura.
     * @return
     */
    protected abstract void erase();
    
    /**
     * Cambia el color de la figura.
     * @Param newColor - Color que se desea asignar a la figura
     * Los colores validos son : red", "yellow", "blue", "green",
     * "magenta" and "black".
     * @return 
     */
    public void changeColor(String newColor){
        color = newColor;
	draw();
    }
    
    /**
     * Hace visible la figura.
     * @return
     */
    public  void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Hace Invisible la figura.
     * @return
     */
    public  void makeInvisible(){
        isVisible = false;
        erase();
    }
    
    /**
     * Mueve la figura Horizontalmente.
     * @return
     */
    public abstract void moveHorizontal(double distance);
    
    /**
     * Mueve la figura Verticalmente.
     * @return
     */
    public abstract void moveVertical(double distance);
    
    /**
     * Mueve lentamente la figura Horizontalmente.
     * @return
     */
    public abstract void slowMoveHorizontal(int distance);
    
    /**
     * Mueve lentamente la figura Verticalmente.
     * @return
     */
    public abstract void slowMoveVertical(int distance);
    
    /**
     * Mueve la figura a la derecha.
     * @return
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Mueve la figura a la izquierda.
     * @return
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Mueve la figura hacia arriba.
     * @return
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Mueve la figura hacia abajo.
     * @return
     */
    public void moveDown(){
        moveVertical(20);
    }
}
