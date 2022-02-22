import java.util.HashMap;



public class Drone{

    int x,y,capacite;
    HashMap<Integer,Integer> inventaire;

    public Drone(int x, int y, int capacite){
        this.x=x;
        this.y=y;
        this.capacite=capacite;
        this.inventaire=new HashMap<Integer,Integer>();
    }

}
    

