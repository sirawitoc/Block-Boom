package model;
import java.awt.Color;
import  java.util.Random;
import  java.util.List;
import  java.util.ArrayList;

public  class PieceGenerator {

    private  int[][] SHAPES;
    private  Color color;

    public int[][] getCells(){
       return  SHAPES;
    }
    public void  getColor(){

    }
    public  int getHeight(){
        return 2;
    }
    public  int getWidth(){
       return  2;
    }

    public  int size(){
        return 2;
    }
} 