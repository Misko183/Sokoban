import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    private static final long serialVersionUID = 1L;

    public static final int OFFSET = 56;

    public Main() {
        initUI();
    }
    public void initUI() {
        Panel board = new Panel();
        add(board, BorderLayout.CENTER);
        setSize(board.getBoardWidth() + OFFSET, board.getBoardHeight() + 2 * OFFSET);
        setTitle("Sakoban");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        Main game = new Main();
        game.setVisible(true);
    }
}
