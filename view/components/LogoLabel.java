package view.components;
import  javax.swing.*;
import  java.awt.*;

public class LogoLabel extends JComponent {
    public  LogoLabel(){
        setPreferredSize(new Dimension(300,80));
    }

    @Override 
    protected  void paintComponent(Graphics g){
     Graphics2D g2=(Graphics2D) g;
     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
     g2.setFont(Theme.font(Font.BOLD, 36));

    FontMetrics fm=g2.getFontMetrics();
    String text="Block Boom";
    int x=(getWidth()-fm.stringWidth(text))/2;
    int y=(getHeight()+fm.getAscent())/2;

    drawOutlined(g2,text,x,y,Theme.NAVY,Theme.YELLOW);
    }

    // ~ หมายถึง package-private ตาม diagram
    void drawOutlined(Graphics2D g2,String text,int x,int y,Color outline,Color fill){
        g2.setColor(outline);
        g2.drawString(text, x - 1, y);
        g2.drawString(text, x + 1, y);
        g2.drawString(text, x, y - 1);
        g2.drawString(text, x, y + 1);

        g2.setColor(fill);
        g2.drawString(text, x, y);
    }
}
