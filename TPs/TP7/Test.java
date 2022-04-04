import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Test{

    static float frequences[];
    static int notes[];
    static int nombreNotes = 0;
    static int longueurInit = 0;
    static int preSequence[];
    static int si[];
    static int nbrJouees=0;
    static LinkedList<Integer> bufferNotes = new LinkedList<>();


    //Initialise le tableau des fréquences 
    private static void calculFrequences() {
        int moyenne = somme(notes);
        System.out.println(moyenne);
        for(int i=1;i<frequences.length; i++){
            frequences[i] =(float) notes[i] / moyenne;
            System.out.print(frequences[i]+" ");
        }
        System.out.println();
    }

    public static int somme(int[] toto){
        int tmp=0;
        for(int i=0;i<toto.length;i++){
            tmp+=toto[i];
        }
        return tmp;
    }

    // A-t-on déjà joué toute la séquence possible ?
    private static boolean stop() {
        int currentNote = bufferNotes.get(bufferNotes.size()-1);
        return bufferNotes.size()-1*(currentNote / somme(notes)) == getNbrOcc(currentNote);
    }

    // retourne nombre d'occurence d'une note
    private static int getNbrOcc(int note) {
        int occ = 0;
        for (int i = 0 ; i < bufferNotes.size() ; i++) {
            if (bufferNotes.get(i) == note) {
                occ++;
            }
        }
        return occ;
    }

    public static void main(String[] args){
        parsing(args[0]);
        calculFrequences();
        //GERER PRESEQUENCE ?
        //INITIALISER SI[] ?
        print(si);
        while(true){
            int bestNote = -1;
            float bestGauche = Integer.MAX_VALUE;
            for(int i=1; i<nombreNotes+1;i++){    
                if(isJouable(i)){
                    if(bestNote == -1) {
                        bestNote = i;
                        bufferNotes.add(bestNote);
                        
                    }
                    if(distanceGauche(i)<bestGauche) {
                        bestNote = i;
                        bestGauche = distanceGauche(i);
                    }
                }
            }
            if(bestNote==-1) { 
                System.out.println("BLOQUE");
            }
            else{ 
                System.out.println("Note jouee :"+bestNote);
                //JOUER LA NOTE
                si[bestNote]++;
                nbrJouees++;
            }
            if(stop()){
                System.out.println("INFINI");
                nbrJouees-=preSequence.length;
                break;
            }
        }
        print(si);
    }

    public static void print(int[] toto){
        for(int i=1;i<toto.length;i++){
            System.out.print(toto[i]+" ");
        }
        System.out.println("");
    }

    public static boolean isJouable(int index){
        return(si[index]<(frequences[index]*nbrJouees)+1 && si[index]>(frequences[index]*nbrJouees)-1);
    }

    public static float distanceGauche(int index){
        return(si[index]-frequences[index]*nbrJouees-1);
    }

    public static float[] decalage(int index){
        float[] decale=new float[2];
        decale[0]=(nbrJouees*frequences[index])-1;
        decale[1]=(nbrJouees*frequences[index])+1;
        return decale;
    }
    
    private static void parsing(String path) {
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
                    nombreNotes=intLine[0];
                    longueurInit=intLine[1];
                    System.out.print(nombreNotes+" "+longueurInit);
                    System.out.println(" ");
                }
                else if(compteur==1){
                    notes=new int[nombreNotes+1];
                    si=new int[nombreNotes+1];
                    frequences = new float[nombreNotes+1];
                    for(int i=1;i<notes.length;i++){
                        notes[i]=intLine[i-1];
                        System.out.print(notes[i]+" ");
                    }
                    System.out.println("");
        
                }
                else{
                    preSequence=new int[intLine.length];
                    nbrJouees=preSequence.length;
                    for(int i=0;i<intLine.length;i++){
                        preSequence[i]=intLine[i];
                        System.out.print(preSequence[i]+" ");
                    }
                    System.out.println("");
                    initSi();
                }
                compteur++;
            } 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
        }
	}

    public static void initSi(){
        for(int i=0;i<preSequence.length;i++){
            si[preSequence[i]]++;
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