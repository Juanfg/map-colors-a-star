import java.util.Scanner;
import java.util.Random;
//Funcion f -> h + n
//Numero de vecinos
//Numero maximo de profundidad

import java.util.PriorityQueue;

public class Main{
    public static Scanner sc = new Scanner(System.in);

    public static void main(String []args){
        Estado uno    = new Estado(generateMatrix(3,5));
        Estado dos    = new Estado(generateMatrix(3,5));
        Estado tres   = new Estado(generateMatrix(3,5));
        Estado cuatro = new Estado(generateMatrix(3,5));
        Estado cinco  = new Estado(generateMatrix(3,5));
        Estado seis   = new Estado(generateMatrix(3,5));
        Path p1 = new Path();
        Path p2 = new Path();

        p1.add(uno);
        p1.add(dos);
        p2.add(tres);
        p2.add(cuatro);

        p2.add(dos);


        PriorityQueue<Path> open = new PriorityQueue<Path>();

        open.add(p1);
        open.add(p2);

       
       System.out.println(p1);
       System.out.println(p2);

       System.out.println("The winner is:::");
       System.out.println(open.peek());

    }

    public static void main2(String []args){

        int size = 0;
        int mat[][];
        int colors = 3;
        String choice = "";
        if(args.length == 0){
            return;
        }
        else{
            size = Integer.parseInt(args[0]);
            choice = args[1];
        }

        //0 -> Random
        //1 -> Manual input
        if(choice.equals("0")){
            mat = generateMatrix(colors, size);
        }
        else{
            mat = scanMatrix(colors);
        }
        //long startTime = System.nanoTime();
        Estado inicial = new Estado(mat);
        BFS bfs        = new BFS(inicial,colors);

        System.out.println("Estado inicial: ");
        System.out.println(inicial);

        Path solucion  = bfs.findBestPath();
        if(solucion != null){
            //System.out.println(solucion);
            System.out.println("Listo!");
        }
        else{
            System.out.printf("No solution found or there was an error with the algorithm\n");
        }

       // long endTime  = System.nanoTime();
       // long duration = (endTime - startTime); 
       // System.out.println("Total time:" + duration/1000000000 + "." + duration%1000000000 );
    }

    public static int[][] scanMatrix(int colors){
        int size = sc.nextInt();
        int matrix[][] = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                matrix[i][j] = sc.nextInt();
            }
        }
        return matrix;
    }

    public static int[][] generateMatrix(int colors, int size){
        Random r = new Random(System.nanoTime());
        int matrix[][] = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                matrix[i][j] = r.nextInt(colors);
            }
        }
        return matrix;
    }
}

//METER HORIZONTE LIMITADO

/*
open -> arreglo de paths 
    priority queue de Paths
    su comparable es su mejor funcion f del ultimo estado
closed -> arreglo de estados <--- Lista

*/

/*
BFS segun olmos
open -> arreglo de estados aun considerados
closed -> Arreglo de estados previamente descartados


//open contains the initial state 
while(!open.isEmpty()){
    // open is preoviously sorted to contain the best state based on the "f function" <- what we've been through + number of adyacencies
    current = open.pop() //pops best state
    if(current.isGOAL() ){
        print current.path
    }
    else{
        current.generateChildren();
        for child in current.childs() {
            if child is not in open and closed {
                evaluate child (add the heuristic value)
                add child to open
            }
            if child is in closed{
                let childClosed = closed.get(child)
                if(child.path < childClosed.path){ //path of child NOT in closed is better than path in child in closed
                    closed.remove(childClosed)
                    open.add(child)
                }
            }
            if child is in open {
                let childOpen = open.get(child)
                if child.path < childOpen.path {
                    open.remove(childOpen)
                    open.add(child)
                }
            }
        }
    }
   
    closed.add(current)
    //open rearranged to show best first

}

INPUT -> colores y matriz 
OUTPUT ->  
n.- coordenada de celda modificada -> color 
*/