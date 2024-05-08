import javax.swing.ImageIcon;

public class Krol extends Figura {

    public Krol(boolean isWhite) {
        super(isWhite ? "biały" : "czarny", new ImageIcon(Krol.class.getResource(isWhite ? "/figurypng/wk.png" : "/figurypng/bk.png")), 1000);
    }

    @Override
    public boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica) {
        // Sprawdź czy docelowe pole znajduje się w sąsiedztwie pola, na którym aktualnie znajduje się król
        if (Math.abs(destX - startX) <= 1 && Math.abs(destY - startY) <= 1) {
            // Sprawdź czy docelowe pole jest puste lub zajęte przez figurę przeciwnika
            return szachownica[destX][destY].getFigura() == null || !szachownica[destX][destY].getFigura().getKolor().equals(this.getKolor());
        }
        return false; // Ruch króla jest niemożliwy
    }
}
