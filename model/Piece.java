package model;
import  java.awt.Color;

public  class Piece {

     private  int [][] cells;
     private   Color color ;


     public Piece(int [][] cells,Color color){  //1=มีเนื้อบล็อก 0=ไม่มีเนื้อบล็อก
        this.cells=cells;
        this.color=color;

     }

     public  int[][] getCells(){               //รูปทรงหน้าตาของบล็อกเช่นรูปตัว L O
        return  cells;
     }

     public  Color color(){                     //กำหนดสีของบล็อก
        return  color;
     }

     public  int getHeight(){                   //เมดตอดตัวนี้มีหน้าที่บอกว่าบล็อกที่สร้างสูงกี่ช่อง(มีกี่เเถว)
        return  cells.length;
     }
      public  int getWidth(){                    //เมดตอดตัวนี้มีหน้าที่บอกว่าบล็อกที่สร้างกว้างช่อง(มีกี่คอลัมน์)
        if (cells.length==0) {
        return  0;  
             }else return  cells[0].length;
     }

     public  int size(){                            //วนเช็คว่าช่องไหนมีบล็อกบ้าง  1=มีเนื้อบล็อก 0=ไม่มีเนื้อบล็อก
        int count=0;
        for(int r=0;r<cells.length;r++){             //วนทุกเเถว
            for(int c=0;c<cells[r].length;c++){      //วนทุกคอลัมน์
                if (cells[r][c]==1) {                //ถ้าช่องมีบล็อก  (ค่า=1)
                    count++;                         //บวกตัวนับ
                }
            }
        }
        return  count;
     }
}

