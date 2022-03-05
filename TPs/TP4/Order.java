import java.util.HashMap;

public class Order {
    public static int x, y;
    public static int nbrItems;
    public static HashMap<Integer, Integer> itemsList;
    
    public Order(int x, int y, int nbrItems, HashMap<Integer, Integer> itemsList) {
        this.x = x;
        this.y = y;
        this.nbrItems = nbrItems;
        this.itemsList = itemsList;
    }
}
