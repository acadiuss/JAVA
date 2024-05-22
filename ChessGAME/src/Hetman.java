import javax.swing.ImageIcon;

public class Hetman extends Figura {
    private final Wieza wieza;
    private final Goniec goniec;

    public Hetman(boolean isWhite) {
        super(isWhite ? "biały" : "czarny", new ImageIcon(Hetman.class.getResource(isWhite ? "/figurypng/wh.png" : "/figurypng/bh.png")), 9);
        this.wieza = new Wieza(isWhite);
        this.goniec = new Goniec(isWhite);
    }

    @Override
    public boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica) {
        if (startX == destX && startY == destY) {
            return false;
        }
        if (startX == destX || startY == destY) {
            return wieza.czyMozliwyRuch(startX, startY, destX, destY, szachownica);
        }
            return goniec.czyMozliwyRuch(startX, startY, destX, destY, szachownica);
    }
}
