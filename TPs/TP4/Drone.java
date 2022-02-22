import java.util.HashMap;



public class Drone{

    private int x,y,capacite;
    private HashMap<Integer,Integer> inventaire;

    public Drone(int x, int y, int capacite){
        this.x=x;
        this.y=y;
        this.capacite=capacite;
        this.inventaire=new HashMap<Integer,Integer>();
    }

    public void addProduct(int product, int weight) {
        if (inventaire.containsKey(product)) {
            inventaire.put(product, inventaire.get(product)+weight);
        } else {
            inventaire.put(product, weight);
        }
    }

    public void unload(int id) {
        inventaire.remove(id);
    }

}
    

