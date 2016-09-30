package jetsetpaul.photosearch;

/**
 * Created by pauljoiner on 9/29/16.
 */

public class Photo {
    private String mCaption;
    private String mId;
    private String mURL;
    private String mLargeURL;

    public String getmLargeURL() {
        return mLargeURL;
    }

    public void setmLargeURL(String mLargeURL) {
        this.mLargeURL = mLargeURL;
    }

    @Override
    public String toString() {
        return mCaption;
    }

    public String getmCaption() {
        return mCaption;
    }

    public void setmCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmURL() {
        return mURL;
    }

    public void setmURL(String mURL) {
        this.mURL = mURL;
    }
}
