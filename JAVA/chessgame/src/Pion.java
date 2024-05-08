import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Pion extends Figura {

    public Pion(boolean isWhite) {
        super(isWhite ? "biały" : "czarny", new ImageIcon(Pion.class.getResource(isWhite ? "/figurypng/wp.png" : "/figurypng/bp.png")), 1000);
    }

    @Override
    public boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica) {
        // Prevent moving to the same square
        if (startX == destX && startY == destY)
        {
            return false;
        }

        // Direction of movement (+1 for white, -1 for black)
        int direction = this.getKolor().equals("biały") ? 1 : -1;

        // Check if this is the first move
        boolean isFirstMove = (this.getKolor().equals("biały") && startX == 1) || (this.getKolor().equals("czarny") && startX == 6);

        // Double step from starting position
        boolean isDoubleStep = (destX == startX + 2 * direction) && (startY == destY) && isFirstMove &&
                (szachownica[startX + direction][startY].getFigura() == null) && (szachownica[destX][destY].getFigura() == null);

        // Single step forward
        boolean isSingleStep = (destX == startX + direction) && (destY == startY) && (szachownica[destX][destY].getFigura() == null);

        // Normal capture diagonally
        boolean isNormalCapture = (Math.abs(destY - startY) == 1) && (destX == startX + direction) &&
                (szachownica[destX][destY].getFigura() != null) && !szachownica[destX][destY].getFigura().getKolor().equals(this.getKolor());

        // En passant capture
        boolean isEnPassant = (Math.abs(destY - startY) == 1) && (destX == startX + direction) &&
                (szachownica[startX][destY].getFigura() != null) &&
                (szachownica[startX][destY].getFigura().getKolor().equals(this.getKolor().equals("biały") ? "czarny" : "biały"));


        // Validate if any valid move is possible
        boolean isValidMove = isSingleStep || isDoubleStep || isNormalCapture || isEnPassant;


        if (isValidMove) {
            if (destX == 0 || destX == 7) {  // Check if the pawn reaches the last row for promotion
                Figura promotedPiece = choosePromotionPiece(this.getKolor().equals("biały"));
                szachownica[destX][destY].setFigura(promotedPiece);

            } else {
                szachownica[destX][destY].setFigura(szachownica[startX][startY].getFigura());

            }
            return true;
        }
        return true;
    }

    private Figura choosePromotionPiece(boolean isWhite) {
        Object[] options = {"Pion", "Skoczek","Goniec","Wieza", "Hetman"};
        int choice = JOptionPane.showOptionDialog(null, "Wybierz figurę na promocję:",
                "Promocja Piona", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        switch (choice) {
            case 1:
                return new Pion(isWhite);
            case 2:
                return new Skoczek(isWhite);
            case 3:
                return new Goniec(isWhite);
            case 4:
                return new Wieza(isWhite);
            default:
                return new Hetman(isWhite);
        }
    }

}
