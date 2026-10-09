package view;

import data.*;
import model.*;
import view.components.*;


import javax.swing.*;
import java.awt.*;

/**
 * LoginPanel
 *
 * หน้าจอ Login: ให้กรอก Username กับ Password 
 * เมื่อรหัสถูกก็จะไปหน้า Home ถ้าผิด จะแสดงข้อความ error สีแดง
 * 
 */
public class LoginPanel extends GradientPanel{
    private  final PlaceholderField idField = new PlaceholderField("   Username"); // ช่องกรอก Username  ที่เป็นข้อความข้างใน เพื่อบอกให้ผู้ใช้รู้ว่าคือช่องอะไร
    private final PlaceholderPasswordField passwordField = new PlaceholderPasswordField("    Password"); // ช่องกรอก  Password  ที่เป็นข้อความข้างใน เพื่อบอกให้ผู้ใช้รู้ว่าคือช่องอะไร และตัวหนังสือจะเป็นตัว จุดๆ
    private final JLabel errorLabel = new JLabel(" "); //ข้อความ error สีแดง เป็นช่องว่างไว้ก่อน เพื่อเว้นที่ไว้ เมื่อมัน error
    private final GameFrame frame;

    /* สร้างหน้า Login วางโลโก้ กับ ช่องสี่เหลี่ยมไว้ในคอลัมน์เดียว  */
    public  LoginPanel(GameFrame frame){
        this.frame = frame;
        setPreferredSize(new Dimension(GamePanel.PANEL_W, GamePanel.PANEL_H)); // ขนาดจอเท่ากับหน้า Game  ทุกหน้าจะได้ขนาดเท่ากัน
        setLayout(new GridBagLayout());

        //คอลัมน์ กล่องแนวตั้งที่ซ้อนโลโก้ไว้ด้านบน และ ช่องสี่เหลี่ยมไว้ด้านล่าง
         JPanel column = new JPanel();
        column.setOpaque(false);
        column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
        column.setPreferredSize(new Dimension(338, 706));
        column.add(new LogoLabel());
        column.add(buildCard());

         // ตั้งค่าการวางคอลัมน์
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 1; //ขยายเต็มหน้าจอ
        gc.weighty = 1; //ขยายเต็มหน้าจอ
        gc.anchor = GridBagConstraints.NORTH; //ยึดคอลัมน์ไว้ ด้านบนกึ่งกลาง
        gc.insets = new Insets(20, 0, 0, 0); // เว้นระยะจากขอบบน
        add(column, gc);
    }

    /** สร้างช่องสี่เหลี่ยม ที่ใส่ฟอร์มทั้งหมด โดยมี หัวข้อ, ช่องกรอก, ข้อความ error, ปุ่ม และ footer */
    private  JPanel buildCard(){
        RoundedPanel card = new RoundedPanel(new Color(255, 255, 255, 200), 18);  // ช่องสี่เหลี่ยม ให้มีสีขาวโปร่งๆ  มุมโค้ง 18
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16)); //  เว้นขอบด้านในรอบสี่เหลี่ยม 16 px
        card.setMaximumSize(new Dimension(338, Integer.MAX_VALUE)); // กว้างไม่เกิน 338 แต่สามารถขยายให้สูงได้
        card.setAlignmentX(Component.CENTER_ALIGNMENT); //ทำให้ช่องสีเหลี่ยมอยู่กึ่งกลางคอลัมน์
        

        // หัวข้อ 
        JLabel title = new JLabel("Login");
        title.setFont(Theme.font(Font.BOLD, 25)); // ขนาด และ font
        JLabel subtitle = new JLabel("Welcome back!");
        subtitle.setFont(Theme.font(Font.PLAIN, 18)); // ขนาด และ font 
        subtitle.setForeground(Theme.TEXT_MUTED);
        
        // ข้อความ error
        errorLabel.setForeground(Theme.RED); // สีแดง
        errorLabel.setFont(Theme.font(Font.PLAIN, 12)); // ขนาด และ font
        
        // ปุ่ม Login
        RoundedButton loginButton = new RoundedButton("Login", Theme.BLUE);
        loginButton.setPreferredSize(new Dimension(200, 58));
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 58)); // // ตั้งความสูงไม่ให้ยึดเอง
        loginButton.addActionListener(e -> doLogin());
        passwordField.addActionListener(e -> doLogin());
 
        RoundedButton leaveButton = new RoundedButton("Leave", Theme.RED);
        leaveButton.setPreferredSize(new Dimension(200, 58));
        leaveButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 58));
        leaveButton.addActionListener(e -> System.exit(0));
 
        card.add(title);
        card.add(subtitle);
        card.add(Box.createVerticalStrut(12));
        card.add(idField);
        card.add(Box.createVerticalStrut(16));
        card.add(passwordField);
        card.add(Box.createVerticalStrut(6));
        card.add(errorLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(loginButton);
        card.add(Box.createVerticalStrut(12));
        card.add(leaveButton);
        card.add(Box.createVerticalGlue());
        card.add(buildFooter());
        Layouts.alignLeft(card);
        return card;
    }
    private JPanel buildFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        footer.setOpaque(false);
        footer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel text = new JLabel("Don't have an account?");
        text.setFont(Theme.font(Font.PLAIN, 15));
        text.setForeground(Theme.FIELD_TEXT);
        LinkButton signUp = new LinkButton("Sign up");
        signUp.addActionListener(e -> frame.showSignUp());
        footer.add(text);
        footer.add(signUp);
        return footer;
    }
    
    private void doLogin() {
        User user = AuthService.login(idField.getText(), new String(passwordField.getPassword()));
        if (user == null) {
            errorLabel.setText("Invalid username or password.");
            return;
        }
        frame.getSession().login(user);
        frame.goHome();
    }
     public void reset() {
        idField.setText("");
        passwordField.setText("");
        errorLabel.setText(" ");
     }
}
