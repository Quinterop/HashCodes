import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static int nbrVertex = 0;
	private static int nbrEdges = 0;
	private static int nbrPlayers = 0;
	private static int nbrParty = 0;
	private static int[][] matrix;
	int[] distanceFromArbitre;     //taille joeuurs
    int[] distanceToArbitre;  

    // Liste de toutes les lignes
    private static List<int[]> ficLines = new LinkedList<>();

	public static void main(String args[]) {
        parsing(args[0]);
        printMatrix();
        int[] res=dijkstra(matrix, 5);
        for(int i=0;i<res.length;i++){
            System.out.print(res[i]+" ");
        }
        System.out.println();
	}

    private static void parsing(String path) {
        // ouverture du fichier passé en argument
        File f = new File(path);

        // lecture du fichier et ajout de chaque ligne dans l'ArrayList de type String
        try {
            try (Scanner sc = new Scanner(f)) {
                while (sc.hasNextLine()) {
                    String[] strLine = split_on_char(sc.nextLine());
                    int[] intLine = new int[strLine.length];
                    for (int i = 0 ; i < intLine.length ; i++) {
                        intLine[i] = Integer.parseInt(strLine[i]);
                    }
                    ficLines.add(intLine);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0 ; i <ficLines.size() ; i++) {
            if (i == 0) {
                nbrVertex = ficLines.get(i)[0];
                nbrPlayers = ficLines.get(i)[1];
                nbrParty = ficLines.get(i)[2];
                nbrEdges = ficLines.get(i)[3];
                matrix = new int[nbrVertex][nbrVertex];
                initMatrix(matrix);
                continue;
            }
            matrix[ficLines.get(i)[0]-1][ficLines.get(i)[1]-1] = ficLines.get(i)[2];
        }
    }

//retourne le poids total de toutes les communications au sein d'une équipe
    public int poidsEquipe(int joueursEquipes[]) {
        int total = 0;
        for(int i=0;i<joueursEquipes.length;i++) {
            int poidsJoueur = 0;
            for(int j=i+1;j<joueursEquipes.length;j++) {
                poidsJoueur += distanceFromArbitre[joueursEquipes[j]] + distanceToArbitre[joueursEquipes[j]];
            }
            total += poidsJoueur;
        }
        return total;
    }

    // Pour convertir la chaine de caractère en tableau
    public static String[] split_on_char(String line) {
        return line.split(" ");
    }

    public static void printMatrix() {
        for (int i = 0 ; i < matrix.length ; i++) {
            System.out.print("[");
            for (int j = 0 ; j < matrix.length ; j++) {
                if(matrix[i][j]>-1){
                    System.out.print(" "+matrix[i][j] + ", ");
                }
                else{
                    System.out.print(matrix[i][j] + ", ");
                }
            }
            System.out.println("]");
        }
    }

    public static int[] dijkstra(int[][] matrice, int arbitre){
        boolean isfinished=false;
        int [] P=new int[matrice.length];
        for(int i=0;i<matrice[1].length;i++){
            P[i]=-1;
        }
        while(!isfinished){
            for(int i=0;i<matrice[1].length;i++){
                P[i]=distance(matrice, i, arbitre);
            }
            isfinished=isNotFinished(P);
        }
        return P;
    }

    public static boolean isNotFinished(int[] t){
        for(int i=0;i<t.length;i++){
            if(t[i]==-1){
                return false;
            }
        }
        return true;
    }

    public static int distance(int[][] matrice,int position,int arbitre){
        int t=100000000;
        int tmp=0;
        int stockage_position_x=0;
        int i=0;
        while(i<arbitre){
            System.out.println("position x: "+position+" position y: "+i+" valeur: "+matrice[i][position]);
            if(t>matrice[i][position] && position!=i && matrice[i][position]>-1){
                t=matrice[i][position];
                stockage_position_x=i;
                System.out.println(stockage_position_x);
            }
            if(i==4){
                tmp+=t;
                //System.out.println(stockage_position_x+"aaaaa"+position);
                if(stockage_position_x!=arbitre-1){
                    position=stockage_position_x;
                    i=0;
                    continue;
                }
            }
            //System.out.println(tmp);
            i++;
        }
        return tmp;
    }

    public static void initMatrix(int[][] matrice){
        for(int i=0;i<matrice.length;i++){
            for(int j=0;j<matrice[i].length;j++){
                matrice[i][j]=-1;
            }
        }
    }

}
