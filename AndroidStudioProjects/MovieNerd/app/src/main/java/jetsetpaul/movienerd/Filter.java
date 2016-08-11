package jetsetpaul.movienerd;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pauljoiner on 8/6/16.
 */
public class Filter implements Serializable {
    public String mCategory;
    public ArrayList<String> mSubFilters;

    public Filter(String mCategory, ArrayList mSubFilters){
        this.mCategory = mCategory;
        this.mSubFilters = mSubFilters;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public ArrayList<String> getmSubFilters() {
        return mSubFilters;
    }

    public void setmSubFilters(ArrayList<String> mSubFilters) {
        this.mSubFilters = mSubFilters;
    }
}
