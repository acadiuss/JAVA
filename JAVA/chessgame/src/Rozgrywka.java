import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rozgrywka {

    private final int SIZE;
    private Pole[][] szachownica;
    private boolean czyRuchBialych;
    private List<Ruch> historiaRuchow;
    private Figura selectedFigura;
    private int selectedRow = -1;
    private int selectedCol = -1;
public boolean result = false;
    public Rozgrywka(int size) {
        this.SIZE = size;
        this.szachownica = new Pole[SIZE][SIZE];
        this.czyRuchBialych = true; // Białe zaczynają grę
        this.historiaRuchow = new ArrayList<>();
        setupInitialBoard(); // Inicjowanie planszy
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
            if (szachownica[startX][startY].getFigura().czyMozliwyRuch(startX, startY, destX, destY, szachownica)) {
                // Correctly move the piece to the new location
                szachownica[destX][destY].setFigura(szachownica[startX][startY].getFigura());
                szachownica[startX][startY].setFigura(null);  // Clear the old location

                // Ensure to toggle the player's turn if needed (commented out here for clarity)
                czyRuchBialych = !czyRuchBialych;

                return true;
            }
        }
        return false;
    }




    // Metoda czyszcząca podświetlenie pól
    private void clearHighlightedFields() {
        // Implementacja czyszczenia podświetlenia pól
    }

    public Pole[][] getSzachownica() {
        return szachownica;
    }

    public List<Ruch> getHistoriaRuchow() {
        return historiaRuchow;
    }
}
