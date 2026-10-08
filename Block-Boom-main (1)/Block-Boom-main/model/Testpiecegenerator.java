package model;
import  java.util.List;
public class Testpiecegenerator {
    public static void main(String[] args) {
        PieceGenerator gen = new PieceGenerator();
        Piece p = gen.generateRandom();
        System.out.println("Width: " + p.getWidth());
        System.out.println("Height: " + p.getHeight());
        System.out.println("Color: " + p.getColor());

        // เพิ่มทดสอบ generateTray ต่อตรงนี้ ยังอยู่ใน main() เดิม
        List<Piece> tray = gen.generateTray(3);
        System.out.println("Tray size: " + tray.size());
        for (Piece piece : tray) {
            System.out.println("- width " + piece.getWidth() + ", height " + piece.getHeight());
        }
    }
}