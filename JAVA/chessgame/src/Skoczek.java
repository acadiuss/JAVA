import javax.swing.ImageIcon;

public class Skoczek extends Figura {

    public Skoczek(boolean isWhite) {
        super(isWhite ? "biały" : "czarny", new ImageIcon(Skoczek.class.getResource(isWhite ? "/figurypng/ws.png" : "/figurypng/bs.png")), 1000);
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

        // Skoczek może poruszać się o dwa pola w jednym kierunku i jedno pole w drugim
        // Dlatego różnice powinny być odpowiednio 1 i 2 lub 2 i 1
        boolean isDeltaValid = (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);

        // Jeśli różnice są zgodne z regułą "L" dla skoczka, zwróć true, w przeciwnym razie false
        return isDeltaValid;
    }
}