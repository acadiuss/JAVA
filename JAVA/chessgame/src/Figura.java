import javax.swing.ImageIcon;
public abstract class Figura {
    protected String kolor;
    protected int waga;
    protected ImageIcon imageIcon;

    public Figura(String kolor, ImageIcon imageIcon, int waga) {
        this.kolor = kolor;
        this.imageIcon = imageIcon;
        this.waga = waga;
    }

    public abstract boolean czyMozliwyRuch(int startX, int startY, int destX, int destY, Pole[][] szachownica);

    public ImageIcon getIcon() {
        return imageIcon;
    }

    public String getKolor() {
        return kolor;
    }

    public int getWaga() {
        return waga;
    }

}