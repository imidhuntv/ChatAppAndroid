package io.wonderkid.midhunchatbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.wonderkid.utils.CircleTransform;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.avatar)
    ImageView mAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Picasso.with(this)
                .load(R.drawable.bot)
                .fit()
                .centerCrop()
                .transform(new CircleTransform())
                .into(mAvatar);

    }

}
