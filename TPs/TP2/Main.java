import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

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
    private static int[][] adjaMatrix = new int[0][0];


    // ############

    /*
     * START OF
     * CHEMIN NAIF
     */

    public static void bestDepart(){

        int[] bestA = new int[3];
        int[] bestB = new int[3];
        int[] bestC = new int[3];
        String[] nameA = {"","",""};
        String[] nameB = {"","",""};
        String[] nameC = {"","",""};

        for (Entry<String, Depart> entry : villesDep.entrySet()) {
            for(int i=0; i<3 ; i++){
                if(entry.getValue().getA()>bestA[i]){ 
                    bestA[i] = entry.getValue().getA(); 
                    nameA[i]=entry.getKey();
                }
                if(entry.getValue().getB()>bestB[i]){ 
                    bestB[i] = entry.getValue().getB(); 
                    nameB[i]=entry.getKey();
                }
                if(entry.getValue().getC()>bestC[i]){ 
                    bestC[i] = entry.getValue().getC(); 
                    nameC[i]=entry.getKey();
                }
            }

        }
        for(String i:nameA)
            System.out.println(i);
        
    }

    /*
    public void shortest(int[][] tab){
        int[] bests = new int[5]; //taille customisable ? en fonction de tab ?


        for(int i=0; i<tab.length; i++){
            for(int j=0; j<tab[i].length; j++){

                for(int k=0;k<bests.length ; k++){ //triple boucle c nul mdr

                if(tab[i][j]>bests[k])
                    bests[k] = tab[i][j];
                    
                }
            }
        }
    }
    */

    /*
     * END OF
     * CHEMIN NAIF
     */

    // ##################

    /*
     * START OF
     * PARSING
     */

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
                adjaMatrix = new int[nbVilles][nbVilles];
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
                adjaMatrix[x][y] = Integer.valueOf(lineSplited[2]);           	
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

    /*
     * END OF
     * PARSING
     */


    /*
     * START OF
     * WRITE OUTPUT
     */
    
    private static void writeOutput(){
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

    /*
     * END OF
     * WRITE OUTPUT
     */

    public static void main(String[] args) {
        // vérification qu'un argument (chemin) a bien été passé
        if (args.length < 1) {
            System.out.println("Préciser le chemin du fichier...");
            System.exit(1);
        }
        parsing(args[0]);
        bestDepart();
    }

}