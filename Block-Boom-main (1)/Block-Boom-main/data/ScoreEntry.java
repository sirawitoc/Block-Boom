
package data;

public class ScoreEntry  {//แม่แบบเก็บข้อมูลคะแนนของผู้เล่น 1 รายการ (ชื่อ + คะแนน)
    private String name;
    private int score;

      public ScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }
 
    public String getName() {
        return name;
    }
 
    public int getScore() {
        return score;
    }
 
    public String toString() {
        return name + " - " + score;

}}
