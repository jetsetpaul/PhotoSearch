package jetsetpaul.photosearch;

import android.support.v4.app.Fragment;

/**
 * Created by pauljoiner on 9/29/16.
 */

public class MainActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment(){
        return PhotoSearchFragment.newInstance();
    }
}
