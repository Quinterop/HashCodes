package TPs.TP2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Parsing {
    
    public static void main(String[] args) {
        
        File f = new File(args[1]);
        
        /*
         *
         */
        int nbVilles, busA, busB, busC = 0;
        int ficNumLine = 0;
        ArrayList<String> ficLines = new ArrayList<String>();
        HashMap<String, Depart> villesDep = new HashMap<String, Depart>();

        try {
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()) {
                ficLines.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0 ; i<ficLines.size() ; i++) {
            if (i == 0) {
                nbVilles = Integer.valueOf(ficLines.get(i));
            }
            if (i == 1) {
                busA = Integer.valueOf(ficLines.get(i));
            }
            if (i == 2) {
                busB = Integer.valueOf(ficLines.get(i));
            }
            if (i == 3) {
                busC = Integer.valueOf(ficLines.get(i));
            }
        }
    }
}