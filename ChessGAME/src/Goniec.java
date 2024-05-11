import javax.swing.ImageIcon;

public class Goniec extends Figura {

    public Goniec(boolean isWhite) {
        super(isWhite ? "biały" : "czarny", new ImageIcon(Goniec.class.getResource(isWhite ? "/figurypng/wg.png" : "/figurypng/bg.png")), 1000);
    }

    @Override
    public boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica) {
        if (startX==destX && startY==destY) {
            return false;
        }

        int diffX = Math.abs(destX - startX);
        int diffY = Math.abs(destY - startY);
        if (diffX == diffY) {
            // Sprawdź czy na drodze ruchu nie ma przeszkód
            int stepX = destX > startX ? 1 : -1; //x
            int stepY = destY > startY ? 1 : -1; //y
            for (int i = 1; i < diffX; i++) {
                int checkX = startX + i * stepX;
                int checkY = startY + i * stepY;
                if (szachownica[checkX][checkY].getFigura() != null) { // czy na drodze jest figura

                    return false;
                }
            }
            return true;
        }
        return false;
    }
}