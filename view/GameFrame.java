package view;

import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * GameFrame.java
 *
 * หน้าต่างหลักของเกม (มีอยู่อันเดียวทั้งโปรแกรม)
 * ทำหน้าที่เป็น "ตัวกลาง" เก็บทุกหน้า (Login, SignUp, Home, Game, Leaderboard)
 * แล้วสลับโชว์ทีละหน้าด้วย CardLayout
 * หน้าอื่นๆ จะเรียกเมธอดในคลาสนี้เวลาอยากไปหน้าอื่น เช่น frame.goHome()
 */
public class GameFrame extends JFrame {
    // ชื่อเรียกแต่ละหน้า ไว้ใช้เป็นป้ายตอนสลับหน้า
    public static final String LOGIN = "login";
    public static final String SIGN_UP = "signup";
    public static final String HOME = "home";
    public static final String GAME = "game";
    public static final String LEADERBOARD = "leaderboard";

    private final CardLayout cardLayout = new CardLayout(); // CardLayout = เหมือนกองการ์ด วางซ้อนกัน โชว์ได้ทีละใบ
    private final JPanel cards = new JPanel(cardLayout); // panel ที่เป็นกองการ์ด (ใส่ cardLayout เข้าไป)
    private final Session session = new Session(); // เก็บข้อมูลคนที่ล็อกอินอยู่ (ชื่อผู้เล่น ฯลฯ) ให้ทุกหน้าใช้ร่วมกัน

     // แต่ละหน้าของเกม (เก็บไว้เป็นตัวแปร จะได้เรียก reset/refresh ได้)
    private final LoginPanel loginPanel;
    private final SignUpPanel signUpPanel;
    private final HomePanel homePanel;
    private final GamePanel gamePanel;
    private final LeaderboardPanel leaderboardPanel;

    public GameFrame() {
        super("Block Boom "); // ชื่อบนหัวหน้าต่าง
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // กดปิดหน้าต่างแล้วปิดโปรแกรมทั้งหมด
        setResizable(false);  // ห้ามย่อ/ขยายหน้าต่าง (ขนาดคงที่)

        // สร้างทุกหน้า โดยส่ง this (หน้าต่างนี้) ไปให้ เพื่อให้หน้านั้นเรียกสลับหน้าได้
        loginPanel = new LoginPanel(this);
        signUpPanel = new SignUpPanel(this);
        homePanel = new HomePanel(this);
        gamePanel = new GamePanel(this);
        leaderboardPanel = new LeaderboardPanel(this);

         // ใส่ทุกหน้าลงกองการ์ด พร้อมป้ายชื่อ
        cards.add(loginPanel, LOGIN);
        cards.add(signUpPanel, SIGN_UP);
        cards.add(homePanel, HOME);
        cards.add(gamePanel, GAME);
        cards.add(leaderboardPanel,LEADERBOARD);

        add(cards);   // เอากองการ์ดใส่ในหน้าต่าง
        pack();   // ปรับขนาดหน้าต่างให้พอดีกับเนื้อหา
        setLocationRelativeTo(null);   // วางหน้าต่างกลางจอ
        showLogin(); // เริ่มต้นที่หน้า Login
        setVisible(true);  // แสดงหน้าต่าง
    }

    // ให้หน้าอื่นขอดูข้อมูลผู้ใช้ที่ล็อกอินอยู่
    public Session getSession() {
        return session;
    }

    // ไปหน้า Login (ล้างข้อมูลที่เคยกรอกค้างไว้ก่อน)
     public void showLogin() {
        loginPanel.reset();
        cardLayout.show(cards, LOGIN);
     }

      // ไปหน้าสมัครสมาชิก (ล้างฟอร์มก่อน)
     public void showSignUp() {
        signUpPanel.reset();
        cardLayout.show(cards, SIGN_UP);

     }

     // ออกจากระบบ: ล้างข้อมูลผู้ใช้ แล้วกลับหน้า Login
     public void logout() {
        session.logout();
        showLogin();
     }

     // ไปหน้า Home (อัปเดตข้อมูลหน้าก่อนโชว์)
      public void goHome() {
        homePanel.refresh();
        cardLayout.show(cards, HOME);
      }

      // เริ่มเล่นเกม: จำชื่อผู้เล่นใน session, เริ่มเกมใหม่, แล้วสลับไปหน้าเกม
      public void startGame(String playerName) {
         session.setPlayerName(playerName);
        gamePanel.startNewGame(playerName);
        cardLayout.show(cards, GAME);
      }

       // ไปหน้า Leaderboard (โหลดคะแนนล่าสุดก่อนโชว์)
      public void openLeaderboard() {
         leaderboardPanel.refresh();
        cardLayout.show(cards, LEADERBOARD);
      }
}
