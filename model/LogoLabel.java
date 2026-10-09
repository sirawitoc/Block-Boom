package view.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * คลาสสำหรับแสดง "โลโก้เกม" จากไฟล์รูปภาพ PNG
 */
public class LogoLabel extends JComponent {
    
    // กำหนดที่อยู่ของไฟล์รูปภาพโลโก้ โฟลเดอร์ img ไฟล์ชื่อ img.png
    private static final String Logo_Game = "img/img.png";

    // ตัวแปรเก็บรูปภาพโลโก้
    private Image logo;

    // ฟังก์ชันสร้างโลโก้
    public LogoLabel() {
        // กำหนดขนาดพื้นที่สำหรับแสดงผลโลโก้
        setPreferredSize(new Dimension(300, 80));

        try {
            // โหลดรูปจากไฟล์ในเครื่องโดยตรง
            logo = ImageIO.read(new File(Logo_Game));
        } catch (IOException e) {
            System.err.println("โหลดโลโก้ไม่สำเร็จ: " + Logo_Game);
            System.err.println("กำลังหาจาก: " + new File(Logo_Game).getAbsolutePath());
        }
    }

    // ฟังก์ชันที่ระบบจะเรียกใช้เพื่อ "วาดกราฟิก" ลงบนหน้าจอ
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (logo != null) {
            Graphics2D g2 = (Graphics2D) g;
            // เปิดโหมดลบรอยหยักเพื่อความคมชัด
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // คำนวณตำแหน่งกึ่งกลางตามขนาดรูป
            int x = (getWidth() - logo.getWidth(null)) / 2;
            int y = (getHeight() - logo.getHeight(null)) / 2;

            // วาดรูปภาพตรงกลางพื้นที่
            g2.drawImage(logo, x, y, this);
        }
    }
}