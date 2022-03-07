import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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

    // liste des entrepots
    private static List<Entrepot> warehouses = new LinkedList<Entrepot>();

    // Liste de toutes les lignes
    private static List<String> ficLines = new LinkedList<String>();

    // tableau pour stocker chaque mot de la ligne courante
    private static String[] lineSplited;

    private static List<Drone> drones = new LinkedList<Drone>();

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

            // initialisation des premiers champs
            if (i == 0) {
                gridX = Integer.parseInt(lineSplited[0]);
                gridY = Integer.parseInt(lineSplited[1]);
                nbrDrones = Integer.parseInt(lineSplited[2]);
                shiftingMax = Integer.parseInt(lineSplited[3]);
                maxWeight = Integer.parseInt(lineSplited[4]);
                i += 1;
                continue;
            }

            // initialisation nombre de produits
            if (i == 1) {
                nbrProducts = Integer.parseInt(lineSplited[0]);
                i += 1;
                continue;
            }

            // initialisation de la map liant chaque produit à son poids
            if (i == 2) {
                for (int j = 0; j < nbrProducts; j++) {
                    productsBindweight.put(j, Integer.parseInt(lineSplited[j]));
                }
                i += 1;
                continue;
            }
            
            // initialisation du nombre d'entrepots
            if (i == 3) {
                nbrWarehouse = Integer.parseInt(lineSplited[0]);
                i += 1;
                continue;
            }

            // création des entrepots
            if (i < 3 + nbrWarehouse * 2 + 1) {
                int iterator = 0;
                while (iterator < nbrWarehouse) {
                    // création d'un entrepot avec ses coordonnées (x, y)
                    Entrepot e = new Entrepot(Integer.parseInt(lineSplited[0]), Integer.parseInt(lineSplited[1]), warehouses.size());
                    // ajout du nouvel entrepot à la liste d'entrepots
                    warehouses.add(e);
                    i += 1;
                    lineSplited = split_on_char(ficLines.get(i));
                    // remplissage de l'entrepot avec ses produits
                    for (int j = 0; j < lineSplited.length; j++) {
                        e.getInventaire().put(j, Integer.parseInt(lineSplited[j]));
                    }
                    i += 1;
                    lineSplited = split_on_char(ficLines.get(i));
                    iterator += 1;
                }

                int iterator2 = 0;
                while (iterator2 < nbrDrones) {
                    if (warehouses.size() == 0) {
                        System.out.println("liste vide ");
                        System.exit(1);
                    } else {
                        // création des drones à la position du premier entrepot
                        drones.add(new Drone(warehouses.get(0).getX(), warehouses.get(0).getY(), maxWeight, drones.size()));
                        iterator2 += 1;
                    }

                }
                continue;
            }

            // récupération du nombre de commandes à traiter
            if (i == 3 + nbrWarehouse * 2 + 1) {
                nbrOrders = Integer.parseInt(lineSplited[0]);
                i += 1;
                continue;
            }

            if (i > 3 + nbrWarehouse * 2 + 1) {
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
                    if (itemsList.containsKey(id)) {
                        itemsList.put(id, itemsList.get(id) + productsBindweight.get(id));
                    }
                    // si item pas présent on ajoute à poids 1
                    itemsList.put(id, productsBindweight.get(id));
                }
                ordersList.add(new Order(x, y, nbrItems, itemsList));
            }
            i++;
        }
    }

    public static String loadDrone(Drone d, Entrepot w, Order o) {
        int poids = 0;
        for (Map.Entry<Integer, Integer> pair : o.itemsList.entrySet()) {
            poids = poids + pair.getValue();
            if(poids>d.capacite){
                poids=poids-pair.getValue();
            }
            else{
                pair.setValue(0);
            }
        }
        HashMap<Integer,Integer> nItems=new HashMap<>();
        for(Map.Entry<Integer, Integer> nPair : o.itemsList.entrySet()){
            if(nPair.getValue()!=0){
                nItems.put(nPair.getKey(), nPair.getValue());
            }
        }
        Order order=new Order(o.x, o.y, o.nbrItems-1, nItems);
        ordersList.add(order);
        ordersList.remove(o);
        System.out.println("Capacite du drone: "+d.capacite+" Poids de la commande: "+poids);
        if (d.capacite >= poids) {
            o.itemsList.forEach((k, v) -> {
                d.addProduct(k, v);
            });
            d.x = w.x;
            d.y = w.y;
            String rep = "";

            for (Map.Entry<Integer, Integer> pair : o.itemsList.entrySet()) {
                int n = pair.getKey();
                int amount = pair.getValue() / productsBindweight.get(n);
                dechargeEntrepot(w, n, amount);
                rep = rep + d.id + " L " + w.id + " " + n + " " + amount + '\n';
            }
            return rep;
        }
        return "nope";
    }

    public static String deliver(Drone d, Order o, Integer droneID, Integer orderID) {
        boolean hasEnough = true;
        for (Map.Entry<Integer, Integer> pair : o.itemsList.entrySet()) {
            if (d.inventaire.get(pair.getKey()) < pair.getValue()) {
                hasEnough = false;
            }
        }

        if (hasEnough) {
            d.x = o.x;
            d.y = o.y;
            String rep = "";
            for (Map.Entry<Integer, Integer> pair : o.itemsList.entrySet()) {
                int n = pair.getKey();
                int amount = pair.getValue() / productsBindweight.get(n);
                dechargeDrone(d, n, amount);
                rep = rep + droneID.toString() + " D " + orderID.toString() + " " + n + " " + amount + '\n' ;
            }
            return rep;
        }
        return "nope";
    }

    // donne les distance entre les points A et B
    public static int poidsTrajet(int xA, int yA, int xB, int yB) {
        return (int) Math.sqrt(Math.pow(Math.abs(xA - xB), 2) + Math.pow(Math.abs(yA - yB), 2));
    }

    // retire le produit et son nombre de l'entrepot e
    public static void dechargeEntrepot(Entrepot e, int produit, int amount) {
        for (int i = 0; i < amount; i++) {
            if (e.inventaire.get(produit) >= amount) {
                e.inventaire.put(produit, e.inventaire.get(produit) - productsBindweight.get(produit));
            } 
        }
    }

    // retire le produit et son nombre du drone d
    public static void dechargeDrone(Drone d, int produit, int amount) {
        for (int i = 0; i < amount; i++) {
            if (d.inventaire.get(produit) >= amount) {
                d.inventaire.put(produit, d.inventaire.get(produit) - productsBindweight.get(produit));
            }
        }
    }

    // écriture du résultat dans un fichier res.out
    private static void writeOutput(String res) {
		try {
			System.out.println("Debut ecriture resultat");
			File outputfile = new File("res.out");
			outputfile.createNewFile();

			FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

            bw.write(res);

			bw.close();
			System.out.println("Fin ecriture resultat");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    


    private static Entrepot closestWarehouse(Drone d, Order o) {
        Entrepot e = warehouses.get(0);
        int poids = 0;
        for (Map.Entry<Integer, Integer> pair : o.itemsList.entrySet()) {
            poids = poids + pair.getValue();
        }
        int bestDist = -1;
        for (int i = 0; i < warehouses.size(); i++) {
            int distance = poidsTrajet(d.x, d.y, warehouses.get(i).x, warehouses.get(i).y);
            if (bestDist == -1 || bestDist > distance && canLoad(o, warehouses.get(i))) {
                e = warehouses.get(i);
                bestDist = distance;
            }
        }
        return e;
    }

    private static boolean canLoad(Order o, Entrepot e) {
        for (Map.Entry<Integer, Integer> pair : o.itemsList.entrySet()) {
            int n = pair.getKey();
            if (e.inventaire.get(n) < pair.getValue()) {
                return false;
            }
        }
        return true;
    }

    public static int distanceCommande(Drone d, Order o, int droneID) {
        int dToW;
        int WtoD;
        Entrepot pres = closestWarehouse(d, o);
        dToW = poidsTrajet(d.x, d.y, pres.x, pres.y);
        WtoD = poidsTrajet(o.x, o.y, pres.x, pres.y);
        return dToW + WtoD;
    }
    
    private static Order closestOrder(Entrepot w){
        Order o = ordersList.get(0);
        int bestDist = -1;
        for(int i=0;i<ordersList.size();i++){
            int distance = poidsTrajet(w.x,w.y,ordersList.get(i).x,ordersList.get(i).y);
            if(bestDist==-1 || bestDist>distance){
                o = ordersList.get(i);
                bestDist=distance;
            }
        }

        return o;
    }


    public static void main(String[] args) {

        parsing("inputs/busy_day.in");
        String res = loadDrone(drones.get(0), warehouses.get(0), ordersList.get(0));
        System.out.println(res);
        System.out.println(ordersList.get(0));
        writeOutput(res);
    }

    /*
     * public static String algo(){
     * if(drones.size()>ordersList.size()){
     * droneid =0;
     * entrid = 0;
     * for (commandes c) {
     * for(drones d)
     * loadDrone(d, entrepot 0, c, droneID, entrID)
     * }
     * }else{
     * 
     * }
     * }
     */
}