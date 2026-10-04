package view.components;


import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/** กำหนดหน้าตา (Style) และวาดข้อความตัวอย่าง(Placeholder) ที่ใช้ร่วมกันในช่องพิมพ์ข้อความ */
final class FieldHint {
    // ป้องกันไม่ให้สร้างวัตถุ (Object) จากคลาสนี้โดยตรง เพราะจะเรียกใช้คำสั่งข้างในได้ทันที
    private FieldHint() {}
    // ฟังก์ชันสำหรับสร้าง (ฺBorder) ของช่องใส่ข้อความ
    static Border border() {
        // สร้างกรอบแบบผสม = กรอบเส้นขอบด้านอก + ขอบว่างด้านใน (ระยะเว้นวรรค)
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.FIELD_BORDER),
                BorderFactory.createEmptyBorder(8, 10, 8, 10));
    }

    static void paint(JTextComponent field, Graphics g, String hint, boolean empty) {
        if (!empty || field.isFocusOwner()) return;
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(new Color(0x9AA8C2));
        g2.setFont(field.getFont());
        FontMetrics fm = g2.getFontMetrics();
        int x = field.getInsets().left;
        int y = (field.getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(hint, x, y);
        g2.dispose();
    }
}
