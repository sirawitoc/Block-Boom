package view;

import view.components.Theme;
import view.components.RoundedButton;

import javax.swing.*;
import java.awt.*;

/**
 * PopupDialog.java
 *
 * คลาสแม่  สำหรับ popup ทุกอันในเกม
 * หน้าตาเหมือนกันหมด คือ แถบหัวสีม่วง + ปุ่ม X (เลือกได้ว่าจะมีหรือไม่) + เนื้อหาพื้นขาว
 * คลาสลูก (เช่น GameOverDialog) แค่สร้างเนื้อหาแล้วส่งให้ setBody()
 */
abstract class PopupDialog  extends JDialog{
    private final JPanel root = new JPanel(new BorderLayout()); // กล่องใหญ่ของ dialog บนคือแถบหัว ตรงกลางคือเนื้อหา

    PopupDialog(Window owner, String title , boolean closable){
        super(owner,ModalityType.APPLICATION_MODAL);    // modal = popup เปิดอยู่ จะไปกดหน้าต่างอื่นไม่ได้
        setUndecorated(true); // เอากรอบหน้าต่างของระบบออก

        // แถบหัวสีม่วง
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Theme.PURPLE);
        header.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6)); // เว้นขอบ

        // ชื่อ popup อยู่ตรงกลาง สีขาว ตัวหนา
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(Theme.font(Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        header.add(titleLabel, BorderLayout.CENTER);
        header.add(javax.swing.Box.createHorizontalStrut(30), BorderLayout.WEST); // ใส่ช่องว่างฝั่งซ้าย 30 px ให้เท่าฝั่งขวา ข้อความจะได้อยู่กลาง
        if (closable) {
            // ปิดเองได้ -> ใส่ปุ่ม X สีแดงฝั่งขวา กดแล้วปิด
            RoundedButton close = new RoundedButton("X", Theme.RED);
            close.setPreferredSize(new Dimension(30, 26));
            close.addActionListener(e -> dispose());
            header.add(close, BorderLayout.EAST);
        } else {
            // ปิดเองไม่ได้ -> ใส่ช่องว่างแทนปุ่ม X (กว้างเท่ากัน ข้อความจะไม่ขยับ)
            header.add(javax.swing.Box.createHorizontalStrut(30), BorderLayout.EAST);
        }

        // ขอบรอบ dialog 
        root.setBorder(BorderFactory.createLineBorder(Theme.PURPLE, 2));
        root.add(header, BorderLayout.NORTH);   // แถบหัวอยู่บนสุด
        setContentPane(root);   // เอา root เป็นหน้าตาหลักของ dialog
    }

    // ใส่เนื้อหาพื้นขาว แล้วปรับขนาดกับตำแหน่ง
    // คลาสลูกต้องเรียกอันนี้ตอนท้าย constructor ด้วย ไม่งั้นไม่มีเนื้อหา
    protected void setBody(JComponent body) {
        body.setBackground(Color.WHITE);
        root.add(body, BorderLayout.CENTER);// วางไว้ใต้แถบหัว
        pack();  // ย่อ/ขยาย dialog ให้พอดีกับเนื้อหา
        setLocationRelativeTo(getOwner());   // วางไว้กลางหน้าต่างเกม
    }
    }

