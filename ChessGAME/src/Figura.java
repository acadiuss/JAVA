import javax.swing.*;

public abstract class Figura {
    protected String kolor;
    protected int waga;
    protected ImageIcon imageIcon;




    public Figura(String kolor, ImageIcon imageIcon, int waga) {
        this.kolor = kolor;
        this.imageIcon = imageIcon;
    }

    public abstract boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica);

    public ImageIcon getIcon() {
        return imageIcon;
    }

    public String getKolor() {
        return kolor;
    }

    public boolean isKrol() {
        return false;
    }
    /*public boolean ifSzach() {
        if(czyMozliwyRuch(/*dla  figur pion wierza hetman skoczek goneic któóre sa aktualnie na planszy public boolean wykonajRuch(int startX, int startY, int destX, int destY) {
        if (szachownica[startX][startY] != null && szachownica[startX][startY].getFigura() != null) {
            Figura movingFigura = szachownica[startX][startY].getFigura(); dest == pozycja krola szachownica[destX][destY].getFigura().isKrol()) i jest mozliwa jak funkcja zwruci true to return true "szach"))
        return false;
    }*/
}