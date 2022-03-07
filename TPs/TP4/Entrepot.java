import java.util.HashMap;


public class Entrepot {
    
    int x, y, id;
    HashMap<Integer,Integer> inventaire;

    public Entrepot(int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
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
