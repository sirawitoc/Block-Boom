package view;

import view.components.*;
import model.*;

import javax.swing.*;
import  java.awt.*;

// หน้า Sign up
public class SignUpPanel extends GradientPanel {
    private final PlaceholderField usernameField = new PlaceholderField("   Username"); // ช่องกรอก Username  ที่เป็นข้อความข้างใน เพื่อบอกให้ผู้ใช้รู้ว่าคือช่องอะไร
    private final PlaceholderPasswordField passwordField = new PlaceholderPasswordField("   Password"); // ช่องกรอก  Password  ที่เป็นข้อความข้างใน เพื่อบอกให้ผู้ใช้รู้ว่าคือช่องอะไร และตัวหนังสือจะเป็นตัว จุดๆ
    private final PlaceholderPasswordField confirmField = new PlaceholderPasswordField("   Confirm Password"); // ช่องกรอก  Confirm Password  ที่เป็นข้อความข้างใน เพื่อบอกให้ผู้ใช้รู้ว่าคือช่องอะไร และตัวหนังสือจะเป็นตัว จุดๆ
    private final JLabel errorLabel = new JLabel(" ");  //ข้อความ error สีแดง เป็นช่องว่างไว้ก่อน เพื่อเว้นที่ไว้ เมื่อมัน error
    private final GameFrame frame;

    /* สร้างหน้า SignUp วางโลโก้ กับ ช่องสี่เหลี่ยมไว้ในคอลัมน์เดียว  */
    public SignUpPanel(GameFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(GamePanel.PANEL_W, GamePanel.PANEL_H)); // ขนาดจอเท่ากับหน้า Game  ทุกหน้าจะได้ขนาดเท่ากัน
        setLayout(new GridBagLayout());

        // สร้างคอลัมน์ กล่องที่ใส่โลโก้กับการ์ดซ้อนกัน
        JPanel column = new JPanel();
        column.setOpaque(false);
        column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
        column.setPreferredSize(new Dimension(338, 706));
        column.add(new LogoLabel());
        column.add(Box.createVerticalStrut(10));
        column.add(buildCard());
        
         // ตั้งค่าการวางคอลัมน์
        GridBagConstraints gc = new GridBagConstraints();
         gc.weightx = 1;
        gc.weighty = 1;
         gc.anchor = GridBagConstraints.NORTH;
        gc.insets = new Insets(20, 0, 0, 0);
        add(column, gc);
    }

    /** สร้างการ์ดสี่เหลี่ยม ที่ใส่ฟอร์มทั้งหมด โดยมี หัวข้อ, ช่องกรอก, ข้อความ error, ปุ่ม และ footer */
    private JPanel buildCard() {
        RoundedPanel card = new RoundedPanel(new Color(255, 255, 255, 200), 18);    // การ์ดสี่เหลี่ยม ให้มีสีขาวโปร่งๆ  มุมโค้ง 18
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));   //  เว้นขอบด้านในรอบสี่เหลี่ยม 16 px
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        card.setAlignmentX(Component.CENTER_ALIGNMENT); //ทำให้การ์ดสีเหลี่ยมอยู่กึ่งกลางคอลัมน์

        JLabel title = new JLabel("Sign up");
        title.setFont(Theme.font(Font.BOLD, 25));
        JLabel subtitle = new JLabel("Create your account");
        subtitle.setFont(Theme.font(Font.PLAIN, 18));
        subtitle.setForeground(Theme.TEXT_MUTED);

        errorLabel.setForeground(Theme.RED);
        errorLabel.setFont(Theme.font(Font.PLAIN, 12));

        RoundedButton createButton = new RoundedButton("Create Account", Theme.GREEN);
        createButton.setPreferredSize(new Dimension(200, 58));
        createButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 58));
        createButton.addActionListener(e -> doRegister());
        confirmField.addActionListener(e -> doRegister());

        card.add(title);
        card.add(subtitle);
        card.add(Box.createVerticalStrut(12));
        card.add(usernameField);
        card.add(Box.createVerticalStrut(8));
        card.add(passwordField);
        card.add(Box.createVerticalStrut(8));
        card.add(confirmField);
        card.add(Box.createVerticalStrut(6));
        card.add(errorLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(createButton);
        card.add(Box.createVerticalGlue());
        card.add(buildFooter());
        Layouts.alignLeft(card);
        return card;
    }

    // สร้างข้อความแถวด้านล่างของการ์ด
    private JPanel buildFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        footer.setOpaque(false);
        footer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel text = new JLabel("Already have an account?");
        text.setFont(Theme.font(Font.PLAIN, 15));
        text.setForeground(Theme.FIELD_TEXT);
        LinkButton login = new LinkButton("Login");
        login.addActionListener(e -> frame.showLogin());
        footer.add(text);
        footer.add(login);
        return footer;
    }

    // ตรวจสอบการ Register
    private void doRegister() {
        String error = AuthService.register(usernameField.getText(),
                new String(passwordField.getPassword()), new String(confirmField.getPassword()));
        if (error != null) {
            errorLabel.setText(error);
            return;
        }
        JOptionPane.showMessageDialog(this, "Account created! Please log in.");
        frame.showLogin();
    }

    // ล้างฟอร์ม
    public void reset() {
        usernameField.setText("");
        passwordField.setText("");
        confirmField.setText("");
        errorLabel.setText(" ");
    }
}
