import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static int gridX;
    private static int gridY;
    private static int nbrDrones;
    private static int shiftingMax;
    private static int maxWeight;
    private static int nbrProducts;
    private static int nbrWarehouse;

    private static HashMap<Integer, Integer> productsBindweight = new HashMap<Integer, Integer>();

    private static List<Entrepot> warehouses = new ArrayList<Entrepot>(); 

	// Liste de toutes les lignes du
	private static ArrayList<String> ficLines = new ArrayList<String>();

	// tableau pour stocker chaque mot de la ligne courante
	private static String[] lineSplited;

	// Pour convertir la chaine de caractère en tableau
	public static String[] split_on_char(String line) {
		return line.split(" ");
	}

    private void parsing(String path) {
		// ouverture du fichier passé en argument
		File f = new File(path);

		// lecture du fichier et ajout de chaque ligne dans l'ArrayList de type String
		try {
			try (Scanner sc = new Scanner(f)) {
				while (sc.hasNextLine()) {
					ficLines.add(sc.nextLine());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        // parcours de la liste de lignes
		for (int i = 0; i < ficLines.size(); i++) {
            lineSplited = split_on_char(ficLines.get(i));

            if (i == 0) {
                gridX = lineSplited[0];
                gridY = lineSplited[1];
                nbrDrones = lineSplited[2];
                shiftingMax = lineSplited[3];
                maxWeight = lineSplited[4];
            }
            if (i == 1) {
                nbrProducts = lineSplited[0];
            }
            if (i == 2) {
                for (int j = 0 ; j < nbrProducts ; j++) {
                    productsBindweight.put(j+1, lineSplited[j]);
                }
            }
            if (i == 3) {
                nbrWarehouse = lineSplited[0];
            }
            for (j = 0 ; j<nbrWarehouse ; j++) {
                warehouses.add
            }
        }
    }

    public static void main(String[] args) {

    }
}