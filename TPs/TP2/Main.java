import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

	// Liste des villes de départs (clé String) associées à un objet (Depart)
	// comportant le crédit pour les bus de types A, B, C
	private static HashMap<String, Depart> villesDep = new HashMap<String, Depart>();
	private static HashMap<String, Integer> identifiants = new HashMap<String, Integer>();

	// moche mais devrait marcher
	private static int[][] adjaMatrix = new int[0][0];

	// ############

	/*
	 * START OF
	 * CHEMIN NAIF
	 */

	public static String[][] bestDepart() {

		int[] bestA = new int[busA];
		int[] bestB = new int[busB];
		int[] bestC = new int[busC];
		String[] nameA = { "", "", "" };
		String[] nameB = { "", "", "" };
		String[] nameC = { "", "", "" };

		for (HashMap.Entry<String, Depart> entry : villesDep.entrySet()) {
			for (int i = 0; i < busA; i++) {
				if (entry.getValue().getA() > bestA[i]) {
					bestA[i] = entry.getValue().getA();
					nameA[i] = entry.getKey();
					break;
				}
			}
		}
		for (HashMap.Entry<String, Depart> entry : villesDep.entrySet()) {
			for (int i = 0; i < busB; i++) {
				if (entry.getValue().getB() > bestB[i]) {
					bestB[i] = entry.getValue().getB();
					nameB[i] = entry.getKey();
					break;
				}
			}
		}
		for (HashMap.Entry<String, Depart> entry : villesDep.entrySet()) {
			for (int i = 0; i < busC; i++) {
				if (entry.getValue().getC() > bestC[i]) {
					bestC[i] = entry.getValue().getC();
					nameC[i] = entry.getKey();
					break;
				}

			}
		}
		System.out.println("for a:");
		for (String i : nameA)
			System.out.println(i);
		System.out.println("for b:");
		for (String i : nameB)
			System.out.println(i);
		System.out.println("for c:");
		for (String i : nameC)
			System.out.println(i);

		return new String[][] { nameA, nameB, nameC };
	}

	public static void shortest() {
		// donne le plus court chemin pour le premier bus (A)
		String[][] dprt = bestDepart();
		// pour A:
		// id2 = arrivé id1= depart
		int id2 = 10000000;
		int id1 = identifiants.get(dprt[0][0]);
		// poids = cout entre id1 et id2
		int poids = 1000000;
		for (int j = 0; j < nbVilles; j++) {
			if (adjaMatrix[id1][j] < poids && adjaMatrix[id1][j] != 0) {
				id2 = j;
				poids = adjaMatrix[id1][j];
			}
		}
		for (HashMap.Entry<String, Integer> entry : identifiants.entrySet()) {
			if (id2 == 10000000) {
				System.out.println(id2);
				break;
			}
			if (entry.getValue() == id2) {
				System.out.println(entry.getKey());
			}
		}
	}

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
				while (sc.hasNextLine()) {
					ficLines.add(sc.nextLine());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// parcours de la liste de lignes
		for (int i = 0; i < ficLines.size(); i++) {

			// conversion de la ligne courante en un tableau contenant chaque mot de la
			// ligne
			lineSplited = split_on_char(ficLines.get(i));

			// quatres premières lignes (de type int)
			if (i == 0) {
				nbVilles = Integer.valueOf(lineSplited[0]);
				adjaMatrix = new int[nbVilles][nbVilles];
			} else if (i == 1) {
				busA = Integer.valueOf(lineSplited[0]);
			} else if (i == 2) {
				busB = Integer.valueOf(lineSplited[0]);
			} else if (i == 3) {
				busC = Integer.valueOf(lineSplited[0]);
			}

			else if (i >= nbVilles + 4) {
				// Pour la matrice d'adjacence
				int x = identifiants.get(lineSplited[0]);
				int y = identifiants.get(lineSplited[1]);
				adjaMatrix[x][y] = Integer.valueOf(lineSplited[2]);
				adjaMatrix[y][x] = Integer.valueOf(lineSplited[2]);
			}

			else {
				// ajout des villes de départs de leurs caractéristiques dans la HashMap et la
				// map des id
				villesDep.put(lineSplited[0], new Depart(Integer.valueOf(lineSplited[1]),
						Integer.valueOf(lineSplited[2]), Integer.valueOf(lineSplited[3])));
				identifiants.put(lineSplited[0], id);
				id++;
				if (id > nbVilles)
					System.out.println("c pété");
			}
			// System.out.println(ficLines.get(i));
		}
		// System.out.println(busA + " " + busB + " " + busC);
	}

	/*
	 * END OF
	 * PARSING
	 */

	/*
	 * START OF
	 * WRITE OUTPUT
	 */

	private static void writeOutput(String[][] res) {
		try {
			System.out.println("Début écriture résultat");
			File outputfile = new File("res.out");
			outputfile.createNewFile();

			FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

			for (int i = 0 ; i < res.length ; i++) {
				bw.write("###\n" + alphabet[i]);
				for (int j = 0 ; j < res[i].length ; j++) {
					bw.write(res[i][j]);
				}
			}
			
			bw.close();
			System.out.println("Fin écriture résultat");

		} catch (IOException e) {
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
		shortest();

		for (int i = 0; i < adjaMatrix.length; i++) {
			System.out.println();
			for (int j = 0; j < adjaMatrix[i].length; j++) {
				System.out.print(adjaMatrix[i][j] + " ");
			}
		}
	}

}