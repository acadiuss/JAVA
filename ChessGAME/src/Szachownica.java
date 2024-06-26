import javax.swing.*;
import java.awt.*;

public class Szachownica extends JFrame {

    private static final int WINDOW_SIZE = 800;
    private JButton[][] buttons = new JButton[8][8];

    private Rozgrywka rozgrywka;
    private int startX = -1;
    private int startY = -1;

    public Szachownica() {
        this.rozgrywka = new Rozgrywka();
        setTitle("SZACHY LOTNIKÓW");
        ImageIcon icon = new ImageIcon("ico3.ico");
        setIconImage(icon.getImage());
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel chessBoard = new JPanel(new GridLayout(8, 8));
        initializeBoard(chessBoard);
        add(chessBoard, BorderLayout.CENTER);

    }
    private void initializeBoard(JPanel chessBoard) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                int finalRow = row;
                int finalCol = col;
                button.addActionListener(e -> handleButtonClick(finalRow, finalCol));
                button.setBackground((row + col) % 2 == 0 ? new Color(	221, 12, 57): Color.WHITE);
                buttons[row][col] = button;
                chessBoard.add(button);
            }
        }
        updateBoard();
    }
    private void handleButtonClick(int row, int col) {
        if (startX != -1 && startY != -1) {
            if (rozgrywka.makeAmove(startX, startY, row, col)) {
                updateBoard();
            }
            startX = -1;
            startY = -1;
        } else {
            startX = row;
            startY = col;
        }
    }
    public void updateBoard() {
        Pole[][] szachownica = rozgrywka.getSzachownica();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figura figura = szachownica[row][col].getFigura();
                if (figura != null) {
                    buttons[row][col].setIcon(figura.getIcon());
                } else {
                    buttons[row][col].setIcon(null);
                }
            }
        }
        revalidate();
        repaint();
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Szachownica chessBoard = new Szachownica();
            chessBoard.setVisible(true);
            chessBoard.setResizable(false);
        });
    }
}
