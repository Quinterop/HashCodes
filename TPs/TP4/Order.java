import java.util.HashMap;

public class Order {
    public int x, y;
    public int nbrItems;
    public HashMap<Integer, Integer> itemsList;
    
    public Order(int x, int y, int nbrItems, HashMap<Integer, Integer> itemsList) {
        this.x = x;
        this.y = y;
        this.nbrItems = nbrItems;
        this.itemsList = itemsList;
    }

    @Override
    public String toString() {
        String a = "(" + x + ";" + y + ")" + " Nombre d'objets: " + nbrItems + "; Types d'objets: " + itemsList;
        return a;
    }
}
