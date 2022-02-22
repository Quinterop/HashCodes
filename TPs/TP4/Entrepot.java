import java.util.HashMap;


public class Entrepot {
    
    int x,y;
    HashMap<Integer,Integer> inventaire;

    public Entrepot(int x, int y, int capacite){
        this.x=x;
        this.y=y;
        this.inventaire=new HashMap<Integer,Integer>();
    }

}
