package view;

import data.LeaderboardManager;
import model.Board;
import model.GameEngine;
import model.Piece;
import view.components.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GamePanel
 */
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener  {
    private static final int CELL = 40;
    private static final int BOARD_PX = CELL * Board.SIZE;
    public static final int PANEL_W = 393;
    public static final int PANEL_H = 800;
    private static final int MARGIN = (PANEL_W - BOARD_PX) / 2;   
    private static final int HEADER_Y = 124;                       
    private static final int BOARD_X = MARGIN;
    private static final int BOARD_Y = 232;
    private static final int TRAY_Y = 600;
    private static final int TRAY_H = 100;
    private static final int TRAY_SLOT_W = BOARD_PX / GameEngine.TRAY_SIZE;
 
    private static final Rectangle SETTINGS_BUTTON = new Rectangle(PANEL_W - MARGIN - 40, HEADER_Y, 40, 40);
    
    private final GameFrame frame;
    private String playerName = "Player";

     private final GameEngine engine = new GameEngine();
    private int bestScore = 0;

    // ---- drag state ----
    private int draggingSlot = -1;   
    private int mouseX, mouseY;
    private boolean dragValid = false;
    private int dragRow, dragCol;


    public GamePanel(GameFrame frame) {
        this.frame = frame;
    }
    public void startNewGame(String playerName) {
        this.playerName = playerName;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}