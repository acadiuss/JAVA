import javax.swing.ImageIcon;

public class Krol extends Figura {

    public Krol(boolean isWhite) {
        super(isWhite ? "biały" : "czarny", new ImageIcon(Krol.class.getResource(isWhite ? "/figurypng/wk.png" : "/figurypng/bk.png")), 1000);
    }

    @Override
    public boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica) {
        if (startX == destX && startY == destY) {
            return false;
        }
        if (Math.abs(destX - startX) > 1 || Math.abs(destY - startY) > 1) {
            return false;
        }

        Figura targetFigura = szachownica[destX][destY].getFigura();

        if (targetFigura == null) {
            return true; // Pole docelowe jest puste
        } else if (!targetFigura.getKolor().equals(this.getKolor())) {
            return true;  // Na polu docelowym jest figura przeciwnika
        } else {
            return false; // ten sam kolor figury
        }}
    @Override

    public boolean isKrol() {
        return true;
    }

    }

