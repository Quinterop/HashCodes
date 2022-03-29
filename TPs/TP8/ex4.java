public class ex4 {


    static int[] tempsIn;
    static int[] tempsOut;
    static int nbrIn;
    static int nbrOut;



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
                switch(compteur){
                    case 0:
                        nbrIn=intLine[0];
                        break;
                    case 1:
                        nbrOut=intLine[0];
                        break;
                    case 2:
                        tempsIn=new int[intLine.length];
                        for(int i=0;i<intLine.length;i++){
                            tempsIn[i]=intLine[i];
                            System.out.print(tempsIn[i]+" ");
                        }
                        System.out.println(" ");
                        break;
                    case 3:
                    tempsOut=new int[intLine.length];
                    for(int i=0;i<intLine.length;i++){
                        tempsOut[i]=intLine[i];
                        System.out.print(tempsOut[i]+" ");
                    }
                    System.out.println(" ");
                    break;
                }
                compteur++;
            } 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
        }
	}

    
}
