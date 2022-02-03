package TPs.TP2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class chemin {
    

    public void bestDepart(HashMap<String, Depart> map){

        int[] bestA, bestB, bestC = new int[3];
        String[] nameA, nameB, nameC = new String[3];

        for (Entry<String, Depart> entry : map.entrySet()) {
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
}
