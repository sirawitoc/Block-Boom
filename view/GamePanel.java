package view;

import data.LeaderboardManager;
import model.Board;
import model.GameEngine;
import model.Piece;
import view.components.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** หน้าจอหลักของเกม */

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {

    private static final int CELL = 40;
    private static final int BOARD_PX = CELL * Board.SIZE;
    // ความกว้างความยาวของช่องวางบล็อค
    public static final int PANEL_W = 393;
    public static final int PANEL_H = 800;
    private static final int MARGIN = (PANEL_W - BOARD_PX) / 2;
    private static final int HEADER_Y = 124;
    // ตำแหน่งของช่องวางบล็อค
    private static final int BOARD_X = MARGIN;
    private static final int BOARD_Y = 232;
    // ตำแหน่งของบล็อคต่อ
    private static final int TRAY_Y = 600;
    private static final int TRAY_H = 100;
    private static final int TRAY_SLOT_W = BOARD_PX / GameEngine.TRAY_SIZE;

    private static final Rectangle SETTINGS_BUTTON = new Rectangle(PANEL_W - MARGIN - 40, HEADER_Y, 40, 40);

    private final GameFrame frame;
    private String playerName = "Player";

    private final GameEngine engine = new GameEngine();
    private int bestScore = 0;

    private int draggingSlot = -1;
    private int mouseX, mouseY;
    private boolean dragValid = false;
    private int dragRow, dragCol;

    // ทำหน้าที่เตรียม panel ให้พร้อม ตั้งขนาด เปิดรับเมาส์ โหลดคะแนนสูงสุด
    public GamePanel(GameFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(PANEL_W, PANEL_H));
        addMouseListener(this);
        addMouseMotionListener(this);
        bestScore = LeaderboardManager.getTopScore();
    }

    // รีเซ็ตกระดาน เริ่มเกมใหม่
    public void startNewGame(String playerName) {
        this.playerName = playerName;
        engine.newGame();
        draggingSlot = -1;
        bestScore = LeaderboardManager.getTopScore();
        repaint();
    }

    // บันทึกชื่อและคะแนนลงตารางอันดับ
    private void onGameOver() {
        LeaderboardManager.addScore(playerName, engine.getScore());
        bestScore = Math.max(bestScore, engine.getScore());
        repaint();

        SwingUtilities.invokeLater(() -> new GameOverDialog(frame, this, playerName, engine.getScore(),
                () -> startNewGame(playerName)).setVisible(true));
    }

    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);// ไม่ให้ภาพเฟรมเก่าซ้อนค้าง
        Graphics2D g = (Graphics2D) g0;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// ทำให้ขอบเนียน
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);// ทำให้ตัวอักษรเนียน

        g.setPaint(new GradientPaint(0, 0, Theme.BG_TOP, 0, getHeight(), Theme.BG_BOTTOM));// พื้นหลัง
        g.fillRect(0, 0, getWidth(), getHeight());// ตัวละบาย

        drawHeader(g);
        drawBoard(g);
        drawTray(g);
        if (draggingSlot != -1)
            drawDraggedPiece(g);
    }

    // ป้ายคะแนนปัจจุบัน ป้ายคะแนนสูงสุด ปุ่มตั้งค่า
    private void drawHeader(Graphics2D g) {
        drawBadge(g, MARGIN, HEADER_Y, 120, 50, "Score", String.valueOf(engine.getScore()), Theme.PURPLE, Color.WHITE);
        drawBadge(g, MARGIN + 140, HEADER_Y, 110, 50, "Best Score", String.valueOf(bestScore), Theme.YELLOW,
                Color.WHITE);
        drawSettingsButton(g);
    }

    // วาดป้ายสี่เหลี่ยมมุมมน
    private void drawBadge(Graphics2D g, int x, int y, int w, int h, String title, String value, Color bg, Color fg) {
        g.setColor(bg);
        g.fillRoundRect(x, y, w, h, 12, 12);
        g.setColor(fg);
        g.setFont(Theme.font(Font.BOLD, 10));
        FontMetrics fm = g.getFontMetrics();
        g.drawString(title, x + (w - fm.stringWidth(title)) / 2, y + 15);
        g.setFont(Theme.font(Font.BOLD, 18));
        fm = g.getFontMetrics();
        g.drawString(value, x + (w - fm.stringWidth(value)) / 2, y + 36);// วาดค่าคะแนนกึ่งกลางแนวนอน
    }

    // ปุ่ม setting
    private void drawSettingsButton(Graphics2D g) {
        g.setColor(Theme.PURPLE);
        g.fillRoundRect(SETTINGS_BUTTON.x, SETTINGS_BUTTON.y, SETTINGS_BUTTON.width, SETTINGS_BUTTON.height, 20, 20);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.PLAIN, 30));
        FontMetrics fm = g.getFontMetrics();
        String gear = "\u2699";
        // ทำให้เฟืองอยู่กึ่งกลาง
        int tx = SETTINGS_BUTTON.x + (SETTINGS_BUTTON.width - fm.stringWidth(gear)) / 2;
        int ty = SETTINGS_BUTTON.y + (SETTINGS_BUTTON.height + fm.getAscent()) / 2 - 3;
        g.drawString(gear, tx, ty);
    }

    // วาดกระดานเกม 8x8
    private void drawBoard(Graphics2D g) {
        Board board = engine.getBoard();
        g.setColor(Theme.BOARD_FRAME);// ตั้งสีกรอบกระดาน
        g.fillRoundRect(BOARD_X - 6, BOARD_Y - 6, BOARD_PX + 12, BOARD_PX + 12, 12, 12);

        for (int r = 0; r < Board.SIZE; r++) {
            for (int c = 0; c < Board.SIZE; c++) {
                int x = BOARD_X + c * CELL;
                int y = BOARD_Y + r * CELL;
                g.setColor(Theme.CELL_EMPTY);// ระบายพื้นช่องว่าง
                g.fillRect(x, y, CELL, CELL);
                g.setColor(Theme.CELL_LINE);// วาดเส้นขอบช่อง
                g.drawRect(x, y, CELL, CELL);
                Color fill = board.getColor(r, c);
                if (fill != null)
                    drawBlock(g, x, y, CELL, fill);
            }
        }
        if (draggingSlot != -1) {
            Piece p = engine.getPiece(draggingSlot);
            Color hl = dragValid ? new Color(46, 204, 113, 140) : new Color(231, 76, 60, 140);
            int[][] shape = p.getCells();
            for (int pr = 0; pr < shape.length; pr++) {
                for (int pc = 0; pc < shape[pr].length; pc++) {
                    if (shape[pr][pc] == 1) {
                        // แปลงตำแหน่งในรูปร่างเป็นตำแหน่งบนกระดาน
                        int br = dragRow + pr;
                        int bc = dragCol + pc;
                        // เงาของบล็อค
                        if (br >= 0 && br < Board.SIZE && bc >= 0 && bc < Board.SIZE) {
                            g.setColor(hl);
                            g.fillRect(BOARD_X + bc * CELL, BOARD_Y + br * CELL, CELL, CELL);
                        }
                    }
                }
            }
        }
    }

    private void drawBlock(Graphics2D g, int x, int y, int size, Color color) {
        g.setColor(color);
        g.fillRoundRect(x + 1, y + 1, size - 2, size - 2, size / 4, size / 4);// ตำแหน่งบล็อค
        g.setColor(new Color(255, 255, 255, 90));// สีเงาบล็อค
        g.fillRoundRect(x + 3, y + 3, size - 6, Math.max(2, (size - 6) / 3), size / 5, size / 5);// ตำแหน่งของเงา
    }

    // วาดถาดด้านล่าง
    private void drawTray(Graphics2D g) {
        for (int i = 0; i < GameEngine.TRAY_SIZE; i++) {
            int slotX = MARGIN + i * TRAY_SLOT_W;
            g.setColor(new Color(255, 255, 255, 170));
            g.fillRoundRect(slotX + 5, TRAY_Y, TRAY_SLOT_W - 10, TRAY_H, 12, 12);// พื้นหลังถาดมุมมน
            // สีเส้นขอบ
            g.setColor(Theme.FIELD_BORDER);
            g.drawRoundRect(slotX + 5, TRAY_Y, TRAY_SLOT_W - 10, TRAY_H, 12, 12);
            // กันภาพค้าง
            if (i == draggingSlot)
                continue;
            Piece p = engine.getPiece(i);
            if (p == null)
                continue;
            drawPiecePreview(g, p, slotX, TRAY_Y, TRAY_SLOT_W, TRAY_H);
        }
    }

    // วาดชิ้นส่วนแบบย่อให้อยู่กึ่งกลางพื้นที่ที่กำหนด
    private void drawPiecePreview(Graphics2D g, Piece p, int areaX, int areaY, int areaW, int areaH) {
        int previewCell = Math.min(24, Math.min((areaW - 24) / p.getWidth(), (areaH - 24) / p.getHeight()));
        int w = p.getWidth() * previewCell;
        int h = p.getHeight() * previewCell;
        int startX = areaX + (areaW - w) / 2;
        int startY = areaY + (areaH - h) / 2;
        int[][] shape = p.getCells();
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] == 1) {
                    drawBlock(g, startX + c * previewCell, startY + r * previewCell, previewCell, p.getColor());
                }
            }
        }
    }
    //วาดชิ้นส่วนที่กำลังถูกลาก
    private void drawDraggedPiece(Graphics2D g) {
        Piece p = engine.getPiece(draggingSlot);
        int w = p.getWidth() * CELL;
        int h = p.getHeight() * CELL;
        int startX = mouseX - w / 2;
        int startY = mouseY - h / 2;
        int[][] shape = p.getCells();
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] == 1) {
                    drawBlock(g, startX + c * CELL, startY + r * CELL, CELL, p.getColor());
                }
            }
        }
    }
    //คำนวณช่องบนกระดานที่ชิ้นส่วนจะวางลง
    private void updateDragTarget() {
        Piece p = engine.getPiece(draggingSlot);
        int w = p.getWidth() * CELL;
        int h = p.getHeight() * CELL;
        int startX = mouseX - w / 2;
        int startY = mouseY - h / 2;
        dragCol = Math.round((startX - BOARD_X) / (float) CELL);
        dragRow = Math.round((startY - BOARD_Y) / (float) CELL);
        dragValid = engine.canPlace(draggingSlot, dragRow, dragCol);
    }

    //เอาไว้ลากบล็อค
    public void mouseDragged(MouseEvent e) {
        if (draggingSlot == -1)
            return;
        mouseX = e.getX();
        mouseY = e.getY();
        updateDragTarget();
        repaint();
    }

    //เอาไว้กดบล็อค ปุ่มตั้งค่า
    public void mousePressed(MouseEvent e) {
        if (engine.isGameOver())
            return;
        if (SETTINGS_BUTTON.contains(e.getPoint())) {
            new SettingsDialog(frame, this).setVisible(true);
            return;
        }
        mouseX = e.getX();
        mouseY = e.getY();
        if (e.getY() >= TRAY_Y && e.getY() <= TRAY_Y + TRAY_H) {
            int slot = (e.getX() - MARGIN) / TRAY_SLOT_W;
            if (slot >= 0 && slot < GameEngine.TRAY_SIZE && engine.getPiece(slot) != null) {
                draggingSlot = slot;
                updateDragTarget();
                repaint();
            }
        }
    }

    //จุดจบของการลาก
    public void mouseReleased(MouseEvent e) {
        if (draggingSlot == -1)
            return;
        int slot = draggingSlot;
        draggingSlot = -1;//เคลียร์สถานะการลากทันที
        if (dragValid && engine.tryPlace(slot, dragRow, dragCol)) {
            if (engine.getScore() > bestScore)
                bestScore = engine.getScore();
            if (engine.isGameOver())
                onGameOver();
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}