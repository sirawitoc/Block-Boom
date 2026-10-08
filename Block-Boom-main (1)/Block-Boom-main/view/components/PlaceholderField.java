package view.components;

import javax.swing.*;
import java.awt.*;

public class PlaceholderField extends JTextField {
    private String hint;

    public PlaceholderField(String hint) {
        this.hint = hint;
        setFont(Theme.font(Font.PLAIN, 14));
        setForeground(Theme.TEXT_DARK);
        setBorder(FieldHint.border());
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        boolean empty=getText().isEmpty();
        FieldHint.paint(this, g, hint, empty);
    }
}