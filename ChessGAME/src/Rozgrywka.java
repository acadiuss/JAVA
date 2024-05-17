import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rozgrywka {


    private Pole[][] szachownica;
    private boolean isWhiteTurn = true;

    public Rozgrywka() {
        this.szachownica = new Pole[8][8];
        setupInitialBoard();
    }

    private void setupInitialBoard() {
        boolean z = false;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                szachownica[row][col] = new Pole(null);
            }
        }
        for (int k = 0; k <= 8; k+=7) {
            szachownica[k][0] = new Pole(new Wieza(z));
            szachownica[k][1] = new Pole(new Skoczek(z));
            szachownica[k][2] = new Pole(new Goniec(z));
            szachownica[k][3] = new Pole(new Hetman(z));
            szachownica[k][4] = new Pole(new Krol(z));
            szachownica[k][5] = new Pole(new Goniec(z));
            szachownica[k][6] = new Pole(new Skoczek(z));
            szachownica[k][7] = new Pole(new Wieza(z));
            z=!z;
        }
        for (int i = 0; i < 8; i++) {
            szachownica[1][i] = new Pole(new Pion(z));
            szachownica[6][i] = new Pole(new Pion(!z));
        }
    }

        public boolean wykonajRuch(int startX, int startY, int destX, int destY) {
        if (szachownica[startX][startY] != null && szachownica[startX][startY].getFigura() != null) {
            Figura movingFigura = szachownica[startX][startY].getFigura();

            // ruchy na zmiane
            if ((isWhiteTurn && movingFigura.getKolor().equals("biały")) || (!isWhiteTurn && movingFigura.getKolor().equals("czarny"))) {
                // zapobieganie przed ruchem na pozycje tego samego koloru
                if (szachownica[destX][destY].getFigura() != null && szachownica[destX][destY].getFigura().isKrol()) {
                    return false;
                }
                if (szachownica[destX][destY].getFigura() != null && szachownica[destX][destY].getFigura().getKolor().equals(movingFigura.getKolor())) {
                    return false;
                }
                if (movingFigura.czyMozliwyRuch(startX, startY, destX, destY, szachownica)) {
                    //przeniesienie
                    szachownica[destX][destY].setFigura(movingFigura);
                    szachownica[startX][startY].setFigura(null);

                    isWhiteTurn = !isWhiteTurn; //ruch nastepnego koloru

                    // Promocja piona
                    if (movingFigura instanceof Pion && (destX == 0 || destX == szachownica.length - 1)) {
                        Figura newPiece = ((Pion) movingFigura).promotePawn(movingFigura.getKolor().equals("biały"));
                        szachownica[destX][destY].setFigura(newPiece);
                    }

                    return true;
                }
            }
        }
        return false;
    }


    public Pole[][] getSzachownica() {
        return szachownica;
    }
}
