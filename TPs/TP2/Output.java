package TPs.TP2;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Output {

    void ecriture(){
        try{
            File outputfile=new File("test.out");
            outputfile.createNewFile();
        
            
            FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("###");
            bw.close();
            
            System.out.println("Modification terminée!");
         
        } 
        catch (IOException e) {
        e.printStackTrace();
        }
    }
    
}
