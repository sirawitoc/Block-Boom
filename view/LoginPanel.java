package view;

import data.*;
import model.*;
import view.components.*;


import javax.swing.*;
import java.awt.*;

public class LoginPanel extends GradientPanel{
    private  final PlaceholderField idField = new PlaceholderField("   Username");
    private final PlaceholderPasswordField passwordField = new PlaceholderPasswordField("    Password");
    private final JLabel errorLabel = new JLabel(" ");
    private final GameFrame frame;

    public  LoginPanel(GameFrame frame){
        this.frame = frame;
        setPreferredSize(new Dimension(GamePanel.PANEL_W, GamePanel.PANEL_H));
        setLayout(new GridBagLayout());

         JPanel column = new JPanel();
        column.setOpaque(false);
        column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
        column.setPreferredSize(new Dimension(338, 706));
        column.add(new LogoLabel());
        column.add(buildCard());

        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.weighty = 1;
        gc.anchor = GridBagConstraints.NORTH;
        gc.insets = new Insets(20, 0, 0, 0);
        add(column, gc);
    }
    private  JPanel buildCard(){
        RoundedPanel card = new RoundedPanel(new Color(255, 255, 255, 200), 18);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        card.setMaximumSize(new Dimension(338, Integer.MAX_VALUE)); // card stretches down to the bottom
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        JLabel title = new JLabel("Login");
        title.setFont(Theme.font(Font.BOLD, 25));
        JLabel subtitle = new JLabel("Welcome back!");
        subtitle.setFont(Theme.font(Font.PLAIN, 18));
        subtitle.setForeground(Theme.TEXT_MUTED);
 
        errorLabel.setForeground(Theme.RED);
        errorLabel.setFont(Theme.font(Font.PLAIN, 12));
 
        RoundedButton loginButton = new RoundedButton("Login", Theme.BLUE);
        loginButton.setPreferredSize(new Dimension(200, 58));
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 58));
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
