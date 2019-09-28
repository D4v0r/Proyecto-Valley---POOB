import java.lang.*;
import shapes.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
/**
 * Write a description of class Trap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Trap implements Comparable <Trap>
{
    private int[] lowerEnd;
    private int[] higherEnd;
    private ArrayList<Puncture> punctures;
    private boolean isVisible;
    private String color;
    private Line line;
    

    /**
     * Constructor for objects of class Trap
     */
    public Trap(int[] lowerEnd, int[] higherEnd){
        isVisible=false;
        color="black";
        this.lowerEnd = lowerEnd;
        this.higherEnd = higherEnd;
        punctures= new ArrayList<>();
        int limitHeight = Valley.getHeight();
        double [] point1 = { (double) lowerEnd[0], (double) (limitHeight - lowerEnd[1])};
        double [] point2 = { (double) higherEnd[0], (double) (limitHeight - higherEnd[1])};
        line = new Line(point1, point2);
        
    }
    
    public int[] getLowerEnd(){
        return lowerEnd;
    }
    
    public int[] getHigherEnd(){
        return higherEnd;
    }
    
    public ArrayList<Puncture> getPunctures(){
        return punctures;
    }
    
    /**
     * Agrega un hueco en una lona
     * @param trap es la ubicacion de la lona en la lista, x la posicion de la misma
     */
    public void makePuncture(int x){
        double y = rectFunction((double) x);
        double limitHeight = (double) Valley.getHeight();
        Puncture p = new Puncture(x , limitHeight - y);
        punctures.add(p);
        p.open();
        if(isVisible){
            p.makeVisible();
        }
    }
    
    public void patchPuncture(int x){
        if(punctures.size() > 0){
            int position = 0;
            for (int i = 0; i < punctures.size(); i++){
                if(punctures.get(i).getXPosition() == (double) x ){
                    position = i;
                }
            }
            if(position == -1){
                if(isVisible){
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Ese Hueco no existe");
                }
            } else {
                punctures.get(position).close();
                punctures.remove(position);
            }
        }
    }
    
    public double rectFunction(double x){
        double[] point = {(double) lowerEnd[0], (double) higherEnd[1]};
        double m = (double) (higherEnd[1]-lowerEnd[1])/ (higherEnd[0]-lowerEnd[0]);
        double b = lowerEnd[1]-(m*lowerEnd[0]);
        return ( (m * x) + b);
    }
    
    public void remove(){
        makeInvisible();
        punctures = null;
        line = null;
    }
    
    public void makeVisible(){
        isVisible=true;
        if(punctures.size() > 0){
            for(Puncture p: punctures){
                p.makeInvisible();
            }
        }
        line.makeVisible();
    }
    
    public void makeInvisible(){
        isVisible=false;
        line.makeInvisible();
        if(punctures.size() > 0){
            for(Puncture p: punctures){
                p.makeVisible();
            }
        }
    }
    
    @Override 
    public int compareTo(Trap otherTrap){
        int x1 = otherTrap.getLowerEnd()[0];
        int x2 = otherTrap.getHigherEnd()[0];
        if (x1 < lowerEnd[0] || x2 < higherEnd[0]){
            return 0;
        }else if(x1 == lowerEnd[0] && x2 == higherEnd[0]){
            return 1;
        }else {
            return -1;
        }
    }

}
