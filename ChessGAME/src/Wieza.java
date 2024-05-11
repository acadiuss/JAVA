import javax.swing.ImageIcon;




//WYKONYWANIE RUCH BICIA TEGO SAMEGO KOLORU !!!!!!!!!!!rozbudowa

/// if (!szachownica[destX][destY].getFigura().getKolor().equals(this.getKolor())){
//            return false;}


public class Wieza extends Figura {

    public Wieza(boolean isWhite) {
        super(isWhite ? "biały" : "czarny", new ImageIcon(Wieza.class.getResource(isWhite ? "/figurypng/ww.png" : "/figurypng/bw.png")), 1000);
    }

    @Override
    public boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica) {

        if (startX == destX || startY == destY) {
            // Sprawdź, czy na drodze nie ma żadnych przeszkód
            int dX = Integer.compare(destX, startX);
            int dY = Integer.compare(destY, startY);
            int currentX = startX + dX;
            int currentY = startY + dY;

            while (currentX != destX || currentY != destY) {
                if (szachownica[currentX][currentY].getFigura() != null) {
                    //jesli na drodze jest figura nie wykonuj tuchu
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
