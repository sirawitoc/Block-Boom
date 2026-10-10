package model;

import java.util.List;
/**
 * GameEngine.java
 * classนี้ได้นำ Piece Borad PieceGeneretor นำมาช่วยในการกำหนดกฎต่างๆในเกม 
 * เช่นการนับคะเเนน การวางบล็อก รวมไปถึงการรู้ว่าเกมจะจบยังไง
 */


public class GameEngine {
    public static final int TRAY_SIZE = 3;   //กำหนดให้ถาดว่างบล็อกมี3อันไม่สามารถเปลี่ยนค่าได้

    private Board board;
    private PieceGenerator generator;
    private Piece[] tray;
    private int score;
    private boolean gameOver;

    public GameEngine() {   
        board = new Board();                 //เริ่มเป็นกระดานใหม่
        generator = new PieceGenerator();      
        tray = new Piece[TRAY_SIZE];         
        newGame();
    }

    // เริ่มเกมใหม่
    public void newGame() {
        board.reset();                      //เริ่มเกมใหม่
        score = 0;                          //กำหนดสกอร์ตอนเริ่มเป็น0
        gameOver = false;                    //กำหนดให้เgameover false
        refillTray();                          //เปลี่ยนถุงเก็บล็อกในการเล่น
    }

    public Board getBoard() {
        return board;
    }

    // ดูบล็อกในช่องที่ slot (คืน null ถ้าช่องนั้นวางไปแล้ว)
    public Piece getPiece(int slot) {
        if (slot < 0 || slot >= TRAY_SIZE) {
            return null;
        }
        return tray[slot];
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    // ถามว่าบล็อกช่อง slot วางที่ (row, col) ได้ไหม (ยังไม่วางจริง)
    public boolean canPlace(int slot, int row, int col) {
        Piece piece = getPiece(slot);
        if (gameOver || piece == null) {
            return false;
        }
        return board.canPlace(piece, row, col);
    }

    // พยายามวางจริง คืน true ถ้าวางสำเร็จ
    public boolean tryPlace(int slot, int row, int col) {
        if (!canPlace(slot, row, col)) {
            return false;
        }
        Piece piece = tray[slot];

        board.place(piece, row, col);
        score += piece.size() * 10;               // คะแนนจากการวาง

        int lines = board.clearFullLines();
        score += lines * lines * 100;             // คะแนนจากการแตกของบล็อก (ยิ่งแตกพร้อมกันยิ่งได้เยอะ)

        tray[slot] = null;                        // บล็อกชิ้นนี้ใช้ไปแล้ว
        if (trayEmpty()) {
            refillTray();
        }
        checkGameOver();
        return true;
    }

    // ---------- private helper ----------

    private void refillTray() {
        List<Piece> pieces = generator.generateTray(TRAY_SIZE);
        for (int i = 0; i < TRAY_SIZE; i++) {
            tray[i] = pieces.get(i);
        }
    }

    private boolean trayEmpty() {        //เช็กว่าบล็อกใยถาดว่างไหม
        for (Piece p : tray) {            
            if (p != null) {              //ไม่ว่าง return false
                return false;
            }
        }
        return true;                       //ถาดว่างจริง return ture
    }

    // เกมจบเมื่อ "ทุกชิ้นที่เหลือในถาด" วางไม่ได้เลย
    private void checkGameOver() {
        for (Piece p : tray) {
            if (p != null && !board.hasNoValidMove(p)) {
                return;  // มีชิ้นที่ยังวางได้ เกมยังไม่จบ
            }
        }
        gameOver = true;
    }
}