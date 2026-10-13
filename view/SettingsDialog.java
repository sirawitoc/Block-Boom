package view;

import view.components.RoundedButton;
import view.components.Theme;

import javax.swing.*;
import java.awt.*;

/**
 * SettingsDialog.java
 *
 * popup หน้าตั้งค่า ตอนนี้มีปุ่มเดียวคือ Home (กลับหน้าแรก)
 * หน้าตากรอบกับแถบหัวสืบทอดมาจาก PopupDialog
 */
public class SettingsDialog extends PopupDialog {
    // frame  = หน้าต่างหลักของเกม (ไว้ใช้ตอนกด Home)
    // parent = component ที่เรียก dialog นี้ (ไว้หาตำแหน่งให้ popup อยู่กลางหน้าต่าง)
    public SettingsDialog(GameFrame frame, Component parent) {
        super(SwingUtilities.getWindowAncestor(parent), "Setting", true); // ส่งให้คลาสแม่สร้างแถบหัว "Setting" (true = มีปุ่ม X ให้ปิดเองได้)

         // ปุ่ม Home สีแดง
        RoundedButton homeBtn = new RoundedButton("Home", Theme.RED);
        homeBtn.setPreferredSize(new Dimension(180, 42));
        homeBtn.addActionListener(e -> {    // กดแล้วปิด popup ก่อน แล้วกลับไปหน้าเมนูหลัก
            dispose();
            frame.goHome();
        });

        // กล่องเนื้อหาจัดปุ่มให้อยู่กลาง
        JPanel body = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        body.setBorder(BorderFactory.createEmptyBorder(24, 40, 24, 40)); // เว้นขอบ   
        body.add(homeBtn);
        setBody(body); // // ส่งเนื้อหาให้คลาสแม่ใส่ใน popup + ปรับขนาด + จัดกลาง
    }
}
