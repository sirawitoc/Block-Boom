package model;
import  java.awt.Color;


public  class Board {

     public  static final int SIZE=8;
     private   Color[][] grid;  //ช่องว่างมีค่า=มีบล็อกอยู่ (เก้บสีขอบล็อกนั้น)


     public boolean canPlace(Piece piece,int row,int col){
          return true;

     }

     public void place (Piece piece ,int row ,int col){
           
     }
     public  int clearFullLines(){
          return  0;
     }

     public  Color getColor(int row, int col){
        return grid[row][col];
     }

     public boolean hasaNoValidMove(Piece piece){
          return  true;
     }

     public  void  reset(){
          
     }
}