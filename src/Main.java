import java.util.Scanner;

//Funcion f -> h + n
//Numero de vecinos -> 
//Numero maximo de profundidad: 
public class Main{
    public static void main(String []args){
        long startTime = System.nanoTime();
        
        Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();
        int matrix[][] = new int[height][height];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < height; j++){
                matrix[i][j] = sc.nextInt();
            }
        }

        int colors = 3;
        Estado inicial = new Estado(matrix);
        BFS bfs        = new BFS(inicial,colors);
        Path solucion  = bfs.findBestPath();
        if(solucion != null){
            System.out.println(solucion);
        }
        else{
            System.out.printf("No solution found or there was an error with the algorithm\n");
        }


        long endTime  = System.nanoTime();
        long duration = (endTime - startTime); 
        System.out.println("Total time:" + duration/1000000000 + "." + duration%1000000000 );
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