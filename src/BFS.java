import java.lang.Math;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
class BFS{
    Estado inicial;
    PriorityQueue<Path> open;
    ArrayList<Path> closed;

    int profundidadMaxima;
    int colores;

    static final int openValue = 0;
    static final int closedValue = 1;

    BFS(Estado inicial, int colores){
        this.open = new PriorityQueue<Path>();
        this.closed = new ArrayList<Path>();

        this.inicial = inicial;
        open.add(new Path(inicial));
        this.colores = colores;
        this.profundidadMaxima = this.calculaHorizonte();
    }

    Path findBestPath(){
        long startTime = System.nanoTime();
        long prevTime = startTime;
        System.out.println("Estado inicial: ");
        System.out.println(this.inicial);


        int count = 0;
        while(open.size() != 0){
            Path current = open.poll();

            if(++count%30 == 0){
                System.out.printf("Current: %d\n",current.getHeuristic());
            }
            


            if(current.isGoal()){

                long endTime = System.nanoTime();
                long duration = (endTime - startTime); 
                System.out.println("Total time:" + duration/1000000000 + "." + duration%1000000000 );

                return current;
            }
            else if( current.getDepth() >= this.profundidadMaxima ){
                //clear everything
                //add current to open 
                open.clear();
                closed.clear();
                current.truncatedDepth = current.camino.size();
                open.add(current);
            }
            else{
                ArrayList<Estado> vecinos = current.ultimo.calculaVecinos(this.colores);
                //Collections.shuffle(vecinos);
                for(int i = 0; i < vecinos.size(); i++){
                    Path currentPath = current.clonaPath();
                    currentPath.add(vecinos.get(i));
                    //Not in open nor closed
                    //in order to check in open we must iterate through open and check ultimo in each path in open and see if current.ultimo is in each path. If it exists, check the length of the path in open. If it is bigger than the path of current, delete path in open and add current
                    Path openPath   = this.fetchPath(openValue,currentPath.ultimo);
                    Path closedPath = this.fetchPath(closedValue,currentPath.ultimo);
                    //If null, not in list, which means false
                    boolean openResult   = (openPath != null);
                    boolean closedResult = (closedPath != null);
                    if(!openResult && !closedResult){
                        open.add(currentPath);
                    }
                    else if(openResult){
                        //States are both on open, we check which one is better and add it to open
                        //the other is....deleted from open or forgotten
                        if(currentPath.getDepth() < openPath.getDepth()){
                            open.remove(openPath);
                            open.add(currentPath);
                        }
                        else{

                        }
                    }
                    else if(closedResult){
                        //K
                        if(currentPath.getDepth() < closedPath.getDepth()){
                            closed.remove(closedPath);
                            open.add(currentPath);
                        }
                    }
                }
            }
            closed.add(current);  
        }
        return null;
    }

    //Check for the state to exist in any path in one of two lists
    boolean hasState(int origin, Estado state){
        //0 -> open. 1 -> closed
        Iterator it = closed.iterator();
        if(origin == 0) it = open.iterator();

        while(it.hasNext()){
            Path current = (Path)it.next();
            if(current.ultimo.equals(state)){
                return true;
            }
        }
        return false;
    }

    //Fetches PATH that contains the STATE
    Path fetchPath(int origin, Estado state){
        //0 -> open. 1 -> closed
        Iterator it = closed.iterator();
        if(origin == 0) it = open.iterator();
        
        while(it.hasNext()){
            Path current = (Path)it.next();
            if(current.ultimo.equals(state)){
                return current;
            }
        }
        return null;

    }
    

    int calculaHorizonte(){
        long maxMemory = Runtime.getRuntime().maxMemory();
        long tamanoPorEstado = this.calculaTamanoEstado();
        long numeroColores = this.colores;
        int tamanoMatrix = inicial.matrix.length;
        //double numeroVecinos = Math.pow(numeroColores,Math.pow(tamanoMatrix,2)); //---> //numero de colores ^ numero de celdas ^ 2
        double numeroVecinos = inicial.calculaNumeroVecinos(tamanoMatrix,this.colores);
        //d < log(memSyze/size(en)) / log(2n^2)
        double res = Math.log(maxMemory/tamanoPorEstado) / Math.log(numeroVecinos);
        System.out.println(res);
        return (int)Math.ceil(res);
    }
    long calculaTamanoEstado(){
        int height = inicial.matrix.length;
        int sizeOfInt = 4;
        int totalSize = (sizeOfInt * height * height) + sizeOfInt;
        return totalSize;        
    }
}

