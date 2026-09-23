package model;
import  java.awt.Color;

public  class Piece {

     private  int [][] cells;
     private   Color color ;


     public Piece(int [][] cells,Color color){  //1=มีเนื้อบล็อก 0=ไม่มีเนื้อบล็อก
        this.cells=cells;
        this.color=color;

     }

     public  int[][] getCells(){
        return  cells;
     }

     public  Color color(){
        return  color;
     }

     public  int getHeight(){
        return  cells.length;
     }
      public  int getWidth(){
        if (cells.length==0) {
        return  0;  
             }else return  cells[0].length;
     }

     public  int size(){
        int count=0;
        for(int r=0;r<cells.length;r++){
            for(int c=0;c<cells[r].length;c++){
                if (cells[r][c]==1) {
                    count++;
                }
            }
        }
        return  count;
     }
}

