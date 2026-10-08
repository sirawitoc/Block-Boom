package view.components;
import javax.swing.*;
import  java.awt.*;

public class RoundedPanel extends JPanel {
    private  Color background;
    private  int   arc;

    public RoundedPanel(Color backgroumd,int arc){
        this.background=  backgroumd;
        this.arc=arc;
        setOpaque(false);  //ไม่ให้javaวาดพื้นหลังสีเหลี่ยมฉากทับ

    }

    @Override 
    protected  void  paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(background);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
    }
    
}
