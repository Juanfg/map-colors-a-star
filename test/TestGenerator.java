import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;

public class TestGenerator{
    public static void main(String []args){
        Random r1 = new Random(System.nanoTime());
        
        int colors, size;
        String fileName = "";


        if(args.length != 0){
            fileName = args[0];
            colors = Integer.parseInt(args[1]);
            size = Integer.parseInt(args[2]);
        }
        else{
            Scanner sc = new Scanner(System.in);
            fileName = sc.nextLine();
            colors = sc.nextInt();
            size   = sc.nextInt();
        }

        Random r2 = new Random(System.nanoTime());
        File file = new File("./" + fileName + ".txt");
        //Create the file
        try{
            file.createNewFile();
            //Write Content
            FileWriter writer = new FileWriter(file);
            
            writer.write(Integer.toString(size) + "\n");
            for(int row = 0; row < size; row++){
                for(int col = 0; col < size; col++){
                    Random r = new Random(System.nanoTime());
                    int rNum = r.nextInt(colors);
                    
                   
                    String str = Integer.toString(rNum) + " ";
                    writer.write(str);
                }
                writer.write("\n");
            }

            writer.close();
        }
        catch(Exception e){
            System.out.println("oh noes");
            return;
        }
        
        


    }
}