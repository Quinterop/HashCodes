public class Brute {
    
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
    
    public static int biggestWord(){
        int len = 0;
        for(int i = 0; i<mots.length; i++){
            if(String.length(mots[i])>len)
            len = String.length(mots[i]);
        }
        return len;
    }
    
}
