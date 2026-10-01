package view.components;

import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Container;

/**
 * Layouts.java
 *
 * ตัวช่วยสำหรับการจัดวางหน้าจอ คำสั่ง alignLeft() เข้ามาช่วยปรับให้ทุกชิ้นส่วน
 * ขยับมาชิดซ้ายตรงกันทั้งหมด
 */
public final class Layouts {
    private Layouts() { }

    public static void alignLeft(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JComponent) ((JComponent) c).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
    }
}
