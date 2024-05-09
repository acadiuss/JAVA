import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Pion extends Figura {
    private boolean czyPierwszyRuch;

    public Pion(boolean isWhite) {
        super(isWhite ? "biały" : "czarny", new ImageIcon(Pion.class.getResource(isWhite ? "/figurypng/wp.png" : "/figurypng/bp.png")), 1);
        this.czyPierwszyRuch = true;
    }

    @Override
    public boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica) {
        int direction = this.kolor.equals("biały") ? -1 : 1;
        int promotionRow = this.kolor.equals("biały") ? 0 : szachownica.length - 1;

        // Normalny ruch o jedno pole do przodu na puste pole
        if (startX + direction == destX && startY == destY && szachownica[destX][destY].getFigura() == null) {
            return true;
        }

        // Ruch o dwa pola do przodu na puste pole jako pierwszy ruch
        if (czyPierwszyRuch && startX + 2 * direction == destX && startY == destY &&
                szachownica[destX - direction][destY].getFigura() == null &&
                szachownica[destX][destY].getFigura() == null) {
            return true;
        }

        // Bicie na skos
        if ((startX + direction == destX && (startY + 1 == destY || startY - 1 == destY)) &&
                szachownica[destX][destY].getFigura() != null &&
                !szachownica[destX][destY].getFigura().getKolor().equals(this.kolor)) {
            return true;
        }

        // Sprawdzenie promocji piona
        if (destX == promotionRow) {
            return true;
        }

        return false; // Inne ruchy są niemożliwe
    }

    public Figura promotePawn(boolean isWhite) {
        Object[] options = {"Hetman", "Wieza", "Goniec", "Skoczek"};
        int choice = JOptionPane.showOptionDialog(null, "Wybierz figurę na promocję:",
                "Promocja Piona", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        switch (choice) {
            case 1: return new Wieza(isWhite);
            case 2: return new Goniec(isWhite);
            case 3: return new Skoczek(isWhite);
            default: return new Hetman(isWhite);
        }
    }
}
