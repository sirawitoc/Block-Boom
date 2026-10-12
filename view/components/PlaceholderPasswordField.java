package view.components;

import  javax.swing.*;
import  java.awt.*;


public class PlaceholderPasswordField extends  JPasswordField{
    private String hint;

    public  PlaceholderPasswordField(String hint){

         this.hint = hint;
        setFont(Theme.font(Font.BOLD, 15));
        setForeground(Theme.FIELD_TEXT);
        setBorder(FieldHint.border());
        setPreferredSize(new Dimension(200, 50));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
         
        
    }

        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        boolean empty=getPassword().length==0;
        FieldHint.paint(this, g, hint, empty);
    }

      

    
}