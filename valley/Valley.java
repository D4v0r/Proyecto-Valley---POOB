import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Toolkit;
import java.util.Random;
import java.lang.Math;
import shapes.*;
/**
 * Write a description of class Valley here.
 * 
 * @author (David Otalora - Davor Cortez) 
 * @version (a version number or a date)
 */
public class Valley
{
    private static int width,height;
    private static ArrayList<Vineyard> vineyards;
    private ArrayList<Rain> rains;
    private static ArrayList<Trap> traps;
    private boolean isVisible;
    private boolean ok;
    private Canvas canvas;
    
    
    /**
     * Constructor de los objetos de Valley
     */
    public Valley(int width, int height){
        
        rains= new ArrayList<>();
        vineyards = new ArrayList<>();
        this.width=width;
        this.height=height;
        isVisible = false;
        traps= new ArrayList<>();

    }
    
    /**
     * Crea un viñedo en una posicion x dada
     * @param name es el nombre del viñedo, xi la posicion inicial x, xf la posicion final en x 
     */
    public void openVineyard(String name, int xi, int xf){
        if (noEsPosible(name, xi, xf)){
            if (isVisible){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Intenta otra Combinación");
            }
            ok=false;
        } else {
            Vineyard v = new Vineyard(name, xi, xf);
            v.open();
            if (isVisible){
                v.makeVisible();
            }
            vineyards.add(v);
            ok=true;
        }
    }
    
    /**
     * Dado el nombre del viñedo, lo borra
     * @param name es el nombre del viñedo
     */
    public void closeVineyard(String name){
        int indice=-2;
        for (int i=0; i< vineyards.size();i++){
            if (vineyards.get(i).getName()==name){
                indice=i;
            }
        }
        if (indice != -2){
            ok=true;
            vineyards.get(indice).makeInvisible();
            vineyards.remove(indice);
        }
        else{
            ok=false;
            if(isVisible){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Nombre Invalido");
            }
        }
    }
    
    /**
     * Verifica que 2 viñedos no tengan el mismo nombre ni se cruzen
     * @param name el nombre del viñedo, xi la posicion inicial x, xf la posicion final en x 
     */
    private boolean noEsPosible(String name, int xi, int xf) {
       boolean respuesta = false;
       int longitud = xf - xi;
       if ((xi >= width) || ( longitud > width) || (xi >= xf) || (xi < 1) || (xf < 1) ){
            respuesta=true;
       } else{
            
            for(Vineyard v: vineyards){
                int x = v.getPosition();
                if ((xi >=  x && xi <= v.getWidth() + x) || (name == v.getName())) {
                    respuesta =  true;
                }
            }
       }
        return respuesta;
    }
    
    /**
     * Agrega una lona en el valle con respecto a sus puntos
     * @param lowerEnd son los puntos mas bajos de la lona, higherEnd son los puntos mas altos de la lona
     */
    public void addTrap(int[] lowerEnd, int[] higherEnd){
        Trap t = new Trap(lowerEnd, higherEnd);
        if (isVisible){
            t.makeVisible();
        }
        traps.add(t);
    }
    
    /**
     * Elimina una lona 
     * @param position que es la posicion de la lista de lonas
     */
    public void removeTrap(int position){
        position--;
        Collections.sort(traps);
        if (position<=traps.size()){
            traps.get(position).remove();
            traps.remove(position);
            ok=true;
        }
        else{
            if (isVisible){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "posicion invalida");
            }
        }
    }
    
    /**
     * Agrega un hueco en una lona
     * @param trap es la ubicacion de la lona en la lista, x la posicion de la misma
     */
   
    public void makePuncture(int trap, int x){
        trap--;
        if  (trap > traps.size()){
            if (isVisible){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "numero de lona incorrecto");
            }
            ok = false;
        } else {
            Collections.sort(traps);
            Trap t = traps.get(trap);
            if((x >= t.getLowerEnd()[0] && x <= t.getHigherEnd()[0]) || (x >= t.getHigherEnd()[0] && x <= t.getLowerEnd()[0])){        
                t.makePuncture(x);
                ok=true;
            } else {
                if (isVisible){
                   Toolkit.getDefaultToolkit().beep();
                   JOptionPane.showMessageDialog(null, "posicion invalida");
                }
                ok = false;
            }
        }
    }
    
    public void patchPuncture(int trap, int x){
        trap--;
        if  (trap > traps.size()){
            if (isVisible){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "numero de lona incorrecto");
            }
            ok = false;
        } else {
            Collections.sort(traps);
            Trap t = traps.get(trap);
            if((x >= t.getLowerEnd()[0] && x <= t.getHigherEnd()[0]) || (x >= t.getHigherEnd()[0] && x <= t.getLowerEnd()[0])){        
                t.patchPuncture(x);
                ok=true;
            } else {
                if (isVisible){
                   Toolkit.getDefaultToolkit().beep();
                   JOptionPane.showMessageDialog(null, "posicion invalida");
                }
                ok = false;
            }
        }
    }
    
    public void stopRain(int position){
        Collections.sort(rains);
        rains.get(position-1).makeInvisible();
    }
    
    /**
     * Empieza a caer la lluvia
     * @param x represeta la posicion en donde empieza la lluvia
     */
    
    public void startRain(int x){
        Rain rain = new Rain(x);
        rain.start(x);
        rains.add(rain);
        if(isVisible){
            draw();
        }
        
    }
    
    public String[] rainFalls(){
        ArrayList<Vineyard> watered = new ArrayList<>();
        for(Vineyard v: vineyards){
            if(v.isWatered()){
                watered.add(v);
            }
        }
        
        String[] wateredVineyards = new String[watered.size()];
        for(int i = 0; i < watered.size(); i++){
            Vineyard v = watered.get(i);
            wateredVineyards[i] = (v.isWatered())? v.getName() : null;
        }
        return wateredVineyards;
    }
    
    public void makeVisible(){
        //Canvas canvas = Canvas.getCanvas(height, width);
        isVisible = true;
        draw();
    }
    
    private void draw(){
        canvas = canvas.getCanvas(height, width);
        for (Vineyard v: vineyards){
            v.makeVisible();
        }
        
        for (Trap t : traps){
            t.makeVisible();
        }
        if (rains.size()>0){
            for(Rain r: rains){
                r.makeVisible();
            }
        }
    }
    
    public static void water(int position){
        vineyards.get(position).water(true);
    }
    
    public static int getHeight(){
        return height;
    }
    
    /**
     * 
     */
    public static ArrayList<Trap> getTraps(){
        return traps;
    }
    
    public static ArrayList<Vineyard> getVineyards(){
        return vineyards;
    }
    
    /**
     * Hace invisible todo el simulador
     */
    public void makeInvisible(){
        isVisible = false;
        canvas.setVisible(isVisible);
        
    }
    
    /**
     * 
     */
    public void finish(){
        System.exit(0);
    }
    
    /**
     * Verifica que la ultima accion se halla realizado correctamente
     * @return si salio bien o no
     */
    public boolean ok(){
        return ok;
    }

}
