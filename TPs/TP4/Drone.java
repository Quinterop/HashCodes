import java.util.HashMap;



public class Drone{
    public static int iD=-1;
    public int id=0;
    public int x,y,capacite;
    public HashMap<Integer,Integer> inventaire;

    public Drone(int x, int y, int capacite){
        this.x=x;
        this.y=y;
        this.capacite=capacite;
        this.inventaire=new HashMap<Integer,Integer>();
        iD++;
        id=iD;
    }

    public void addProduct(int product, int weight) {
        int tmp=0;
        if (inventaire.containsKey(product)) {
            tmp=inventaire.get(product);
            inventaire.put(product, tmp+weight);
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
    

