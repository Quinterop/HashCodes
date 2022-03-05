import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    private static int gridX;
    private static int gridY;
    private static int nbrDrones;
    private static int shiftingMax;
    private static int maxWeight;
    private static int nbrProducts;
    private static int nbrWarehouse;
    private static int nbrOrders;

    private static HashMap<Integer, Integer> productsBindweight = new HashMap<Integer, Integer>();

    private static List<Entrepot> warehouses = new ArrayList<Entrepot>();

    // Liste de toutes les lignes
    private static ArrayList<String> ficLines = new ArrayList<String>();

    // tableau pour stocker chaque mot de la ligne courante
    private static String[] lineSplited;

    private static ArrayList<Drone> drones = new ArrayList<Drone>();

    private static LinkedList<Order> ordersList = new LinkedList<Order>();

    // Pour convertir la chaine de caractère en tableau
    public static String[] split_on_char(String line) {
        return line.split(" ");
    }

    private static void parsing(String path) {
        // ouverture du fichier passé en argument
        File f = new File(path);

        // lecture du fichier et ajout de chaque ligne dans l'ArrayList de type String
        try {
            try (Scanner sc = new Scanner(f)) {
                while (sc.hasNextLine()) {
                    ficLines.add(sc.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // parcours de la liste de lignes
        int i = 0;
        while (i < ficLines.size()) {
            lineSplited = split_on_char(ficLines.get(i));

            if (i == 0) {
                gridX = Integer.parseInt(lineSplited[0]);
                gridY = Integer.parseInt(lineSplited[1]);
                nbrDrones = Integer.parseInt(lineSplited[2]);
                shiftingMax = Integer.parseInt(lineSplited[3]);
                maxWeight = Integer.parseInt(lineSplited[4]);
                i+=1;
                continue;
            }

            if (i == 1) {
                nbrProducts = Integer.parseInt(lineSplited[0]);
                i+=1;
                continue;
            }
            if (i == 2) {
                for (int j = 0; j < nbrProducts; j++) {
                    productsBindweight.put(j, Integer.parseInt(lineSplited[j]));
                }
                i+=1;
                continue;
            }
            // WAREHOUSE
            if (i == 3) {
                nbrWarehouse = Integer.parseInt(lineSplited[0]);
                i+=1;
                continue;
            }
            if (i < i + nbrWarehouse * 2) {
                System.out.println("rentre dans cond");
                int iterator = 0;
                while (iterator < nbrWarehouse) {
                    Entrepot e = new Entrepot(Integer.parseInt(lineSplited[0]), Integer.parseInt(lineSplited[1]));
                    warehouses.add(e);
                    i += 1;
                    System.out.println("i="+i);
                    lineSplited = split_on_char(ficLines.get(i));
                    for (int j = 0; j < lineSplited.length; j++) {
                        
                        e.getInventaire().put(j, Integer.parseInt(lineSplited[j]));
                    }
                    i += 1;
                    lineSplited = split_on_char(ficLines.get(i));
                    iterator+=1;
                }
            }

            int iterator2 = 0;
            while (iterator2 < nbrDrones) {
                if(warehouses.size() == 0){
                    System.out.println("liste vide ");
                    System.exit(1);
                }else {
                    drones.add(new Drone(warehouses.get(0).getX(), warehouses.get(0).getY(), maxWeight));
                    iterator2 += 1;
                }
                
            }

            // ORDERS
            if (i == i + nbrWarehouse * 2 + 1) {
                nbrOrders = Integer.parseInt(lineSplited[0]);
            }

            if (i > i + nbrWarehouse * 2 + 1) {
                // coordonnées du point d'arrivé
                int x = Integer.parseInt(lineSplited[0]);
                int y = Integer.parseInt(lineSplited[1]);
                i += 1;
                lineSplited = split_on_char(ficLines.get(i));
                // nombre d'objets à livrer
                int nbrItems = Integer.parseInt(lineSplited[0]);
                i += 1;
                lineSplited = split_on_char(ficLines.get(i));

                // liste des items à livrer
                HashMap<Integer, Integer> itemsList = new HashMap<Integer, Integer>();
                for (int j = 0; j < nbrItems; j++) {
                    int id = Integer.parseInt(lineSplited[j]);
                    // si item déjà présent, on augmente le poids
                    if (itemsList.containsValue((Integer.parseInt(lineSplited[j])))) {
                        itemsList.put(id, itemsList.get(id) + productsBindweight.get(id));
                    }
                    // si item pas présent on ajoute à poids 1
                    itemsList.put(id, productsBindweight.get(id));
                }
                ordersList.add(new Order(x, y, nbrItems, itemsList));
            }

            // WHILE INCREMENTATION
            i += 1;
        }
    }

    public static String loadDrone(Drone d, Entrepot w, Order o, Integer droneID, Integer entrID) {
        int poids = 0;

        for (Map.Entry<Integer, Integer> pair : o.itemsList.entrySet()) {
            poids = poids + pair.getValue();
        }

        if (d.capacite > poids) {
            o.itemsList.forEach((k, v) -> {
                d.addProduct(k, v);
            });
            d.x = w.x;
            d.y = w.y;
            String rep = "";

            for (Map.Entry<Integer, Integer> pair : o.itemsList.entrySet()) {
                int n = pair.getKey();
                int amount = pair.getValue() / productsBindweight.get(n);
                rep = rep + '\n' + droneID.toString() + " L " + entrID.toString() + " " + n + " " + amount;
            }

        }
        return "nope";
    }

    public static int poidsTrajet(int xA, int yA, int xB, int yB) {
        return (int) Math.sqrt(Math.pow(Math.abs(xA - xB), 2) + Math.pow(Math.abs(yA - yB), 2));
    }

    public static void main(String[] args) {

        parsing("inputs/busy_day.in");
        System.out.println(loadDrone(drones.get(0),warehouses.get(0),ordersList.get(0),0,0));
        

    }
}