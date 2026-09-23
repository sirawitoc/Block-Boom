package model;
import  java.awt.Color;

public class TestPiece {
     
    public static void main(String[] args) {
        int[][] lShape = {
            {1, 0},
            {1, 0},
            {1, 1}
        };
        Piece p = new Piece(lShape, Color.RED);

        System.out.println("Height: " + p.getHeight()); // ควรได้ 3
        System.out.println("Width: " + p.getWidth());   // ควรได้ 2
        System.out.println("Size: " + p.size());         // ควรได้ 4
    }
}

