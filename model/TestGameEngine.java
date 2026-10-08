package model;

public class TestGameEngine {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        System.out.println("Score: " + engine.getScore());        // 0
        System.out.println("GameOver: " + engine.isGameOver());   // false

        for (int i = 0; i < GameEngine.TRAY_SIZE; i++) {
            System.out.println("Slot " + i + " มีบล็อก: " + (engine.getPiece(i) != null)); // true ทั้ง 3
        }

        System.out.println("วางช่อง 0 ที่ (0,0): " + engine.tryPlace(0, 0, 0));  // true
        System.out.println("Score: " + engine.getScore());                       // มากกว่า 0
        System.out.println("Slot 0 หลังวาง: " + engine.getPiece(0));             // null
        System.out.println("วางช่อง 0 ซ้ำ: " + engine.tryPlace(0, 4, 4));       // false (ใช้ไปแล้ว)
    }
}