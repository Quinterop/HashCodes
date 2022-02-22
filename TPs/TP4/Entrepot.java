import java.util.HashMap;


public class Entrepot {
    
    int x,y;
    HashMap<Integer,Integer> inventaire;

    public Entrepot(int x, int y){
        this.x=x;
        this.y=y;
        this.inventaire= new HashMap<>();
    }

    public HashMap<Integer, Integer> getInventaire() {
        return inventaire;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

}
