package view;

import javax.swing.*;
import java.awt.*;

import view.components.*;

public class HomePanel extends GradientPanel {
    private PlaceholderField nameField = new PlaceholderField("Enter your name");
    private JLabel errorLabel = new JLabel(" ");
    private GameFrame frame;

    public HomePanel(GameFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(GamePanel.PANEL_W, GamePanel.PANEL_H));
        setLayout(new GridBagLayout());

        JPanel column = new JPanel();
        column.setOpaque(false);
        column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
        column.setPreferredSize(new Dimension(300, 440));

        JLabel nameLabel = new JLabel("Name :");
        nameLabel.setFont(Theme.font(Font.BOLD, 12));
        errorLabel.setForeground(Theme.RED);
        errorLabel.setFont(Theme.font(Font.PLAIN, 12));

        RoundedButton startBtn = menuButton("Start", Theme.GREEN);
        startBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                errorLabel.setText("Please enter your name.");
                return;
            }
            frame.startGame(name);
        });

        RoundedButton scoreBtn = menuButton("Leaderboard", Theme.BLUE);
        scoreBtn.addActionListener(e -> frame.openLeaderboard());

        RoundedButton logoutBtn = menuButton("Log out", Theme.RED);
        logoutBtn.addActionListener(e -> frame.logout());

        column.add(new LogoLabel());
        column.add(Box.createVerticalStrut(20));
        column.add(nameLabel);
        column.add(Box.createVerticalStrut(4));
        column.add(nameField);
        column.add(errorLabel);
        column.add(Box.createVerticalStrut(10));
        column.add(startBtn);
        column.add(Box.createVerticalStrut(14));
        column.add(scoreBtn);
        column.add(Box.createVerticalStrut(14));
        column.add(logoutBtn);
        Layouts.alignLeft(column);
        add(column);

    }

    private RoundedButton menuButton(String text, Color color) {
        RoundedButton b = new RoundedButton(text, color);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        return b;
    }

    public void refresh() {
        nameField.setText(frame.getSession().getPlayerName());
        errorLabel.setText(" ");
    }
}
