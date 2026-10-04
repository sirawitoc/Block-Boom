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
    private  Random random;     //เครื่องสุ่มของjava
    private  List<Integer> bag;

    public PieceGenerator(){       //สร้างถุงเปล่ามาเพื่อเก็บpiece
        this.random=new  Random();
        this.bag=new ArrayList<>();
    }
    public  Piece generateRandom(){
        if (bag.isEmpty()) {          //เช็กว่าถุงว่างไหม
            refillBag();
        }
        int ShapeIndex=bag.remove(bag.size()-1);            //หยิบตัวท้ายสุดออกจากถุง(เอาออกจริงๆ)
        int Shape[][]=SHAPES[ShapeIndex];                   //เอาเลขที่ได้มาไปเปิดมนshapesว่าได้รูปทรงอะไร
        Color color=COLORS[random.nextInt(COLORS.length)];    //สุ่มสีให้กับบล็อกนั้น
       return new  Piece(Shape, color);                       //ส่งรูปทรงของบล็อกเเละสีออกไป

    }

    public  List<Piece> generateTray(int count){       //สร้างlistเปล่าเเล้วใช้Generaterandom
          List<Piece>tray=new ArrayList<>();          //สร้างบล็อกเเต่ล่ะตัว 
          for(int i=0;i<count;i++){
            tray.add(generateRandom());
          }
          return  tray;
    }


       private void  refillBag(){
        bag.clear();                        //เครียร์บล็อกที่อยู่ในถุงเก่าให้หมด
        for(int i=0;i<SHAPES.length;i++){   //วนloopเเต่ล่ะรูปทรงเเล้วเก็บเข้าไปในกระเป๋า
            bag.add(i);                     //เก็บเข้าbag
        }
        Collections.shuffle(bag,random);    //สลับถุงในการสุ่ม
    }

} 