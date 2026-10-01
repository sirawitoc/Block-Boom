package model;
import java.awt.Color;
import  java.util.Random;
import  java.util.List;
import  java.util.ArrayList;
import  java.util.Collections;

public  class PieceGenerator {


    private static  final int[][][] SHAPES={        //รูปทรงต่างๆของบล็อก
         // ขนาดเล็ก
    { {1} },                           // จุดเดี่ยว
    { {1, 1} },                        // เส้นนอน 2
    { {1}, {1} },                      // เส้นตั้ง 2

    // ขนาดกลาง
    { {1, 1, 1} },                     // เส้นนอน 3
    { {1}, {1}, {1} },                 // เส้นตั้ง 3
    { {1, 1}, {1, 1} },                // สี่เหลี่ยม 2x2
    { {1, 0}, {1, 0}, {1, 1} },        // ตัว L
    { {0, 1}, {0, 1}, {1, 1} },        // ตัว L กลับด้าน
    { {1, 1, 0}, {0, 1, 1} },          // ตัว Z
    { {0, 1, 1}, {1, 1, 0} },          // ตัว S

    // ขนาดใหญ่
    { {1, 1, 1, 1} },                  // เส้นนอน 4
    { {1}, {1}, {1}, {1} },            // เส้นตั้ง 4
    { {1, 1, 1}, {1, 1, 1}, {1, 1, 1} } // สี่เหลี่ยม 3x3
};

    
    private static  final Color[] COLORS={     //สีของบล็อก
        Color.RED, Color.BLUE, Color.GREEN,
        Color.ORANGE, Color.MAGENTA, Color.CYAN

    };
    private  Random random;
    private  List<Integer> bag;

    public PieceGenerator(){
        this.random=new  Random();
        this.bag=new ArrayList<>();
    }
    public  Piece generateRandom(){
        if (bag.isEmpty()) {          //เช็กว่าถุงว่างไหม
            refillBag();
        }
        int ShapeIndex=bag.remove(bag.size()-1);
        int Shape[][]=SHAPES[ShapeIndex];
        Color color=COLORS[random.nextInt(COLORS.length)];
       return new  Piece(Shape, color);

    }

    public  List<Piece> generateTray(int count){
          List<Piece>tray=new ArrayList<>();
          for(int i=0;i<count;i++){
            tray.add(generateRandom());
          }
          return  tray;
    }


       private void  refillBag(){
        bag.clear();                        //เครียร์บล็อกที่อยู่ในถุงเก่สให้หมด
        for(int i=0;i<SHAPES.length;i++){   //วนloopเเต่ล่ะรูปทรงเเล้วเก็บเข้าไปในกระเป๋า
            bag.add(i);                     //เก็บเข้าbag
        }
        Collections.shuffle(bag,random);    //สลับถุงในการสุ่ม
    }

} 