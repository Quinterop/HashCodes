package TPs.TP2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Parsing {
    
    public static void main(String[] args) {
        
        // vérification qu'un argument (chemin) a bien été passé
        if (args.length < 1) {
            System.out.println("Préciser le chemin du fichier...");
            System.exit(1);
        }
        
        /*
         * déclaration des variables
         */

        // ouverture du fichier passé en argument
        File f = new File(args[1]);
        
        // nombre de bus pour chaque type
        int busA, busB, busC = 0;

        // nombre de villes sur la ligne
        int nbVilles = 0;

        // Liste de toutes les lignes du 
        ArrayList<String> ficLines = new ArrayList<String>();

        // tableau pour stocker chaque mot de la ligne courante
        String[] lineSplited;

        // Liste des villes de départs (clé String) associées à un objet (Depart) comportant le crédit pour les bus de types A, B, C
        HashMap<String, Depart> villesDep = new HashMap<String, Depart>();



        // lecture du fichier et ajout de chaque ligne dans l'ArrayList de type String
        try {
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()) {
                ficLines.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // parcours de la liste de lignes
        for (int i = 0 ; i<ficLines.size() ; i++) {

            // conversion de la ligne courante en un tableau contenant chaque mot de la ligne
            lineSplited = split_on_char(ficLines.get(i));

            // quatres premières lignes (de type int)
            if (i == 0) {
                nbVilles = Integer.valueOf(lineSplited[0]);
            }
            else if (i == 1) {
                busA = Integer.valueOf(lineSplited[0]);
            }
            else if (i == 2) {
                busB = Integer.valueOf(lineSplited[0]);
            }
            else if (i == 3) {
                busC = Integer.valueOf(lineSplited[0]);
            }

            else if (i >= nbVilles+4) {
                // Pour la matrice d'adjacense
            }

            else {
                // ajout des villes de départs de leurs caractéristiques dans la HashMap
                villesDep.put(lineSplited[0], new Depart(Integer.valueOf(lineSplited[1]), Integer.valueOf(lineSplited[2]), Integer.valueOf(lineSplited[3])));
                nbVilles--;
            }
        }
    }

    // Pour convertir la chaine de caractère en tableau
    public static String[] split_on_char(String line) {
        return line.split(" ");
    }

}