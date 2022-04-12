public class Main {
    
    
    public static void main(String[] args) {
        for(int i = 0; i < nbdes; i++) {
            
        }
    }
    public static int[] colors(){
        int[] colors = new int[nbcols];
        for (int i = 0; i < nbdes; i++) {
            colors[des.get(i)[0]]++;
        }
        return colors;
    }

    public static int highestColor(){
        int[] colors = colors();
        int max = 0;
        for (int i = 0; i < nbcols; i++) {
            if (colors[i] > max) {
                max = colors[i];
            }
        }
        return max;
    }

    public void place(){
        for(int i = 0;i<nbdes; i++){
            //IF PREVIOUS COLOR IS NOT THE SAME
            
        }
    }
}