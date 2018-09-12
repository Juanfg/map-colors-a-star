import java.util.Random;
import java.util.ArrayList;

class Estado implements Comparable<Estado>{
    int matrix[][];
    int adyacencyDegree;

    Estado(int matrix[][]){
        this.matrix = matrix;
        //CHANGE THIS. THIS SHOULD NOT BE THERE
        this.adyacencyDegree = countAdjacencies();

    }
    Estado clonaEstado(){
        int[][] newMat = copyMatrix(this.matrix);
        Estado nuevo = new Estado(newMat);
        return nuevo;
    }
    ArrayList<Estado> calculaVecinos(int colors){
        //int numeroVecinos = (int)Math.pow(colors-1,Math.pow(this.matrix.length,2));

        int numeroVecinos = calculaNumeroVecinos(this.matrix.length,colors);
            //int numeroVecinos = this.calculaNumeroVecinos(this.matrix.length);
        ArrayList<Estado> vecinos = new ArrayList<Estado>(numeroVecinos);
        Random r = new Random();


        for(int row = 0; row < this.matrix.length; row++){
            for(int col = 0; col < this.matrix.length; col++){
                for(int paint = 0; paint < colors; paint++){
                    if(paint == this.matrix[row][col]){
                        
                    }
                    else{
                        int mat[][] = copyMatrix(this.matrix);
                        mat[row][col] = paint;
                        vecinos.add(new Estado(mat));
                    }
                }
            }
        }

        //System.out.printf("%d == %d ??\n",index,numeroVecinos);

        return vecinos;

        /*
        int currentColor;
        int newColor;
        int index;
        int col1;
        int col2;
        for(int i = 0; i < numeroVecinos; i+=2){
            //0 2 4 6 8 <-- i
            //0 1 2 3 4 <-- index
            index = i/2;
            col1 = r.nextInt(this.matrix.length);
            col2 = col1;
            while(col2 == col1){
                col2 = r.nextInt(this.matrix.length);
            }

            int m1[][] = copyMatrix(this.matrix);
            int m2[][] = copyMatrix(this.matrix);

            currentColor =  m1[index][col1];
            newColor = currentColor;
            while(currentColor == newColor){
                newColor = r.nextInt(colors);
            }
            m1[index][col1] = newColor;


            currentColor =  m2[index][col2];
            newColor = currentColor;
            while(currentColor == newColor){
                newColor = r.nextInt(colors);
            }
            m2[index][col2] = newColor;


            Estado e1 = new Estado(m1);
            Estado e2 = new Estado(m2);
            vecinos[i] = e1;
            vecinos[i+1] = e2;
        }
        return vecinos;
        */
    }

    int calculaNumeroVecinos(int length, int colors){
        
        return (colors-1)*(int)Math.pow(length,2);
    }
    
    //NOTE :- Le toca a lozada. Actualmente solo suma cosas
    int calculateAdyacencyDegree(){
        int sum = 0;
        for(int i = 0; i < this.matrix.length; i++){
            for(int j = 0; j < this.matrix.length; j++){
                sum+=this.matrix[i][j];
            }
        }
        return sum;
    }

    public int countAdjacencies(){
        int [][] copy = copyMatrix(this.matrix);
        int adjacencies = 0;
        for(int row = 0; row < copy.length; row++){
            for(int col = 0; col < copy[0].length; col++){
                if(col > 0){ //can go left
                    if(copy[row][col-1] != -1){
                        if(copy[row][col-1] ==  copy[row][col]){
                            adjacencies++;
                        }
                    }
                }
                if(col+1 < copy.length){ //can go right
                    if(copy[row][col+1] != -1){
                        if(copy[row][col+1] ==  copy[row][col]){
                            adjacencies++;
                        }
                    }
                }
                if(row > 0){ //can go up
                    if(copy[row-1][col] != -1){
                        if(copy[row-1][col] ==  copy[row][col]){
                            adjacencies++;
                        }
                    }
                }   
                if(row+1 < copy.length){ //can go down
                    if(copy[row+1][col] != -1){
                        if(copy[row+1][col] ==  copy[row][col]){
                            adjacencies++;
                        }
                    }
                }
                copy[row][col] = -1;
            }
        }
        return adjacencies;
    }

    public static int [][] copyMatrix(int [][] mat){
        int [][] newMatrix = new int[mat.length][mat.length];
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                newMatrix[i][j] = mat[i][j];
            }
        }
        return newMatrix;
    }

    public int compareTo(Estado other) {
        // 1 -> this > other
        // 0 -> this = other
        //-1 -> this < other

        //compare matrix
        //if matrix are equal, return zero 
        //else, compare adyacencies

        if(this.matrix.length == other.matrix.length){
            boolean areDifferent = false;
            for(int i = 0; i < this.matrix.length; i++){
                for(int j = 0; j < this.matrix.length; j++){
                    if(  this.matrix[i][j] != other.matrix[i][j] ){
                        areDifferent = true;
                        i = this.matrix.length;
                        j = this.matrix.length;
                    }
                }
            }

            if(areDifferent){
                return this.adyacencyDegree - other.adyacencyDegree;
            }
            else{
                return 0;
            }
        }
        else{
            return this.matrix.length - other.matrix.length;
        }
    }

    public String toString(){
        String res = "";
        for(int i = 0; i < this.matrix.length; i++){
            for(int j = 0; j < this.matrix.length; j++){
                res += this.matrix[i][j] + " ";
            }
            res+="\n";
        }
        res += "Adyacency Degree: " + this.adyacencyDegree + "\n";
        return res;
    }
}
