public class fonctions {
    public int poidsTrajet(int xA, int yA, int xB, int yB) {
        return sqrt(abs(xA - xB) ^ 2 + abs(yA - yB) ^ 2);
    }
}
