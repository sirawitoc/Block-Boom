package view.components;

import javax.swing.*;
import java.awt.FlowLayout;

public class TestComponent {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test RoundedButton");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 200);
        frame.add(new RoundedButton("เริ่มเกม", Theme.PURPLE));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}