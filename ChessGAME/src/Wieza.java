import javax.swing.ImageIcon;




//WYKONYWANIE RUCH BICIA TEGO SAMEGO KOLORU !!!!!!!!!!!rozbudowa

/// if (!szachownica[destX][destY].getFigura().getKolor().equals(this.getKolor())){
//            return false;}


public class Wieza extends Figura {

    public Wieza(boolean isWhite) {
        super(isWhite ? "biały" : "czarny", new ImageIcon(Wieza.class.getResource(isWhite ? "/figurypng/ww.png" : "/figurypng/bw.png")), 1000);
    }

    @Override
    public int getWaga() {
        return 5;
    }

    @Override
    public boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica) {
        if (startX == destX && startY == destY) {
            return false;
        }

        // Sprawdź, czy figura porusza się wzdłuż kolumny lub rzędu
        if (startX == destX || startY == destY) {
            // Sprawdź, czy na drodze nie ma żadnych przeszkód
            int dX = Integer.compare(destX, startX);
            int dY = Integer.compare(destY, startY);
            int currentX = startX + dX;
            int currentY = startY + dY;

            while (currentX != destX || currentY != destY) {
                if (szachownica[currentX][currentY].getFigura() != null) {
                    // Jest przeszkoda na drodze
                    return false;
                }
                currentX += dX;
                currentY += dY;
            }
            return true;
        }
        return false;

    }
}
