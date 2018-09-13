import java.util.ArrayList;

class Path implements Comparable<Path>{
    //Each path hast an arraylist of states, the path per se
    ArrayList<Estado> camino;
    //And a reference to the last state added
    Estado ultimo;
    //This considers the depth cut by the horizontal limit
    int truncatedDepth;

    //COnstructors
    Path(){
        this.camino = new ArrayList<Estado>();
        ultimo = null;
        truncatedDepth = 0;
    }

    Path(Estado inicial){
        this.camino = new ArrayList<Estado>();
        this.camino.add(inicial);
        ultimo = inicial;
        truncatedDepth = 0;
    }

    Path(ArrayList<Estado> nuevo){
        this.camino = nuevo;
        this.ultimo = this.camino.get((this.camino.size()-1));
        truncatedDepth = 0;
    }

    //USed when generating new neighbours
    Path clonaPath(){
        ArrayList<Estado> newCamino = new ArrayList<Estado>(camino.size());
        for(int i = 0; i < camino.size(); i++){
            Estado clone = camino.get(i).clonaEstado();
            newCamino.add(clone);
        }
        Path nuevo = new Path(newCamino);
        nuevo.truncatedDepth = this.truncatedDepth;
        return nuevo;

    }

    //Adds a new state to the path
    void add(Estado nuevo){
        this.camino.add(nuevo);
        ultimo = nuevo;
    }

    //Concatenates an arraylist to this path
    void concatenate(ArrayList<Estado> arr){
        this.camino.addAll(arr);
        ultimo = this.camino.get((this.camino.size()-1));
    }

    //Gets depth of path
    public int getDepth(){
        //System.out.printf("%d %d\n",camino.size(),truncatedDepth);
        return camino.size();
    }

    //Get heuristic of LAST state
    public double getHeuristic(){
        return this.ultimo.adyacencyDegree;
    }

    //Checks if this path is goal
    public boolean isGoal(){
        return this.getHeuristic() == 0.0;
    }

    public int compareTo(Path other){
        // 1 -> this > other
        // 0 -> this = other
        //-1 -> this < other

        //Compare last states. If last states are equal, compare depth
        Estado lastSelfState = this.ultimo;
        Estado lastOtherState = other.ultimo;

        int result = lastSelfState.compareTo(lastOtherState);
        if(result == 0){
            if(this.getDepth() > other.getDepth()){
                return 1;
            }
            else if(this.getDepth() < other.getDepth()){
                return -1;
            }
            else{
                return 0;
            }
            //return this.getDepth() - other.getDepth();
        }
        else{
            return result;
        }
    }

    public String toString(){
        String res = "\n";
        for(int i = 0; i < camino.size(); i++){
            res += "Estado " + (i+1) + "\n";
            res += camino.get(i).toString();
        }
        res += "Depth: " + camino.size() + "\n";
        return res;
    }
}

