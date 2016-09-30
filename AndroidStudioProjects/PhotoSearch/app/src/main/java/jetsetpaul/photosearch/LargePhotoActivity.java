package jetsetpaul.photosearch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class LargePhotoActivity extends AppCompatActivity {
    ImageView mLargePhoto;
    TextView mCaption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_photo);
        mLargePhoto = (ImageView) findViewById(R.id.large_photo);
        mCaption = (TextView) findViewById(R.id.large_photo_caption);

        mCaption.setText(getIntent().getStringExtra("Caption"));
        Context context = mLargePhoto.getContext();
        Picasso.with(context).load(getIntent().getStringExtra("URL")).into(mLargePhoto);

    }
}
