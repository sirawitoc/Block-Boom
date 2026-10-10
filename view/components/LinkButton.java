package view.components;
import  javax.swing.*;
import  java.awt.*;

/**
 * คลาสสำหรับสร้าง ปุ่มสไตล์ลิงก์
 * โดยเอาปุ่มกดธรรมดามาตกแต่งให้มีลักษณะเหมือนลิงก์
 */
public class LinkButton extends JButton {

    // ฟังก์ชันสร้างปุ่ม โดยรับข้อความ (text) ที่ต้องการให้แสดงบนปุ่ม
    public  LinkButton(String text){
     // ส่งข้อความไปตั้งเป็นชื่อปุ่มกดพื้นฐาน
     super(text);
     // ซ่อนพื้นหลังของปุ่ม
     setContentAreaFilled(false);
     // ซ่อนเส้นขอบของปุ่ม
     setBorderPainted(false);
     // ซ่อนเส้นกรอบโฟกัส ทำให้ไม่มีเส้นขอบเวลาเอาเมาส์ไปคลิก
     setFocusPainted(false);
     // กำหนดสีตัวหนังสือให้เป็นสีม่วง
     setForeground(Theme.PURPLE);
     // กำหนดรูปแบบและขนาดฟอนต์
     setFont(Theme.font(Font.PLAIN, 20));
     // เปลี่ยนรูปตัวชี้เมาส์ (Cursor) ให้เป็นรูป มือชี้ เมื่อเลื่อนเมาส์มาวางบนปุ่ม
     setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
}