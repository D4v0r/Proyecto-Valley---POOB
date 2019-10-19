 /**
 * Write a description of class ValleyContest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Arrays;
import java.lang.Math;


public class valleyContest
{
    private int xi,xf,lonas;
    private int valley[][][];
    private int s[][];
    private int max;
    public valleyContest(){
    }
    
    public int solve(int vineyard[],int trap[][]){
        xi=vineyard[0];
        xf=vineyard[1];
        lonas=vineyard[2];
        s= new int[lonas][5];
        max=xf;
        
        for (int i=0; i<lonas; i++){
            int x1,x2,y1,y2;
            int m,maX,minX;
            x1=trap[i][0];
            y1=trap[i][1];
            x2=trap[i][2];
            y2=trap[i][3];
            double pendiente=(y2-y1)/(x2-x1);
            if(pendiente>0){m=-1;}else{m=1;}
            if (x1<x2){maX=x2;minX=x1;}else{maX=x1;minX=x2;}
            if (max<maX){max=maX;}
            int f[]={m,y1,y2,minX,maX};
            s[i]=f;                       
        }
        
        valley=Matriz(s,vineyard,max+1);
        int g=(xf-xi)/2;
        int punct=sol(xi,0);
        return punct;
    }
    
    public void simulate(int vineyard[],int trap[][],boolean slow){
        int punct=solve(vineyard,trap);
        Valley valley= new Valley(800,500);
        valley.makeVisible();
        valley.openVineyard("simulate",vineyard[0],vineyard[1]);
        for(int i=0;i<lonas;i++){
            if (slow){
                valley.waitValley();
            }
            int low[]={trap[i][0],trap[i][1]};
            int hig[]={trap[i][2],trap[i][3]};
            valley.addTarp(low,hig);
        }
    }
    
    
    private int sol(int x,int y){
        int t=0;
        if (x>max || x<0){ t=1000;//Infinito 
        }else if (valley[y][x][0]==-2){t=1000;//Infinito 
        }else if (valley[y][x][0]==0 && valley[y][x][1]==0){ //llego al vineyard
            t=0;
        }else if(valley[y][x][1]==1){// Caida libre
            t=sol(x,y+1);
        }else if(valley[y][x][0]==1 || valley[y][x][0]==-1){ //Lona y su respectiva direccion
            int min=Math.min(sol(x,y+1)+1,sol(x+valley[y][x][0],y));
            t=min;
        }
        return t;
    }
    

    private int[][][] Matriz(int s[][],int vine[],int n){
        int valley[][][]= new int[lonas+1][n][2];
        int inf[]={-2,0},b[]={0,1};
        for (int i=0;i<lonas+1;i++){
            for (int j=0;j<n;j++){
                valley[i][j]=b;
                if(i==lonas){valley[i][j]=inf;}
            }
        }
        
        for (int i=vine[0];i<=vine[1];i++){
           int f[]={0,0};
           valley[lonas][i]=f;
        }
        
        quicksort(s,0,lonas-1);
        
        for (int i=0;i<lonas;i++){
            int temp[]={s[i][0],0};
            for (int j=s[i][3];j<=s[i][4];j++){
                valley[i][j]=temp;
            }
        }
        /**
        for (int i=0;i<lonas+1;i++){
                System.out.print("[");
                 for(int j=0;j<n;j++){
                    System.out.print(" ");
                    System.out.print(Arrays.toString(valley[i][j]));
                }
                System.out.println("]");
        }*/
        return valley;
    }
    
    private static void quicksort(int A[][], int izq, int der) {
    
      int pivote[]=A[izq]; // tomamos primer elemento como pivote
      int i=izq; // i realiza la búsqueda de izquierda a derecha
      int j=der; // j realiza la búsqueda de derecha a izquierda
      int aux[];
     
      while(i<j){            // mientras no se crucen las búsquedas
         while(A[i][1]>=pivote[1]  && i<j) i++; // busca elemento mayor que pivote
         while(A[j][1]<pivote[1]) j--;         // busca elemento menor que pivote
         if (i<j) {                      // si no se han cruzado                      
             aux= A[i];                  // los intercambia
             A[i]=A[j]; 
             A[j]=aux;
         }
       }
       A[izq]=A[j]; // se coloca el pivote en su lugar de forma que tendremos
       A[j]=pivote; // los menores a su izquierda y los mayores a su derecha
       if(izq<j-1)
          quicksort(A,izq,j-1); // ordenamos subarray izquierdo
       if(j+1 <der)
          quicksort(A,j+1,der); // ordenamos subarray derecho
    }
}