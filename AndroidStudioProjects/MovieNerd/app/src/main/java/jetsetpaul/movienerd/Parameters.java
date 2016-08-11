package jetsetpaul.movienerd;

import java.io.Serializable;

/**
 * Created by pauljoiner on 8/9/16.
 */
public class Parameters implements Serializable {
    public String genre;
    public String decade;
    public String streamingPlatform;
    public String score;

    public Parameters(String genre, String decade, String streamingPlatform, String score){
        this.genre = genre;
        this.decade = decade;
        this.streamingPlatform = streamingPlatform;
        this.score = score;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDecade(String decade) {
        this.decade = decade;
    }

    public void setStreamingPlatform(String streamingPlatform) {
        this.streamingPlatform = streamingPlatform;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getGenre() {
        return genre;
    }

    public String getDecade() {
        return decade;
    }

    public String getStreamingPlatform() {
        return streamingPlatform;
    }

    public String getScore() {
        return score;
    }
}
