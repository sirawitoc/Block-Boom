import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LeaderboardManager {

    private  final String FILE_NAME = "leaderboard.csv";
    private  final int MAX_ENTRIES = 10; //จำนวนอันดับสูงสุดที่เก็บ

    public  List<ScoreEntry> load() { //โหลดคะแนนจากไฟล์
        Map<String, ScoreEntry> best = new LinkedHashMap<>();
        File f = new File(FILE_NAME);
        if (f.exists()) {
            try (BufferedReader r = new BufferedReader(new InputStreamReader(
                    new FileInputStream(f), StandardCharsets.UTF_8))) {
                String line;
                while ((line = r.readLine()) != null) {
                    int comma = line.lastIndexOf(',');
                    if (comma < 0)
                        continue;
                    String name = line.substring(0, comma).trim();
                    try {
                        int score = Integer.parseInt(line.substring(comma + 1).trim());
                        String key = name.toLowerCase();
                        ScoreEntry old = best.get(key);
                        if (old == null || score > old.getScore())
                            best.put(key, new ScoreEntry(name, score));
                    } catch (NumberFormatException ignored) {
                    }
                }
            } catch (IOException ignored) {
            }
        }
        List<ScoreEntry> entries = new ArrayList<>(best.values());//แปลงค่าใน Map เป็น List แล้วเรียงจากมากไปน้อยด้วย
        entries.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());
        return entries;
    }

    public  void addScore(String name, int score) { //เพิ่ม,อัปเดตคะแนน
        List<ScoreEntry> entries = load();
        boolean found = false;
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getName().equalsIgnoreCase(name)) {
                if (score > entries.get(i).getScore()) //คะแนนใหม่สูงกว่า → แทนที่รายการเดิม
                    entries.set(i, new ScoreEntry(name, score)); //คะแนนต่ำกว่า → ไม่ทำอะไร
                found = true;
                break;
            }
        }
        if (!found)
            entries.add(new ScoreEntry(name, score));

        entries.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());
        if (entries.size() > MAX_ENTRIES) {
            entries = new ArrayList<>(entries.subList(0, MAX_ENTRIES));
        }
        save(entries);
    }

    public  int getTopScore() { //คะแนนสูงสุด
        List<ScoreEntry> entries = load();
        if (entries.isEmpty()) {
            return 0;
        } else {
            return entries.get(0).getScore();
        }
    }

    public  void save(List<ScoreEntry> entries) { //บันทึกลงไฟล์
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FILE_NAME), StandardCharsets.UTF_8))) {
            for (ScoreEntry e : entries) {
                w.write(e.getName() + "," + e.getScore());
                w.newLine();
            }
        } catch (IOException ignored) {

        }

    }
}