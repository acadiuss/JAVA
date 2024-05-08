class Ruch {
    private int startX;
    private int startY;
    private int destX;
    private int destY;
    private Figura figura;

    public Ruch(int startX, int startY, int destX, int destY, Figura figura) {
        this.startX = startX;
        this.startY = startY;
        this.destX = destX;
        this.destY = destY;
        this.figura = figura;
    }

    // Gettery
    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getDestX() {
        return destX;
    }

    public int getDestY() {
        return destY;
    }

    public Figura getFigura() {
        return figura;
    }

    // Settery
    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setDestX(int destX) {
        this.destX = destX;
    }

    public void setDestY(int destY) {
        this.destY = destY;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
    }
}
