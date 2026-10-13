package view;

import view.components.*;

import javax.swing.*;
import java.awt.*;

/**
 * GameOverDialog.java
 *
 * popup ที่เด้งตอนเกมจบ โชว์ชื่อผู้เล่นกับคะแนน
 * หน้าตากรอบกับแถบหัวสืบทอดมาจาก PopupDialog อยู่แล้ว
 */
public class GameOverDialog extends PopupDialog {
    // frame     = หน้าต่างหลักของเกม (ไว้ใช้ตอนกด Home)
    // parent    = component ที่เรียก dialog นี้ (ไว้หาตำแหน่งให้ popup อยู่กลางหน้าต่าง)
    // player    = ชื่อผู้เล่น
    // score     = คะแนนตอนเกมจบ
    // onPlayAgain = โค้ดที่จะให้ทำตอนกด Play Again (เช่น เริ่มเกมใหม่)
public GameOverDialog(GameFrame frame, Component parent, String player, int score, Runnable onPlayAgain) {
        super(SwingUtilities.getWindowAncestor(parent), "Game Over", false); // ส่งไปให้คลาสแม่สร้างแถบหัว "Game Over" (false = ไม่มีปุ่ม X ต้องกดปุ่มข้างในเท่านั้น)

         // ข้อความชื่อผู้เล่น อยู่ตรงกลาง ตัวหนา
        JLabel playerLabel = new JLabel(player);
        playerLabel.setFont(Theme.font(Font.BOLD, 16));
        playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ข้อความคะแนน สีม่วง อยู่ตรงกลาง
        JLabel scoreLabel = new JLabel("Score : " + score);
        scoreLabel.setFont(Theme.font(Font.BOLD, 16));
        scoreLabel.setForeground(Theme.PURPLE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

         // ปุ่ม Play Again สีเขียว: กดแล้วปิด popup ก่อน แล้วค่อยรันโค้ดเริ่มเกมใหม่
        RoundedButton againBtn = button("Play Again", Theme.GREEN);
        againBtn.addActionListener(e -> {
            dispose();
            onPlayAgain.run();
        });
        
        // ปุ่ม Home สีแดง: กดแล้วปิด popup แล้วกลับไปหน้าเมนูหลัก
        RoundedButton homeBtn = button("Home", Theme.RED);
        homeBtn.addActionListener(e -> {
            dispose();
            frame.goHome();
        });

        // กล่องเนื้อหา เรียงจากบนลงล่าง
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(BorderFactory.createEmptyBorder(18, 40, 20, 40)); // เว้นขอบ 

        body.add(playerLabel);
        body.add(Box.createVerticalStrut(6)); //ช่องว่างให้ไม่ติดกัน
        body.add(scoreLabel);
        body.add(Box.createVerticalStrut(16));
        body.add(againBtn);
        body.add(Box.createVerticalStrut(10));
        body.add(homeBtn);
        setBody(body); // ส่งเนื้อหาให้คลาสแม่ใส่ใน popup + ปรับขนาด + จัดกลาง

}       
     // ฟังก์ชันช่วยสร้างปุ่ม จะได้ไม่ต้องเขียนตั้งขนาดซ้ำสองรอบ
    private RoundedButton button(String text, Color color) {
        RoundedButton b = new RoundedButton(text, color);
        b.setPreferredSize(new Dimension(200, 40));
        b.setMaximumSize(new Dimension(200, 40));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        return b;
    }
}