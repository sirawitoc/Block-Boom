package view.components;
import  javax.swing.*;
import  java.awt.*;

/**
 * คลาสสำหรับสร้างองค์ประกอบภาพ "โลโก้ข้อความแบบมีเส้นขอบ"
 * ทำหน้าที่วาดตัวหนังสือชื่อเกม
 */
public class LogoLabel extends JComponent {
    // ฟังก์ชันสร้างโลโก
    public  LogoLabel(){
        // กำหนดขนาดพื้นที่สำหรับแสดงผลโลโก้
        setPreferredSize(new Dimension(300,80));
    }

    // ฟังก์ชันที่ระบบจะเรียกใช้เพื่อ "วาดกราฟิก" ลงบนหน้าจอ
    @Override 
    protected  void paintComponent(Graphics g){
     Graphics2D g2=(Graphics2D) g;
     // เปิดโหมดลบรอยหยัก (Anti - aliasing)
     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
     // กำหนดฟอนต์ตัวหนาและขนดฟอนต์
     g2.setFont(Theme.font(Font.BOLD, 36));
    // ดึงข้อมูลขนาดของฟอนต์ เพื่อนำมาช้คำนวณตำแหน่งกึ่งกลาง
    FontMetrics fm=g2.getFontMetrics();
    // ข้อความโลโก้ที่ต้องแสดงผล
    String text="Block Boom";
    // คำนวณพิกัด x (แนวนอน) เพื่อวางข้อความให้อยู่ตรงกลางพื้นที่
    int x=(getWidth()-fm.stringWidth(text))/2;
    // คำนวณพิกัด y (แนวตั้ง) เพื่อวางข้อความให้อยู่ตรงกลางพื้นที่
    int y=(getHeight()+fm.getAscent())/2;
    // เรียกฟังก์ชันวาดข้อความพร้อมใส่เส้นขอบ โดยใช้ขอบสีน้ำเงินเข้มและตัวอักษรสีเหลือง
    drawOutlined(g2,text,x,y,Theme.NAVY,Theme.YELLOW);
    }

    // ~ หมายถึง package-private ตาม diagram
    void drawOutlined(Graphics2D g2,String text,int x,int y,Color outline,Color fill){
        // กำหนดสีสำหรับทำเส้นขอบ
        g2.setColor(outline);
        // วาดข้อความเดิมซ้ำ 4 ครั้ง โดยขยับตำแหน่งไป ซ้าย,ขวา,บน,ล่าง อย่างละ 1 พิกเซล เพื่อจำลองการทำเส้นขอบ
        g2.drawString(text, x - 1, y);
        g2.drawString(text, x + 1, y);
        g2.drawString(text, x, y - 1);
        g2.drawString(text, x, y + 1);
        // กำหนดสีหลักของตัวอักษร
        g2.setColor(fill);
        // วาดข้อความทับลงไปตรงกลาง
        g2.drawString(text, x, y);
    }
}