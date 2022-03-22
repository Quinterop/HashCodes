import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test{

    static float frequences[];
    static int notes[];
    static int nombreNotes = 0;
    static int longueurInit = 0;
    static int preSequence[] = 0;


    //Initialise le tableau des fréquences 
    private static void calculFrequences() {
        int moyenne = somme(notes);
        for(int i=0;i<frequences.length; i++){
            frequences[i] = i / moyenne;
        }
    }

    public static int somme(int[] toto){
        int tmp=0;
        for(int i=0;i<toto.length;i++){
            tmp+=toto[i];
        }
        return tmp;
    }

	public static void main(String[] args){
        System.out.println("Coucou mon pélolohohoh !");
    }

    
    
    private static void parsing(String path) {
		// ouverture du fichier passé en argument
		File f = new File(path);
        int notes[];
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
                    nombreNotes=intLine[0];
                    longueurInit=intLine[1];
                }
                else if(compteur==1){
                    notes=new int[intLine.length];
                    for(int i=0;i<intLine.length;i++){
                        notes[i]=intLine[i];
                    }
                }
                else{
                    preSequence=new int[intLine.length];
                    for(int i=0;i<intLine.length;i++){
                        preSequence[i]=intLine[i];
                    }
                }
            } 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
        }

	}

    // écriture du résultat dans un fichier res.out
    private static void writeOutput(int res) {
        try {
            System.out.println("Debut ecriture resultat");
            File outputfile = new File("res.out");
            outputfile.createNewFile();

            FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(res);
            bw.close();
            System.out.println("Fin ecriture resultat");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}