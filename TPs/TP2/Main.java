package TPs.TP2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    /*
     * déclaration des variables
     */

    // nombre de bus pour chaque type
    private static int busA, busB, busC = 0;
    // nombre de villes sur la ligne
    private static int nbVilles = 0;

    // Liste de toutes les lignes du 
    private static ArrayList<String> ficLines = new ArrayList<String>();

    // tableau pour stocker chaque mot de la ligne courante
    private static String[] lineSplited;
    
    private static int id = 0;

    // Liste des villes de départs (clé String) associées à un objet (Depart) comportant le crédit pour les bus de types A, B, C
    private static HashMap<String, Depart> villesDep = new HashMap<String, Depart>();
    private static HashMap<String,Integer> identifiants = new HashMap<String,Integer>();
    
    //moche mais devrait marcher
    private static int[][] adja = new int[0][0];


    // Pour convertir la chaine de caractère en tableau
    public static String[] split_on_char(String line) {
        return line.split(" ");
    }

    private static void parsing(String path) {
        // ouverture du fichier passé en argument
        File f = new File(path);

        // lecture du fichier et ajout de chaque ligne dans l'ArrayList de type String
        try {
            try (Scanner sc = new Scanner(f)) {
                while(sc.hasNextLine()) {
                    ficLines.add(sc.nextLine());
                }
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
                adja = new int[nbVilles][nbVilles];
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
                // Pour la matrice d'adjacence
            	int x = identifiants.get(lineSplited[0]);
                int y = identifiants.get(lineSplited[1]);
                adja[x][y] = Integer.valueOf(lineSplited[2]);           	
            }

            else {
                // ajout des villes de départs de leurs caractéristiques dans la HashMap et la map des id
                villesDep.put(lineSplited[0], new Depart(Integer.valueOf(lineSplited[1]), Integer.valueOf(lineSplited[2]), Integer.valueOf(lineSplited[3])));
                identifiants.put(lineSplited[0],id);
                id++;
                if (id>nbVilles) System.out.println("c pété");
            }
            /* DEBBUG : System.out.println(ficLines.get(i));*/
        }
    }

    public static void main(String[] args) {
        // vérification qu'un argument (chemin) a bien été passé
        if (args.length < 1) {
            System.out.println("Préciser le chemin du fichier...");
            System.exit(1);
        }
        parsing(args[0]);
    }

}