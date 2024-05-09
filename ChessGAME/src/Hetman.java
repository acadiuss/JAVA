import javax.swing.ImageIcon;

public class Hetman extends Figura {

    public Hetman(boolean isWhite) {
        super(isWhite ? "biały" : "czarny", new ImageIcon(Hetman.class.getResource(isWhite ? "/figurypng/wh.png" : "/figurypng/bh.png")), 1000);
    }

    @Override
    public boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica) {
        // Sprawdź, czy figura nie próbuje poruszyć się na to samo pole
        if (startX == destX && startY == destY) {
            return false;
        }

        // Oblicz różnice między współrzędnymi X i Y
        int deltaX = Math.abs(destX - startX);
        int deltaY = Math.abs(destY - startY);

        // Sprawdź, czy ruch jest liniowy (poziomo, pionowo lub na ukos)
        boolean isLinear = (startX == destX || startY == destY || deltaX == deltaY);

        // Sprawdź, czy nie ma przeszkód na drodze ruchu
        if (isLinear) {
            // Ruch poziomy
            if (startX == destX) {
                int minY = Math.min(startY, destY) + 1;
                int maxY = Math.max(startY, destY);
                for (int y = minY; y < maxY; y++) {
                    if (szachownica[startX][y].getFigura() != null) {
                        return false;
                    }
                }
            }
            // Ruch pionowy
            else if (startY == destY) {
                int minX = Math.min(startX, destX) + 1;
                int maxX = Math.max(startX, destX);
                for (int x = minX; x < maxX; x++) {
                    if (szachownica[x][startY].getFigura() != null) {
                        return false;
                    }
                }
            }
            // Ruch na ukos
            else {
                int minX = Math.min(startX, destX) + 1;
                int minY = Math.min(startY, destY) + 1;
                int maxX = Math.max(startX, destX);
                int maxY = Math.max(startY, destY);
                int x = minX;
                int y = minY;
                while (x < maxX) {
                    if (szachownica[x][y].getFigura() != null) {
                        return false;
                    }
                    x++;
                    y++;
                }
            }
        }

        // Jeśli ruch jest liniowy i nie ma przeszkód, lub ruch jest na ukos, zwróć true
        return isLinear;
    }

}
