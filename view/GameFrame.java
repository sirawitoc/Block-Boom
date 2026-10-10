package view;

import model.*;

import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame {
    public static final String LOGIN = "login";
    public static final String SIGN_UP = "signup";
    public static final String HOME = "home";
    public static final String GAME = "game";
    public static final String LEADERBOARD = "leaderboard";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);
    private final Session session = new Session();

    private final LoginPanel loginPanel;
    private final SignUpPanel signUpPanel;
    private final HomePanel homePanel;
    private final GamePanel gamePanel;
    private final LeaderboardPanel leaderboardPanel;

    public GameFrame() {
        super("Block Boom ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        loginPanel = new LoginPanel(this);
        signUpPanel = new SignUpPanel(this);
        homePanel = new HomePanel(this);
        gamePanel = new GamePanel(this);
        leaderboardPanel = new LeaderboardPanel(this);

        cards.add(loginPanel, LOGIN);
        cards.add(signUpPanel, SIGN_UP);

        add(cards);
        pack();
        setLocationRelativeTo(null);
        showLogin();
        setVisible(true);
    }

    public Session getSession() {
        return session;
    }
     public void showLogin() {
        loginPanel.reset();
        cardLayout.show(cards, LOGIN);
     }
     public void showSignUp() {
        signUpPanel.reset();
        cardLayout.show(cards, SIGN_UP);

     }
     public void logout() {

     }
      public void goHome() {

      }
      public void startGame(String playerName) {

      }
      public void openLeaderboard() {

      }
}
