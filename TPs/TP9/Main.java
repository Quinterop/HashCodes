package TPs.TP9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {


    static int nbrCouleur;
    static int valMax;
    static int tailleMax;
    static int nbrDominos;
    static HashMap<Integer,int[]> toto=new HashMap<>();


    public static void parsing(String path) {
        // ouverture du fichier passé en argument
        File f = new File(path);
        int compteur=0;
        // lecture du fichier et ajout de chaque ligne dans l'ArrayList de type String
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String[] strLine = sc.nextLine().split(" ");
                int[] intLine = new int[strLine.length];
                for (int i = 0 ; i < intLine.length ; i++) {
                    intLine[i] = Integer.parseInt(strLine[i]);
                }
                if(compteur==0){
                    nbrCouleur=intLine[0];
                    valMax=intLine[1];
                    tailleMax=intLine[2];
                    nbrDominos=intLine[3];
                }
                else{
                    int[] dominos = new int[intLine.length];
                    for(int i=0;i<intLine.length;i++){
                        dominos[i]=intLine[i];
                    }
                    toto.put(compteur-1, dominos);
                    System.out.println(dominos);
                }
                compteur++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        
    }
}
