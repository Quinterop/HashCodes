import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

    

    public static void main(String[] args) {
        parsing("data/ex1_1.txt");
        System.out.println(nbMots);
        for(int i=0;i<nbMots;i++){
            System.out.println(mots.get(i));
        }
        System.out.println("chaine =\n"+chaineChar);
    }
}