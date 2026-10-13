package view;

import view.components.*;

import javax.swing.*;
import java.awt.*;


public class GameOverDialog extends PopupDialog {
public GameOverDialog(GameFrame frame, Component parent, String player, int score, Runnable onPlayAgain) {
        super(SwingUtilities.getWindowAncestor(parent), "Game Over", false);
    
        JLabel playerLabel = new JLabel(player);
        playerLabel.setFont(Theme.font(Font.BOLD, 16));
        playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreLabel = new JLabel("Score : " + score);
        scoreLabel.setFont(Theme.font(Font.BOLD, 16));
        scoreLabel.setForeground(Theme.PURPLE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton againBtn = button("Play Again", Theme.GREEN);
        againBtn.addActionListener(e -> {
            dispose();
            onPlayAgain.run();
        });
        
        RoundedButton homeBtn = button("Home", Theme.RED);
        homeBtn.addActionListener(e -> {
            dispose();
            frame.goHome();
        });

        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(BorderFactory.createEmptyBorder(18, 40, 20, 40));
        body.add(playerLabel);
        body.add(Box.createVerticalStrut(6));
        body.add(scoreLabel);
        body.add(Box.createVerticalStrut(16));
        body.add(againBtn);
        body.add(Box.createVerticalStrut(10));
        body.add(homeBtn);
        setBody(body);
        
}
    private RoundedButton button(String text, Color color) {
        RoundedButton b = new RoundedButton(text, color);
        b.setPreferredSize(new Dimension(200, 40));
        b.setMaximumSize(new Dimension(200, 40));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        return b;
    }
}