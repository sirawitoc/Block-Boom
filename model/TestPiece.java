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
        System.out.println("Size: " + p.size());         // ควรได้ 4package model;
        Board board = new Board();
        Piece square = new Piece(new int[][]{{1, 1}, {1, 1}}, Color.BLUE);

        System.out.println(board.canPlace(square, 0, 0)); // true
        System.out.println(board.canPlace(square, 7, 7)); // false (ล้นขอบ)
        
board.place(square, 0, 0);
System.out.println(board.getColor(0, 0));            // ควรได้ Color.BLUE
System.out.println(board.getColor(1, 1));            // ควรได้ Color.BLUE
System.out.println(board.getColor(2, 2));            // ควรได้ null (ไม่ได้วางตรงนี้)
System.out.println(board.canPlace(square, 0, 0));    // ควรได้ false (ชนของเดิมแล้ว)

Board b = new Board();
Piece line = new Piece(new int[][]{{1,1,1,1,1,1,1,1}}, Color.RED); // แถวยาว 8 ช่อง

b.place(line, 0, 0);
System.out.println(b.clearFullLines());   // ควรได้ 1
System.out.println(b.getColor(0, 0));     // ควรได้ null (ถูกลบแล้ว)
Board b2 = new Board();
Piece single = new Piece(new int[][]{{1}}, Color.RED);

System.out.println(b2.hasNoValidMove(single)); // false (กระดานว่าง วางได้)

// วางบล็อก 1 ช่องจนเต็มทั้งกระดาน
for (int r = 0; r < Board.SIZE; r++) {
    for (int c = 0; c < Board.SIZE; c++) {
        b2.place(single, r, c);
    }
}
System.out.println(b2.hasNoValidMove(single)); // true (เต็มแล้ว วางไม่ได้)

b2.reset();
System.out.println(b2.hasNoValidMove(single)); // false (รีเซ็ตแล้ว วางได้อีก)
        
    }
}
    

