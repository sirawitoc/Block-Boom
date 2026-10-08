package view.components;
import  javax.swing.*;
import  java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton  extends  JButton{
    private  static  final int DEPTH=4;
    private  Color   base;
    private  boolean hover  =false;

    public RoundedButton (String text,Color base){
      super(text);
      this.base=base;
      setContentAreaFilled(false);
      setFocusPainted(false);
      setBorderPainted(false);
      setForeground(Color.WHITE);
      setFont(Theme.font(Font.BOLD,16));


      addMouseListener(new MouseAdapter() {
        @Override 
        public void  mouseEntered(MouseEvent e){
            hover=true;
            repaint();
        }

        @Override 
        public void mouseExited(MouseEvent e){
                  hover=false;
                  repaint();
        }
      });

    }
    @Override 
    protected  void  paintComponent(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int w=getWidth();
        int h=getHeight();

        //วาดเงาด้านล่างก่อน
        g2.setColor(base.darker());
        g2.fillRoundRect(0, DEPTH, w, h- DEPTH, 20,20);

        //วาดปุ่มทับด้านบน(จะสว่างขึ้นถ้าmouse อยู่)

        g2.setColor(hover ?base.brighter(): base);
        g2.fillRoundRect(0, 0, w, h-DEPTH, 20, 20);

        super.paintComponent(g);
    }
    
}
