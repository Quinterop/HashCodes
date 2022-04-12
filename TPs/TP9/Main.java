package TPs.TP9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Main {


    static int nbrCouleur;
    static int valMax;
    static int tailleMax;
    static int nbrDominos;
    static HashMap<Integer,int[]> dominosListAbs = new HashMap<>();
    LinkedList<ArrayList<int[]>> results = new LinkedList<>();
    
    private static void createConfig() {

        int linkedListPos = 0;
        int arrayListPos = 0;
        int tabPos = 0;
        int blockCount = 0;
        ArrayList<int[]> dominosGrid = new ArrayList<>();

        HashMap<Integer,int[]> dominosList = dominosListAbs;
        LinkedList<Integer> dominosToRemove = new LinkedList<Integer>();

        while (true) {

            for (Entry<Integer, int[]> d : dominosList.entrySet()) {

                // On évalue le cas où on se place sur la première ligne à la première case
                if (tabPos == 0 && arrayListPos == 0) {
                    dominosGrid.get(arrayListPos)[tabPos] = d.getKey();
                    dominosToRemove.add(d.getKey());
                    tabPos++;
                    if (tabPos >= tailleMax) {
                        tabPos = 0;
                        arrayListPos++;
                    }
                    continue;
                }
    
                // id du dominos à la position courante
                int index = dominosGrid.get(arrayListPos)[tabPos-1];
    
                // On évalue les cas où on se place sur la première ligne et pas à la première case
                if (tabPos != 0 && arrayListPos == 0) {
                    if (d.getValue()[4] == dominosGrid.get(index)[2]) {
                        dominosGrid.get(arrayListPos)[tabPos] = d.getKey();
                        dominosToRemove.add(d.getKey());
                        tabPos++;
                        if (tabPos >= tailleMax) {
                            tabPos = 0;
                            arrayListPos++;
                        }
                        continue;
                    }
                }
    
                // On évalue les cas où on ne se place pas sur la première ligne et où on est sur la première case
                if (tabPos == 0 && arrayListPos > 0) {
                    if (d.getValue()[1] == dominosGrid.get(index)[3]) {
                        dominosGrid.get(arrayListPos)[tabPos] = d.getKey();
                        dominosToRemove.add(d.getKey());
                        tabPos++;
                        if (tabPos >= tailleMax) {
                            tabPos = 0;
                            arrayListPos++;
                        }
                        continue;
                    }
                }
    
                // On évalue les cas où on ne se place ni sur la première ligne ni sur la première case
                if (d.getValue()[1] == dominosGrid.get(index)[3] && d.getValue()[4] == dominosGrid.get(index)[2]) {
                    dominosGrid.get(arrayListPos)[tabPos] = d.getKey();
                    dominosToRemove.add(d.getKey());
                    tabPos++;
                    if (tabPos >= tailleMax) {
                        tabPos = 0;
                        arrayListPos++;
                    }
                    continue;
                }
            }

            for(Integer d : dominosToRemove) {
                dominosList.remove(d);
            }

            if (dominosGrid.isEmpty()) {
                break;
            }
        }


    }

    private static void testConfig() {

    }

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
                    dominosListAbs.put(compteur-1, dominos);
                    System.out.println(dominos);
                }
                compteur++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        parsing(args[0]);
        createConfig();
        
    }
}
