package day08.playstore;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PlaystoreStats {

    private String category;
    private float highestRating = -1;
    private String highestRatedApp;
    private float lowestRating = 6;
    private String lowestRatedApp;
    private List<Float> allRatings = new LinkedList<>();

    public PlaystoreStats(String category) { this.category = category; }

    public void compute(PlaystoreEntry entry) {
        allRatings.add(entry.rating());
        if (entry.rating() < getLowestRating()) {
            setLowestRating(entry.rating());
            setLowestRatedApp(entry.appName());
        }
        if (entry.rating() > getHighestRating()) {
            setHighestRating(entry.rating());
            setHighestRatedApp(entry.appName());
        }
    }

    public Float averageRating() {
        return allRatings.stream().reduce(0f, (acc, v) -> acc + v) / allRatings.size();
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public float getHighestRating() { return highestRating; }
    public void setHighestRating(float highestRating) { this.highestRating = highestRating; }

    public String getHighestRatedApp() { return highestRatedApp; }
    public void setHighestRatedApp(String highestRatedApp) { this.highestRatedApp = highestRatedApp; }

    public float getLowestRating() { return lowestRating; }
    public void setLowestRating(float lowestRating) { this.lowestRating = lowestRating; }

    public String getLowestRatedApp() { return lowestRatedApp; }
    public void setLowestRatedApp(String lowestRatedApp) { this.lowestRatedApp = lowestRatedApp; }

    public List<Float> getAllRatings() { return allRatings; }
    public void setAllRatings(List<Float> allRatings) { this.allRatings = allRatings; }
    
}
