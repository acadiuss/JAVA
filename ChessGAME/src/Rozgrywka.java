import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rozgrywka {

    private final int SIZE;
    private Pole[][] szachownica;
    private boolean isWhiteTurn = true;

    public Rozgrywka(int size) {
        this.SIZE = size;
        this.szachownica = new Pole[SIZE][SIZE];
        setupInitialBoard();
    }

    private void setupInitialBoard() {
        // inicjaalizacja kazdego pola jako puste
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < SIZE; col++) {
                szachownica[row][col] = new Pole(null);
            }
        }
        //rozmieszczenie poczatkowe figur na szachownicy

        szachownica[0][0] = new Pole(new Wieza(false));
        //szachownica[0][1] = new Pole(new Skoczek(false));
        //szachownica[0][2] = new Pole(new Goniec(false));
        //szachownica[0][3] = new Pole(new Hetman(false));
        szachownica[0][4] = new Pole(new Krol(false));
        //szachownica[0][5] = new Pole(new Goniec(false));
        //szachownica[0][6] = new Pole(new Skoczek(false));
        szachownica[0][7] = new Pole(new Wieza(false));

        for (int i = 0; i < 8; i++) {
            szachownica[1][i] = new Pole(new Pion(false));
            szachownica[6][i] = new Pole(new Pion(true));
        }

        szachownica[7][0] = new Pole(new Wieza(true));
        //szachownica[7][1] = new Pole(new Skoczek(true));
        //szachownica[7][2] = new Pole(new Goniec(true));
        //szachownica[7][3] = new Pole(new Hetman(true));
        szachownica[7][4] = new Pole(new Krol(true));
        //szachownica[7][5] = new Pole(new Goniec(true));
        //szachownica[7][6] = new Pole(new Skoczek(true));
        szachownica[7][7] = new Pole(new Wieza(true));


    }

    public boolean wykonajRuch(int startX, int startY, int destX, int destY) {
        if (szachownica[startX][startY] != null && szachownica[startX][startY].getFigura() != null) {
            Figura movingFigura = szachownica[startX][startY].getFigura();

            // ruchy na zmiane
            if ((isWhiteTurn && movingFigura.getKolor().equals("biały")) || (!isWhiteTurn && movingFigura.getKolor().equals("czarny"))) {
                // zapobieganie przed ruchem na pozycje tego samego koloru
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
