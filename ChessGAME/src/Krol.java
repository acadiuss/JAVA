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
        if (Math.abs(destX - startX) > 1 || Math.abs(destY - startY) > 1) return false;
        //czy wolne pole czy zajete przez przeciwnika
        Figura targetFigura = szachownica[destX][destY].getFigura();
        return targetFigura == null || !targetFigura.getKolor().equals(this.getKolor());
    }
    @Override
    public boolean isKrol() {
        return true;
    }

    }

