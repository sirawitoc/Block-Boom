package model;

import java.awt.Color;

public class TestMultiClear {
    public static void main(String[] args) {
        Board board = new Board();

        Piece rowPart = new Piece(new int[][]{{1,1,1,1,1,1,1}}, Color.RED);
        Piece colPart = new Piece(new int[][]{{1},{1},{1},{1},{1},{1},{1}}, Color.BLUE);
        Piece dot = new Piece(new int[][]{{1}}, Color.GREEN);

        board.place(rowPart, 0, 1);   // แถว 0 ช่อง 1-7
        board.place(colPart, 1, 0);   // คอลัมน์ 0 แถว 1-7
        board.place(dot, 3, 3);       // ตัวตรวจ ไม่อยู่ในแถว 0 หรือคอลัมน์ 0

        System.out.println("ก่อนวางช่องสุดท้าย: " + board.clearFullLines()); // 0

        board.place(dot, 0, 0);       // ช่องจุดตัด ทำให้แถว 0 และคอลัมน์ 0 เต็มพร้อมกัน
        System.out.println("แตกพร้อมกัน: " + board.clearFullLines());        // 2

        System.out.println("(0,0): " + board.getColor(0, 0));   // null
        System.out.println("(0,7): " + board.getColor(0, 7));   // null
        System.out.println("(7,0): " + board.getColor(7, 0));   // null
        System.out.println("(3,3): " + board.getColor(3, 3));   // ยังมีสีเขียวอยู่
        
    }
}