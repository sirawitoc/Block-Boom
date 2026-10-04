package view.components;
import  javax.swing.*;
import  java.awt.*;

public class LinkButton extends JButton {

    public  LinkButton(String text){
     super(text);
     setContentAreaFilled(false);
     setBorderPainted(false);
     setFocusPainted(false);
     setForeground(Theme.PURPLE);
     setFont(Theme.font(Font.PLAIN, 13));
     setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
}
