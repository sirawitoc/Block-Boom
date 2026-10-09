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

    /** สร้างการ์ดสี่เหลี่ยม ที่ใส่ฟอร์มทั้งหมด โดยมี หัวข้อ, ช่องกรอก, ข้อความ error, ปุ่ม และ footer */
    private  JPanel buildCard(){
        RoundedPanel card = new RoundedPanel(new Color(255, 255, 255, 200), 18);  // การ์ดสี่เหลี่ยม ให้มีสีขาวโปร่งๆ  มุมโค้ง 18
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16)); //  เว้นขอบด้านในรอบสี่เหลี่ยม 16 px
        card.setMaximumSize(new Dimension(338, Integer.MAX_VALUE)); // กว้างไม่เกิน 338 แต่สามารถขยายให้สูงได้
        card.setAlignmentX(Component.CENTER_ALIGNMENT); //ทำให้การ์ดสีเหลี่ยมอยู่กึ่งกลางคอลัมน์
        

        // หัวข้อ 
        JLabel title = new JLabel("Login"); //ข้อความในช่องสีเหลี่ยม
        title.setFont(Theme.font(Font.BOLD, 25)); // ขนาด และ font
        JLabel subtitle = new JLabel("Welcome back!"); //ข้อความในช่องสีเหลี่ยม
        subtitle.setFont(Theme.font(Font.PLAIN, 18)); // ขนาด และ font 
        subtitle.setForeground(Theme.TEXT_MUTED); // ทำให้ตัว Welcome back เป็นสี MUTED
        
        // ข้อความ error
        errorLabel.setForeground(Theme.RED); // สีแดง
        errorLabel.setFont(Theme.font(Font.PLAIN, 12)); // ขนาด และ font
        
        // ปุ่ม Login
        RoundedButton loginButton = new RoundedButton("Login", Theme.BLUE); // ปุ่ม Login สีน้ำเงิน
        loginButton.setPreferredSize(new Dimension(200, 58)); // ขนาด
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 58)); // // ตั้งความสูงไม่ให้ยึดเอง
        loginButton.addActionListener(e -> doLogin()); // กดปุ่มแล้วเรียก doLogin() เพื่อตรวจสอบ
        passwordField.addActionListener(e -> doLogin()); // กด Enter ในช่อง Password ก็ Login ได้เหมือนกัน
 
        RoundedButton leaveButton = new RoundedButton("Leave", Theme.RED); 
        leaveButton.setPreferredSize(new Dimension(200, 58));
        leaveButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 58));
        leaveButton.addActionListener(e -> System.exit(0));  // กดแล้วปิดโปรแกรมทันที
  
        card.add(title);    // ใส่หัวข้อ Login
        card.add(subtitle);  // ใส่ข้อความ Welcome back!
        card.add(Box.createVerticalStrut(12));  // เว้นช่องว่างแนวตั้ง 
        card.add(idField);  // ใส่ช่องกรอก Username
        card.add(Box.createVerticalStrut(16));
        card.add(passwordField);    // ใส่ช่องกรอก Password
        card.add(Box.createVerticalStrut(6));
        card.add(errorLabel);    // ใส่ข้อความ error (ตอนแรกเป็นช่องว่าง)
        card.add(Box.createVerticalStrut(6));
        card.add(loginButton);  // ใส่ปุ่ม Login
        card.add(Box.createVerticalStrut(12));
        card.add(leaveButton);   // ใส่ปุ่ม Leave
        card.add(Box.createVerticalGlue());
        card.add(buildFooter());    //ใส่แถว footer ("Don't have an account? Sign up")

        Layouts.alignLeft(card);    //ให้ทุกชิ้นในการ์ดชิดซ้ายเท่ากัน
        return card;
    }

    // สร้างแถวด้านล่างของการ์ด
    private JPanel buildFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));    // เรียงแนวนอน อยู่กึ่งกลาง ห่างกัน 4 px
        footer.setOpaque(false);    // ทำให้โปร่งใส เห็นสีการ์ดด้านหลัง
        footer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JLabel text = new JLabel("Don't have an account?"); // ข้อความบอกว่ายังไม่มีบัญชี
        text.setFont(Theme.font(Font.PLAIN, 15));
        text.setForeground(Theme.FIELD_TEXT);
        LinkButton signUp = new LinkButton("Sign up");  // ปุ่มสไตล์ลิงก์ Sign up
        signUp.addActionListener(e -> frame.showSignUp());  // กดแล้วสั่งหน้าต่างหลักให้ไปหน้า Sign up
        footer.add(text);   // ใส่ข้อความในแถว
        footer.add(signUp); // ใส่ลิงก์ Sign up ต่อท้ายข้อความ
        return footer;
    }
    
    // ตรวจสอบการ Login 
    private void doLogin() {
        User user = AuthService.login(idField.getText(), new String(passwordField.getPassword()));  // ส่ง username กับ password ให้ AuthService ตรวจ (ผิดจะได้ null)
        if (user == null) {  // ถ้าไม่พบผู้ใช้หรือรหัสผ่านผิด
            errorLabel.setText("Invalid username or password.");    // แสดงข้อความ error
            return;
        }
        frame.getSession().login(user);     // Login สำเร็00tบันทึกว่าใครล็อกอินอยู่ใน Session
        frame.goHome();     // สั่งหน้าต่างหลักให้ไปหน้า Home
    }

    // ล้างฟอร์ม
     public void reset() {
        idField.setText("");    // ล้างช่อง Username
        passwordField.setText("");   // ล้างช่อง Password
        errorLabel.setText(" ");    // ล้างข้อความ error
     }
}
