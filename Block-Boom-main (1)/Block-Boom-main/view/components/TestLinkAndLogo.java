package view.components;

import javax.swing.*;
import java.awt.FlowLayout;

public class TestLinkAndLogo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 300);
        frame.add(new LogoLabel());
        frame.add(new LinkButton("ยังไม่มีบัญชี? สมัครที่นี่"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}