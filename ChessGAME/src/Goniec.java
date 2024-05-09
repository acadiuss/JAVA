import javax.swing.ImageIcon;

public class Goniec extends Figura {

    public Goniec(boolean isWhite) {
        super(isWhite ? "biały" : "czarny", new ImageIcon(Goniec.class.getResource(isWhite ? "/figurypng/wg.png" : "/figurypng/bg.png")), 1000);
    }

    @Override
    public boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica) {
        // Sprawdź czy ruch jest po skosie (ruch w górę lub w dół o tę samą różnicę co w lewo lub prawo)
        int diffX = Math.abs(destX - startX);
        int diffY = Math.abs(destY - startY);
        if (diffX == diffY) {
            // Sprawdź czy na drodze ruchu nie ma przeszkód
            int stepX = destX > startX ? 1 : -1; // Kierunek ruchu po osi X
            int stepY = destY > startY ? 1 : -1; // Kierunek ruchu po osi Y
            for (int i = 1; i < diffX; i++) {
                int checkX = startX + i * stepX;
                int checkY = startY + i * stepY;
                if (szachownica[checkX][checkY].getFigura() != null) {
                    // Na drodze ruchu znajduje się figura, co blokuje ruch gońca
                    return false;
                }
            }
            return true; // Ruch jest możliwy
        }
        return false; // Ruch nie jest po skosie
    }
}