import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;

public class Main{

	private static ArrayList<String> ficLines = new ArrayList<String>();    
	private static ArrayList<String> mots = new ArrayList<String>();
	private static int nbMots=0;
	private static String chaineChar ="";

	public static void parsing(String path){

		File f = new File(path);

		try {
			try (Scanner sc = new Scanner(f)) {
				while (sc.hasNextLine()) {
					ficLines.add(sc.nextLine());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		nbMots=Integer.valueOf(ficLines.get(0));
		String concat = "";
		int i = 1;
		while(mots.size()<nbMots){
			if(ficLines.get(i).charAt(ficLines.get(i).length()-1)=='#'){
				mots.add(concat+ficLines.get(i).substring(0, ficLines.get(i).length()-1));
				concat="";
				i++;
			}else{
				concat += ficLines.get(i);
				i++;
			}
		}
		while(ficLines.get(i).charAt(ficLines.get(i).length()-1)!='#'){
			chaineChar += ficLines.get(i);
			i++;
		}
		chaineChar+=ficLines.get(i).substring(0, ficLines.get(i).length()-1);
	}

	public static int[] allOccurences(String phrase, String wordToSearch) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int index = 0;
		for(int i=0;i<phrase.length();i++) {	
			index = phrase.substring(i).indexOf(wordToSearch);
			if(index == 0) 
				list.add(i);	
		}
		int[] tab = new int[list.size()];
		int a = 0;
		for(Integer i : list) {
			tab[a++] = i.intValue();
		}
		return tab;
	}

    public static int biggestWord(){
        int len = 0;
        for(int i = 0; i<mots.length; i++){
            if(String.length(mots[i])>len)
            len = String.length(mots[i]);
        }
        return len;
    }

    public static void brute() {
        int winIndex = Integer.MAX_VALUE;
        int winLength = Integer.MAX_VALUE;
        int winString;
        
        
        int searchSize = biggestWord();
        for(int j = 0; j<mots.length ; j++){ //pour tous mots
            
            
            int[] occurences = allOccurences(alphabet,mots[j]); //pour toutes occurences
            for(int i = 0; i < occurences.length ; i++){
                
                int debut = occurences[i];
                String searchString = alphabet.substring(debut, debut+searchSize); //chercher si le string de taille x contient les mots
                
                boolean found = true;
                for(int k = 0; k<mots.length ; k++){
                    if(!searchString.contains(mots[k])){ 
                        found = false;
                    }
                }
                if(found==true){
                    winIndex = debut;
                    winLength = searchSize;
                    winString = searchString; //si oui ok
                    return;
                }else{
                    searchSize++; //si non on augmente 
                }
            }
        }
    }
    
	public static void main(String[] args) {
		parsing(args[0]);
		System.out.println(nbMots);
		for(int i=0;i<nbMots;i++){
			System.out.println(mots.get(i));
		}
		System.out.println("chaine =\n"+chaineChar);
		System.out.println();
		int[] test = allOccurences(chaineChar,mots.get(0));
		System.out.println(Arrays.toString(test));
	}

}
