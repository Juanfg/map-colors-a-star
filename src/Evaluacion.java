    int calculateAdyacencyDegree(){
        double avg = 0;
        int regionSize = (int)Math.ceil(this.matrix.length/2.0);
        int region1 = 0;
        int region2 = 0;
        int region3 = 0;
        int region4 = 0;
        if(this.matrix.length%2 == 0){
            regionSize++;
        }
        for(int i = 0; i < regionSize; i++){
            for(int j = 0; j < regionSize; j++){
                if(i < regionSize-1){
                    if(this.matrix[i][j] == this.matrix[i+1][j]){
                        region1++;
                    }
                }
                if(j < regionSize-1){
                    if(this.matrix[i][j] == this.matrix[i][j+1]){
                        region1++;
                    }
                }
            }
        }
        for(int i = 0; i < regionSize; i++){
            for(int j = this.matrix.length - regionSize; j < this.matrix.length; j++){
                if(i < regionSize-1){
                    if(this.matrix[i][j] == this.matrix[i+1][j]){
                        region2++;
                    }
                }
                if(j < this.matrix.length-1){
                    if(this.matrix[i][j] == this.matrix[i][j+1]){
                        region2++;
                    }
                }
            }
        }
        for(int i = this.matrix.length - regionSize; i < this.matrix.length; i++){
            for(int j = 0; j < regionSize; j++){
                if(i < this.matrix.length-1){
                    if(this.matrix[i][j] == this.matrix[i+1][j]){
                        region3++;
                    }
                }
                if(j < regionSize-1){
                    if(this.matrix[i][j] == this.matrix[i][j+1]){
                        region3++;
                    }
                }
            }
        }
        for(int i = this.matrix.length - regionSize; i < this.matrix.length; i++){
            for(int j = this.matrix.length - regionSize; j < this.matrix.length; j++){
                if(i < this.matrix.length-1){
                    if(this.matrix[i][j] == this.matrix[i+1][j]){
                        region4++;
                    }
                }
                if(j < this.matrix.length-1){
                    if(this.matrix[i][j] == this.matrix[i][j+1]){
                        region4++;
                    }
                }
            }
        }
        avg = (region1+region2+region3+region4)/4.0;
        return (int)Math.round(avg);
    }