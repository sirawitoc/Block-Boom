import view.GameFrame;

import javax.swing.SwingUtilities;

public class BlockBoom {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }
}