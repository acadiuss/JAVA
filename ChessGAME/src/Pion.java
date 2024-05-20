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

        if (startX == destX && startY == destY) {
            return false;
        }
        // +1 pole
        if (startX + direction == destX && startY == destY && szachownica[destX][destY].getFigura() == null) {
            return true;
        }

        // +2 pola pierwszy ruch tylko
        if (czyPierwszyRuch && startX + 2 * direction == destX && startY == destY &&
                szachownica[destX - direction][destY].getFigura() == null &&
                szachownica[destX][destY].getFigura() == null) {
            return true;
        }

        // bicie
        if ((startX + direction == destX && (startY + 1 == destY || startY - 1 == destY)) &&
                szachownica[destX][destY].getFigura() != null &&
                !szachownica[destX][destY].getFigura().getKolor().equals(this.kolor)) {
            return true;
        }
        // bicie w przelocie
        if (startX + direction == destX && (startY + 1 == destY || startY - 1 == destY) &&
                szachownica[destX][destY].getFigura() == null) {
            Figura sasiad = szachownica[startX][startY + (destY - startY)].getFigura();
            if (sasiad instanceof Pion && ((Pion) sasiad).czyPierwszyRuch &&
                    !sasiad.getKolor().equals(this.kolor)) {
                // Usuwanie piona przeciwnika z pola, przez które pion przeskoczył
                szachownica[startX][startY + (destY - startY)].setFigura(null);
                return true;
            }
        }

        // czy promocja piona
        if ((destX == promotionRow) && (startX==1 || startX ==7)) {
            return true;
        }
        return false;
    }


    public Figura promocja(boolean isWhite) {
        Object[] options = {"Pion","Skoczek", "Goniec", "Wieza" ,"Hetman"};
        int wybiez = JOptionPane.showOptionDialog(null, "Wybierz figurę na promocję:",
                "Promocja Piona", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        switch (wybiez) {
            case 1: return new Skoczek(isWhite);
            case 2: return new Goniec(isWhite);
            case 3: return new Wieza(isWhite);
            case 4: return new Hetman(isWhite);
            default: return new Pion(isWhite);
        }
    }

}
