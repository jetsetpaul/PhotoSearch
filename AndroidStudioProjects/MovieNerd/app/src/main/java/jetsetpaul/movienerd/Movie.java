package jetsetpaul.movienerd;

/**
 * Created by pauljoiner on 8/10/16.
 */
public class Movie {
    private String mTitle;
    private String mReleaseYear;
    private String mScore;
    private String mDescription;
    private String mStreamingPlatforms;
    private String mImage;

    public Movie(String mTitle, String mReleaseYear, String mScore){
        this.mTitle = mTitle;
        this.mReleaseYear = mReleaseYear;
        this.mScore = mScore;
        this.mDescription = mDescription;
        this.mStreamingPlatforms = mStreamingPlatforms;
        this.mImage = mImage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmReleaseYear() {
        return mReleaseYear;
    }

    public void setmReleaseYear(String mReleaseYear) {
        this.mReleaseYear = mReleaseYear;
    }

    public String getmScore() {
        return mScore;
    }

    public void setmScore(String mScore) {
        this.mScore = mScore;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmStreamingPlatforms() {
        return mStreamingPlatforms;
    }

    public void setmStreamingPlatforms(String mStreamingPlatforms) {
        this.mStreamingPlatforms = mStreamingPlatforms;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
