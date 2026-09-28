package model;
import  java.awt.Color;


public  class Board {

     public  static final int SIZE=8;
     private   Color[][] grid;  //ช่องว่างมีค่า=มีบล็อกอยู่ (เก้บสีขอบล็อกนั้น)

     public Board(){
          grid= new Color[SIZE][SIZE];
     }


     public boolean canPlace(Piece piece,int row,int col){
     int[][] cells =piece.getCells();

     for(int r=0;r<piece.getHeight();r++){
          for(int c=0;c<piece.getWidth();c++){
               if (cells[r][c]==0) {
                    continue;
               }

               int targetrow =r+row;
               int targetcol=c+col;
               //เช็กขอบ
               if (targetrow<0||targetrow>=SIZE
               ||targetcol<0||targetcol>=SIZE) {
                    return  false;
               }
               //เช็กว่าบล็อกเดิมชนไหม
               if (grid[targetrow][targetcol]!=null) {
                    return false;
               }
               
          }
     }
     return true;

     }

     public void place (Piece piece ,int row ,int col){
          int [][]cells=piece.getCells();
          Color color = piece.getCorlor();

     for(int r=0;r<piece.getHeight();r++){
          for(int c=0;c<piece.getWidth();c++){
               if (cells[r][c]==1) {
                    grid[row+r][col+c]=color;
               }
          }
     }
     
          
     }
     public  int clearFullLines(){
         boolean []fullRows=new boolean[SIZE];
         boolean [] fullCols=new boolean[SIZE];
         int cleared=0;

         for(int i=0;i<SIZE;i++){
          if (isRowFull(i)) {
               fullRows[i]=true;
               cleared++;
          }
          if (isColumnFull(i)) {
               fullCols[i]=true;
               cleared++;
          }
          
         }
         
         for(int i=0;i<SIZE;i++){
          if(fullRows[i])clearRow(i);
          if (fullCols[i])clearCol(i); 
         }
         return cleared;

         
     }
  
     public  Color getColor(int row, int col){
        return grid[row][col];
     }

     public boolean hasNoValidMove(Piece piece){
     for(int row=0;row<SIZE;row++){
          for(int col=0;col<SIZE;col++){
               if(canPlace(piece, row, col)){
                    return  false;
               }
          }
     }
     return  true;
     }

     public  void  reset(){
          grid= new Color[SIZE][SIZE];
          
     }
     private boolean isRowFull(int row){
          for(int c=0;c<SIZE;c++){
               if (grid[row][c]==null) {
                    return  false;
               }
          }
          return true;
     }
     private  boolean isColumnFull(int col){
          for(int r=0;r<SIZE;r++){
               if (grid[r][col]==null) {
                    return false;
               }
          }
          return  true ;
     }
     private  void  clearRow(int row){

          for(int c=0;c<SIZE;c++){
               grid[row][c]=null;
                    
               
          }
      }

     private void  clearCol(int col){
     for (int r=0;r<SIZE;r++){
          grid[col][r]=null;
               
          
     }
     }
}