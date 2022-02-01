package TPs.TP2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Parsing {
    
    public static void main(String[] args) {
        
        if (args.length < 1) {
            System.out.println("Préciser le chemin du fichier...");
            System.exit(1);
        }
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
            System.out.println(ficLines);
        }
    }

    public static String[] split_on_char(String line) {
        return line.split(" ");
    }

}