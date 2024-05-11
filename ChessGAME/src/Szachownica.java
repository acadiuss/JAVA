import javax.swing.*;
import java.awt.*;

public class Szachownica extends JFrame {
    private static final int SIZE = 8;
    private static final int WINDOW_SIZE = 800;
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private Rozgrywka rozgrywka;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public Szachownica() {
        this.rozgrywka = new Rozgrywka(SIZE);
        setTitle("SZACHY");
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel chessBoard = new JPanel(new GridLayout(SIZE, SIZE));
        initializeBoard(chessBoard);
        add(chessBoard, BorderLayout.CENTER);
    }

    private void initializeBoard(JPanel chessBoard) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JButton button = new JButton();
                int finalRow = row;
                int finalCol = col;
                button.addActionListener(e -> handleButtonClick(finalRow, finalCol));
                button.setBackground((row + col) % 2 == 0 ? Color.darkGray : Color.WHITE);
                buttons[row][col] = button;
                chessBoard.add(button);
            }
        }
        updateBoard();
    }

    private void handleButtonClick(int row, int col) {
        if (selectedRow != -1 && selectedCol != -1) {
            if (rozgrywka.wykonajRuch(selectedRow, selectedCol, row, col)) {
                selectedRow = -1;
                selectedCol = -1;
                updateBoard();
            } else {                    // nieprawidlowy ruchh
                selectedRow = -1;
                selectedCol = -1;
            }
        } else {
            // brak wyboru
            selectedRow = row;
            selectedCol = col;
        }
    }

    public void updateBoard() {
        Pole[][] szachownica = rozgrywka.getSzachownica();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
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
