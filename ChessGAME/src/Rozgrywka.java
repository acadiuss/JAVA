import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rozgrywka {

    private final int SIZE;
    private Pole[][] szachownica;
    private List<Ruch> historiaRuchow;
    private boolean isWhiteTurn = true;

    public Rozgrywka(int size) {
        this.SIZE = size;
        this.szachownica = new Pole[SIZE][SIZE];
        setupInitialBoard();

    }


    private void setupInitialBoard() {

        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < SIZE; col++) {
                szachownica[row][col] = new Pole(null);
            }
        }
        szachownica[0][0] = new Pole(new Wieza(false));
        szachownica[0][1] = new Pole(new Skoczek(false));
        szachownica[0][2] = new Pole(new Goniec(false));
        szachownica[0][3] = new Pole(new Hetman(false));
        szachownica[0][4] = new Pole(new Krol(false));
        szachownica[0][5] = new Pole(new Goniec(false));
        szachownica[0][6] = new Pole(new Skoczek(false));
        szachownica[0][7] = new Pole(new Wieza(false));
        for (int i = 0; i < 8; i++) {
            szachownica[1][i] = new Pole(new Pion(false));
            szachownica[6][i] = new Pole(new Pion(true));
        }
        szachownica[7][0] = new Pole(new Wieza(true));
        szachownica[7][1] = new Pole(new Skoczek(true));
        szachownica[7][2] = new Pole(new Goniec(true));
        szachownica[7][3] = new Pole(new Hetman(true));
        szachownica[7][4] = new Pole(new Krol(true));
        szachownica[7][5] = new Pole(new Goniec(true));
        szachownica[7][6] = new Pole(new Skoczek(true));
        szachownica[7][7] = new Pole(new Wieza(true));


    }


    public boolean wykonajRuch(int startX, int startY, int destX, int destY) {
        if (szachownica[startX][startY] != null && szachownica[startX][startY].getFigura() != null) {
            Figura movingFigura = szachownica[startX][startY].getFigura();

            // Sprawdzenie, czy ruch wykonywany jest przez właściwego gracza
            if ((isWhiteTurn && movingFigura.getKolor().equals("biały")) || (!isWhiteTurn && movingFigura.getKolor().equals("czarny"))) {
                // Sprawdzanie, czy cel nie jest zajęty przez figurę tego samego koloru
                if (szachownica[destX][destY].getFigura() != null && szachownica[destX][destY].getFigura().getKolor().equals(movingFigura.getKolor())) {
                    return false; // Nie pozwól na bicie figur tego samego koloru
                }
                if (movingFigura.czyMozliwyRuch(startX, startY, destX, destY, szachownica)) {
                    // Przenoszenie figury
                    szachownica[destX][destY].setFigura(movingFigura);
                    szachownica[startX][startY].setFigura(null);

                    // Zmiana tury
                    isWhiteTurn = !isWhiteTurn;

                    // Promocja piona, jeśli dotyczy
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
