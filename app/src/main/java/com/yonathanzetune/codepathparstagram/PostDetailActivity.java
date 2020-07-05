package com.yonathanzetune.codepathparstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

public class PostDetailActivity extends AppCompatActivity {

    public static final String POST_OBJECT_EXTRA = "POST_OBJECT_EXTRA";

    private TextView usernameTv;
    private TextView descriptionTv;
    private ImageView mediaIv;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        usernameTv = findViewById(R.id.userNameTv);
        descriptionTv = findViewById(R.id.descriptionTv);
        mediaIv = findViewById(R.id.mediaIv);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(POST_OBJECT_EXTRA));
        if (post != null) {
            usernameTv.setText(post.getUser().getUsername());
            descriptionTv.setText(post.getDescription());
            Glide.with(this).load(post.getImage().getUrl()).into(mediaIv);
        }


    }
}