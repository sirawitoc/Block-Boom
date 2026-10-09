package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.nio.charset.CodingErrorAction;

import javax.swing.JPanel;

import model.Board;
import model.GameEngine;
import model.Piece;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
    private int CELL = 40;
    private int BOARD_PX = CELL * Board.SIZE;
    private int MARGIN = 35;
    private int BOARD_X = MARGIN;
    private int BOARD_Y = 100;
    private int TRAY_Y = BOARD_Y + BOARD_PX + 30;
    private int TRAY_H = 120;
    public int PANEL_W = BOARD_PX + 2 * MARGIN;
    public int PANEL_H = TRAY_Y + TRAY_H + 40;
    private int TRAY_SLOT_W = BOARD_PX / GameEngine.TRAY_SIZE;

    private Rectangle SETTINGS_BUTTON = new Rectangle(PANEL_W - MARGIN - 40, 24, 40, 40);

    private GameFrame frame;
    private String playerName = "Player";
    private GameEngine engine = new GameEngine();
    private int bestscore = 0;
    private int draggingSlot = -1;
    private int mouseX,mouseY;
    private boolean dragValid = false;
    private int dragRow,dragCol;
    

    public GamePanel(GameFrame frame){

    }

    public void startNewGame(String playerName){

    }

    private void onGameOver(){

    }

    protected void paintComponent(Graphics g){

    }

    private void drawHeader(Graphics2D g){

    }

    private void drawBadge(Graphics2D g ,int x , int y , int w , int h , String title , String value , Color bg , Color fg){

    }

    private void drawSettingsButton(Graphics2D g){

    }

    private void drawBoard(Graphics2D g){

    }

    private void drawBlock(Graphics2D g ,int x ,int y ,int size ,Color color ,Color bg ,Color fg){

    } 

    private void drawtray(Graphics2D g){

    }

    private void drawPiecePreview(Graphics2D g ,Piece p ,int areaX ,int areaY ,int areaW ,int areaH){

    }

    private void drawDraggedPiece(Graphics2D g){

    }

    private void updateDragTarget(){

    }

    public 

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