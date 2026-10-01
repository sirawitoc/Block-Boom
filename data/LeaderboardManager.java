import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LeaderboardManager {

    private static final String FILE_NAME = "leaderboard.csv";
    private static final int MAX_ENTRIES = 10;

    public static List<ScoreEntry> load() {
        return load();
    }

    public static void addScore(String name, int score) {
        List<ScoreEntry> entries = load();
        boolean found = false;
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getName().equalsIgnoreCase(name)) {
                if (score > entries.get(i).getScore())
                    entries.set(i, new ScoreEntry(name, score));
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

    public static int getTopScore() {
        List<ScoreEntry> entries = load();
        if (entries.isEmpty()) {
            return 0;
        } else {
            return entries.get(0).getScore();
        }
    }

    public static void save(List<ScoreEntry> entries) {
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