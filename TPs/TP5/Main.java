import java.io.File;
import java.io.FileNotFoundException;
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
                for (int j = 0 ; j < ficLines.get(i).length ; i++) {
                    nbrVertex = ficLines.get(i)[0];
                    nbrPlayers = ficLines.get(i)[1];
                    nbrParty = ficLines.get(i)[2];
                    nbrEdges = ficLines.get(i)[3];
                }
            }
            
        }
    }
    // Pour convertir la chaine de caractère en tableau
    public static String[] split_on_char(String line) {
        return line.split(" ");
    }
}
