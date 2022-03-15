import java.util.ArrayList;

public class Dijkstra {


    public static int[] dijkstra(int[][] matrice, int arbitre){
        int [] P;
        for(int i=0;i<matrice[1].length;i++){
            P[i]=-1;
        }
        while(!isFinished(P)){
            for(int i=0;i<matrice[1].length;i++){
                if(P[i]!=-1){
                    P[i]=distance(matrice, i, arbitre);
                }
            }
        }

        return P;
    }

    public static boolean isFinished(ArrayList<Integer> t){
        for(int i=0;i<t.size();i++){
            if(t.get(i)==-1){
                return false;
            }
        }
        return true;
    }

    public static int distance(matrice[][] matrice,int position,int arbitre){
        int t=matrice[1][position];
        int tmp=0;
        int i=0;
        while(i!=arbitre){ 
            if(t>matrice[i][position]){
                t=matrice[i][position];
                position=i;
                i=0;
            }
            tmp+=t;
            i++;
        }
        
    }
    
}
