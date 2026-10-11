package view;

import data.*;
import view.components.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * LeaderboardPanel
 */
public class LeaderboardPanel extends  GradientPanel{
    private static final Color GOLD = new Color(0xF2C94C);
    private static final Color SILVER = new Color(0xC3CEDD);
    private static final Color BRONZE = new Color(0xD1A073);

    private final DefaultTableModel model;
    private boolean hasData = false;


public LeaderboardPanel(GameFrame frame) {
        this.model = new DefaultTableModel();
        setPreferredSize(new Dimension(GamePanel.PANEL_W, GamePanel.PANEL_H));
        setLayout(new BorderLayout(0, 12));
        setBorder(BorderFactory.createEmptyBorder(24, 20, 24, 20));

     RoundedPanel titlePill = new RoundedPanel(Theme.YELLOW, 12);
     titlePill.setLayout(new BorderLayout());
     JLabel title = new JLabel("Leaderboard",SwingConstants.CENTER);
     title.setFont(Theme.font(Font.BOLD, 20));
     title.setForeground(Color.WHITE);
     title.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));   
     titlePill.add(title);

     JPanel header = new JPanel(new BorderLayout(10, 0));
        header.setOpaque(false);
        header.add(titlePill, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
}
 public void refresh() {
    
}
}