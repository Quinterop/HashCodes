import java.util.HashMap;



public class Drone{

    public int x, y, capacite, id;
    public HashMap<Integer,Integer> inventaire;

    public Drone(int x, int y, int capacite, int id){
        this.x=x;
        this.y=y;
        this.capacite=capacite;
        this.id = id;
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

    public int poidsTotal(){
        int cmp=0;
        for (Integer a : inventaire.keySet()) {
            cmp+=inventaire.get(a);
        }
        return cmp;
    }

}
    

