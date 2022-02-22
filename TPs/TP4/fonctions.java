public class fonctions {
    
    public int poidsTrajet(int xA, int yA, int xB, int yB) {
        return sqrt(abs(xA - xB) ^ 2 + abs(yA - yB) ^ 2);
    }
}

public string loadDrone(Drone d, Warehouse w){
    if(d.x!=w.x || d.y!=w.y){
        d.x = w.x;
        d.y = w.y;
    }
        if(//capacité > load) {
            //ajouter load a capacité et le retirer a w
        }
}
