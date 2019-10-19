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
    private int width,height;
    private ArrayList<Vineyard> vineyards;
    private ArrayList<Rain> rains;
    private ArrayList<Tarp> tarps;
    private boolean isVisible;
    private boolean ok;
    private Canvas canvas;
    private static Valley instance;
    
    
    /**
     * Constructor de los objetos de Valley
     */
    public Valley(int width, int height){
        
        rains= new ArrayList<>();
        vineyards = new ArrayList<>();
        this.width=width;
        this.height=height;
        isVisible = false;
        tarps= new ArrayList<>();
        instance = this;

    }
    
    public static Valley getValley(){
        return instance;
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
            v.open(height);
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
            vineyards.get(indice).close();
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
       String colors[]={"red","yellow","blue","black","brown","magenta","grey","fucsia"};
       if ((xi >= width) || ( longitud > width) || (xi >= xf) || (xi < 1) || (xf < 1) ){
            respuesta=true;
       } else{
           int contador = colors.length;
           for (String color: colors){
               contador = (color.equals(name)) ? contador: contador - 1;
           }
           respuesta = (contador == 0)? true: respuesta;
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
    public void addTarp(int[] lowerEnd, int[] higherEnd){
        Tarp t = new Tarp(lowerEnd, higherEnd);
        if (isVisible){
            t.makeVisible();
        }
        tarps.add(t);
    }
    
    public void addTarp(String type, int[] lowerEnd, int[] higherEnd){
        Tarp t = null;
        switch( type ){
            case "radical":
                t = new Radical(lowerEnd, higherEnd);
                break;
            case "hard":
                t = new Hard(lowerEnd, higherEnd);
                break;
            case "temporal":
                t = new Temporal(lowerEnd, higherEnd);
                break;
            case "flexible":
                t = new Flexible(lowerEnd, higherEnd);
                break;
            default:
                t = new Tarp(lowerEnd, higherEnd);
                
        }
        if (isVisible){
            t.makeVisible();
        }
        tarps.add(t);
    }
    
    /**
     * Elimina una lona 
     * @param position que es la posicion de la lista de lonas
     */
    public void removeTarp(int position){
        position--;
        Collections.sort(tarps);
        if (position<=tarps.size()){
            tarps.get(position).remove();
            tarps.remove(position);
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
     * @param tarp es la ubicacion de la lona en la lista, x la posicion de la misma
     */
   
    public void makePuncture(int tarp, int x){
        tarp--;
        if  (tarp > tarps.size()){
            if (isVisible){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "numero de lona incorrecto");
            }
            ok = false;
        } else {
            Collections.sort(tarps);
            Tarp t = tarps.get(tarp);
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
    
    /**
     * tapa un hueco si es posible
     * @param tarp la lona, x la posicion del hueco
     */
    public void patchPuncture(int tarp, int x){
        tarp--;
        if  (tarp > tarps.size()){
            if (isVisible){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "numero de lona incorrecto");
            }
            ok = false;
        } else {
            Collections.sort(tarps);
            Tarp t = tarps.get(tarp);
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
    
    /**
     * detiene una lluvia
     * @param position, la posicion de la lluvia
     */
    public void stopRain(int position){
        Collections.sort(rains);
        try{
            rains.get(position-1).makeInvisible();
            ok=true;
        }catch(IndexOutOfBoundsException e){
            ok=false;   
        }
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
            rain.makeVisible();
        }
        ok=true;
    }
    
    /**
     * retorna las lluvias activas
     * @return String[] lista con las lluvias activas
     */
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
        ok=true;
        return wateredVineyards;
    }
    
    /**
     * hace visible el valle si es posible
     */
    public void makeVisible(){
        //Canvas canvas = Canvas.getCanvas(height, width);
        isVisible = true;
        draw();
        ok=true;
    }
    
    /**
     * dibuja el valle
     */
    private void draw(){
        canvas = canvas.getCanvas(height, width);
        for (Vineyard v: vineyards){
            v.makeVisible();
        }
        
        for (Tarp t : tarps){
            t.makeVisible();
        }
        
        for(Rain r: rains){
                r.makeVisible();
        }
    }
    
    /**
     * 
     */
    public void water(int position){
        vineyards.get(position).water(true);
    }
    
    /**
     * retorna el alto del vally
     */
    public int getHeight(){
        return height;
    }
    
    
    /**
     * retorna las lonas
     */
    public ArrayList<Tarp> getTarps(){
        return tarps;
    }
    
    /**
     * retorna los viñedos
     */
    public ArrayList<Vineyard> getVineyards(){
        return vineyards;
    }
    
    /**
     * Hace invisible todo el simulador
     */
    public void makeInvisible(){
        isVisible = false;
        canvas.setVisible(isVisible);
        ok=true;
    }
    /**
     * Consulta las posiciones de los viñedos
     * @return query
     */
    public int [][] vineyards(){
        int [][] query = new int [vineyards.size()][2];
        int i = 0;
        for(Vineyard v: vineyards){
            query[i][0] = v.getPosition();
            query[i][1] = v.getPosition() + v.getWidth();
        }
        return query;
    }
    
    /**
     * retorna las posiciones de las lonas y de los huecos 
     * @return una matriz con listas de las posiciones de las lonas consus huecos
     */
    public int [][][] tarps(){
        int [][][] query = new int [tarps.size()][3][];
        int i = 0;
        for(Tarp t: tarps){
            for(int j = 0; j < 3; j++){
                if(j == 0){
                    query[i][j] = new int[2];
                    query[i][j][0] = t.getLowerEnd()[0];
                    query[i][j][1] = t.getLowerEnd()[1];
                } else if (j == 1){
                    query[i][j] = new int[2];
                    query[i][j][0] = t.getHigherEnd()[0];
                    query[i][j][1] = t.getHigherEnd()[1];
                } else {
                    query[i][j] = new int[t.getPunctures().size()];
                    int k = 0;
                    for (Puncture p: t.getPunctures()){
                        query[i][j][k] = (int) p.getXPosition();
                        k++;
                    }
                }    
            }
            i++;
        }
        int[][][] vacio={{{}}};
        if (query==vacio){
            ok=false;
        } else{
            ok=true;
        }
        return query;
    }
    
    /**
     * hacer zoom a toda la ciudad
     * @param signo puede ser + / -
     */
    public void zoom(char signo){      
        if (signo=='+' || signo=='-'){
            Canvas canvas = Canvas.getCanvas();
            canvas.zoom(signo);
            ok=true;
        }
        else{
            ok = false;
        }
    }
    
    /**
     * Termina el programa
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
    
    public void waitValley(){
        canvas.wait(1000);
    }
}
