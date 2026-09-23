import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardManager{
    private String FILE_NAME ="leaderboard.txt";
    private int MAX_ENTRIES = 10;
    

    public List<ScoreEntry> load(){
        return null;
        
    }
    public void addScore(String name , int score){
        
       
    }
    public  int getTopScore(){
       List<ScoreEntry> entries = load();
        if (entries.isEmpty()) {
            return 0;
        }
        return Collections.max(entries, Comparator.comparingInt(ScoreEntry::getScore)).getScore();
        
    }
    private void save(List<ScoreEntry> entries){

    }
   
}
