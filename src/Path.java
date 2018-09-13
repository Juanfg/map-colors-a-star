import java.util.ArrayList;

class Path implements Comparable<Path>{
    ArrayList<Estado> camino;
    Estado ultimo;
    int truncatedDepth;

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

    void add(Estado nuevo){
        this.camino.add(nuevo);
        ultimo = nuevo;
    }

    public int getDepth(){
        //System.out.printf("%d %d\n",camino.size(),truncatedDepth);
        return camino.size()-truncatedDepth;
    }

    public double getHeuristic(){
        return this.ultimo.adyacencyDegree;
    }

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
                return -1;
            }
            else if(this.getDepth() < other.getDepth()){
                return 1;
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

