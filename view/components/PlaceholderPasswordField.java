package view.components;
import  javax.swing.*;
import  java.awt.*;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderPasswordField extends  JPasswordField{
    private String hint;

    public  PlaceholderPasswordField(String hint){

         this.hint = hint;
        setFont(Theme.font(Font.PLAIN, 14));
        setForeground(Theme.TEXT_DARK);
        setBorder(FieldHint.border());
        setOpaque(false);
         addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            repaint();
        }
        @Override
        public void focusLost(FocusEvent e) {
            repaint();
        }
    });

        
    }

        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        boolean empty=getPassword().length==0;
        FieldHint.paint(this, g, hint, empty);
    }

      

    
}