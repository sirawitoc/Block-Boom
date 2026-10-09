package view;

import javax.swing.*;
import java.awt.*;

import view.components.*;

public class HomePanel extends GradientPanel {
    private PlaceholderField nameField = new PlaceholderField("Enter your name");
    private JLabel errorLabel = new JLabel(" ");
    private GameFrame frame;

    public HomePanel(GameFrame frame) {
        this.frame = frame;

        setPreferredSize(new Dimension(GamePanel.PANEL_W, GamePanel.PANEL_H));
        setLayout(new GridBagLayout());

    }

    private  RoundedButton menuButton(String text,Color color){
         RoundedButton b = new RoundedButton(text, color);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        return b;
    }

    public void refresh() {
        nameField.setText(frame.getSession().getPlayerName());
        errorLabel.setText(" ");
    }
}
