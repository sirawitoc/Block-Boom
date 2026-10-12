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
        super(owner,ModalityType.APPLICATION_MODAL);
        setUndecorated(true);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Theme.PURPLE);
        header.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(Theme.font(Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        header.add(titleLabel, BorderLayout.CENTER);
        header.add(javax.swing.Box.createHorizontalStrut(30), BorderLayout.WEST);
        if (closable) {
            RoundedButton close = new RoundedButton("X", Theme.RED);
            close.setPreferredSize(new Dimension(30, 26));
            close.addActionListener(e -> dispose());
            header.add(close, BorderLayout.EAST);
        } else {
            header.add(javax.swing.Box.createHorizontalStrut(30), BorderLayout.EAST);
        }

        root.setBorder(BorderFactory.createLineBorder(Theme.PURPLE, 2));
        root.add(header, BorderLayout.NORTH);
        setContentPane(root);
    }

    
    protected void setBody(JComponent body) {
        body.setBackground(Color.WHITE);
        root.add(body, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
    }

