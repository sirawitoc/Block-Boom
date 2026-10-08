package model;
import  java.awt.Color;


public  class Board {

     public  static final int SIZE=8;
     private   Color[][] grid;  //ช่องว่างมีค่า=มีบล็อกอยู่ (เก้บสีขอบล็อกนั้น)
     public Board(){                //สร้างบล็อก8*8
          grid= new Color[SIZE][SIZE];
     }


     public boolean canPlace(Piece piece,int row,int col){  //เช็คว่าสามารถว่างบล็อกได้ไหม
     int[][] cells =piece.getCells();

     for(int r=0;r<piece.getHeight();r++){   //เช็กทุกช่องโดยการวนloop
          for(int c=0;c<piece.getWidth();c++){
               if (cells[r][c]==0) {  //ถ้าช่องทีตรวจ=0
                    continue;          //ให้ต่อไป
               }

               int targetrow =r+row;   // นำช่องของบล็อกบวกกับช่องที่จะว่าง
               int targetcol=c+col;    
               //เช็กขอบว่าเลยขอบไหม
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

     public void place (Piece piece ,int row ,int col){   //ว่างบล็อก
          int [][]cells=piece.getCells();
          Color color = piece.getColor();

     for(int r=0;r<piece.getHeight();r++){
          for(int c=0;c<piece.getWidth();c++){
               if (cells[r][c]==1) {
                    grid[row+r][col+c]=color;
               }
          }
     }
     
          
     }
     public  int clearFullLines(){      //จดไว้ว่าช่องไหนต้องโดนลบบ้าง **ยังไม่โดนลบ**
         boolean []fullRows=new boolean[SIZE];
         boolean [] fullCols=new boolean[SIZE];
         int cleared=0;

         for(int i=0;i<SIZE;i++){    //เช็กเเถว
          if (isRowFull(i)) {
               fullRows[i]=true;
               cleared++;
          }
          if (isColumnFull(i)) {    //เช็กคอลัมน์
               fullCols[i]=true;
               cleared++;
          }
          
         }
         
         for(int i=0;i<SIZE;i++){   //ลบบล็อกเมื่อเเถวนั้ยบล็อกเต็ม
          if(fullRows[i])clearRow(i);
          if (fullCols[i])clearCol(i); 
         }
         return cleared;

         
     }
  
     public  Color getColor(int row, int col){
        return grid[row][col];
     }

     public boolean hasNoValidMove(Piece piece){  //เช็กว่าบล็อกที่เหลือสามารถว่างได้ไหมถ้าไม่ได้=ture
     for(int row=0;row<SIZE;row++){              //วนloopทุกช่อง
          for(int col=0;col<SIZE;col++){
               if(canPlace(piece, row, col)){
                    return  false;                //ถ้ายังว่างได้=false
               }
          }
     }
     return  true;                               //ถ้าว่างไม่ได้ให้return ture
     }

     public  void  reset(){
          grid= new Color[SIZE][SIZE];  //reset หน้ากระดานใหม้เป็นหน้ากระดานว่าง
          
     }
     private boolean isRowFull(int row){  //เช็กว่าบล็อกในเเถวเต็มหรือยัง**ยังไม่ลบ**
          for(int c=0;c<SIZE;c++){        //วนloop เช็กทุกช่อง
               if (grid[row][c]==null) {   //วนเเล้วไม่เจอค่า ให้return false
                    return  false;
               }
          }
          return true;                     //เเต่ถ้าเจอให้return ture เเปรว่าช่องนั้นเต็มเเล้ว
     }
     private  boolean isColumnFull(int col){   //เช็กว่าบล็อกในคอลัมน์เต็มหรือยัง**ยังไม่ลบ**
          for(int r=0;r<SIZE;r++){             //วนloop เช็กทุกช่อง
               if (grid[r][col]==null) {
                    return false;              //วนเเล้วไม่เจอค่า ให้return false
               }
          }
          return  true ;                       //เเต่ถ้าเจอให้return ture เเปรว่าช่องนั้นเต็มเเล้ว
     }
     private  void  clearRow(int row){  //ลบถ้าเเถวเต็ม

          for(int c=0;c<SIZE;c++){
               grid[row][c]=null;
                    
               
          }
      }

     private void  clearCol(int col){  //ลบถ้าคอลัมน์เต็ม
     for (int r=0;r<SIZE;r++){
          grid[r][col]=null;
               
          
     }
     }
}