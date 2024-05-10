import javax.swing.ImageIcon;

public class Krol extends Figura {

    public Krol(boolean isWhite) {
        super(isWhite ? "biały" : "czarny", new ImageIcon(Krol.class.getResource(isWhite ? "/figurypng/wk.png" : "/figurypng/bk.png")), 1000);
    }

    @Override
    public boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica) {
        // Szybkie sprawdzenie, czy ruch jest poza zakresem jednego pola w każdym kierunku
        if (Math.abs(destX - startX) > 1 || Math.abs(destY - startY) > 1) return false;

        // Sprawdzenie czy docelowe pole jest wolne lub zajęte przez przeciwnika
        Figura targetFigura = szachownica[destX][destY].getFigura();
        return targetFigura == null || !targetFigura.getKolor().equals(this.getKolor());
    }
}
