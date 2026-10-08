package view.components;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class TestPlaceholderField {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 150);

        PlaceholderPasswordField field = new PlaceholderPasswordField("กรอกรหัสผ่าน");
        field.setPreferredSize(new Dimension(200, 36));
        frame.add(field);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}