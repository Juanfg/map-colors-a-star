import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
public class PQ{
    public static void main(String []args){
        int numEstados = 6;
        int tamMatrix = 3;
        int colores = 3;
        int daMatrix[][][] = new int[numEstados][tamMatrix][tamMatrix];
        for(int i = 0; i < daMatrix.length; i++){
            for(int j = 0; j < daMatrix[0].length; j++){
                for(int k = 0; k < daMatrix[0][0].length; k++){
                    daMatrix[i][j][k] = i;
                }
            }
        }



        ArrayList<Estado> estados = new ArrayList<Estado>(numEstados);
        for(int i = 0; i < numEstados; i++){
            estados.add(new Estado(daMatrix[i],colores));
        }

        Collections.shuffle(estados);
        Path p1 = new Path();
        Path p2 = new Path();
        Path p3 = new Path();

        for(int i = 0; i < estados.size(); i++){
            if(i < numEstados/3){
                p1.add(estados.get(i));
            }
            else if(i > (numEstados - (numEstados/3) - 1)){
                p3.add(estados.get(i));
            }
            else{
                p2.add(estados.get(i));
            }
        }
        System.out.printf("P1:\n");
        System.out.println(p1);
        System.out.printf("P2:\n");
        System.out.println(p2);
        System.out.printf("P3:\n");
        System.out.println(p3);
        System.out.printf("\n");

        PriorityQueue<Path> paths = new PriorityQueue<Path>();
        paths.add(p1);
        paths.add(p2);
        paths.add(p3);
        System.out.printf("El MAS ALTO ES:\n");
        System.out.println(paths.peek());
    }
}

class Estado implements Comparable<Estado>{
    int matrix[][];
    int height;
    int adyacencyDegree;
    int colores;

    Estado(int matrix[][], int colores){
        this.matrix = matrix;
        this.height = matrix.length;
        this.adyacencyDegree = calculateAdyacencyDegree();
        this.colores = colores;
    }

    int calculateAdyacencyDegree(){
        int sum = 0;
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.height; j++){
                sum+=this.matrix[i][j];
            }
        }
        return sum;
    }

    public int compareTo(Estado other) {
        return this.adyacencyDegree - other.adyacencyDegree;
    }

    public String toString(){
        String res = "";
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.height; j++){
                res += this.matrix[i][j] + " ";
            }
            res+="\n";
        }
        res += "Adyacency Degree: " + this.adyacencyDegree + "\n";
        res += "Colours: " + this.colores + "\n";
        return res;
    }

    
}

class Path implements Comparable<Path>{
    ArrayList<Estado> camino;
    int depth;
    Path(){
        this.camino = new ArrayList<Estado>();
        this.depth = 0;
    }

    void add(Estado nuevo){
        this.camino.add(nuevo);
        this.depth++;
    }
    public int compareTo(Path other){
        Estado lastSelfState = this.camino.get(this.camino.size()-1);
        Estado lastOtherState = other.camino.get(other.camino.size()-1);
        return lastSelfState.compareTo(lastOtherState);
    }

    public String toString(){
        String res = "";

        for(int i = 0; i < camino.size(); i++){
            res += camino.get(i).toString();
        }

        res += "\nDepth: " + depth;

        return res;
    }
}