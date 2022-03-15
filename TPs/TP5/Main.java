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

    // Liste de toutes les lignes
    private static List<int[]> ficLines = new LinkedList<>();

	public static void main(String args[]) {
        parsing(args[0]);
        printMatrix();
	}

	public static void pcc() {
		
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
                continue;
            }
            matrix[ficLines.get(i)[0]-1][ficLines.get(i)[1]-1] = ficLines.get(i)[2];
        }
    }
    // Pour convertir la chaine de caractère en tableau
    public static String[] split_on_char(String line) {
        return line.split(" ");
    }

    public static void printMatrix() {
        for (int i = 0 ; i < matrix.length ; i++) {
            System.out.print("[");
            for (int j = 0 ; j < matrix.length ; j++) {
                System.out.print(matrix[i][j] + ", ");
            }
            System.out.println("]");
        }
    }
}
