import java.util.LinkedList;
import java.util.List;

public class Order {
    public static int x, y;
    public static int nbrItems;
    public static List<Integer> itemsList;
    
    public Order(int x, int y, int nbrItems, List<Integer> itemsList) {
        this.x = x;
        this.y = y;
        this.nbrItems = nbrItems;
        this.itemsList = itemsList;
    }
}
