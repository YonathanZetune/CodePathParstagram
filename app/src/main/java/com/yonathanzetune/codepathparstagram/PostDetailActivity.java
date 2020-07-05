package com.yonathanzetune.codepathparstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class PostDetailActivity extends AppCompatActivity {

    public static final String POST_OBJECT_EXTRA = "POST_OBJECT_EXTRA";

    private TextView usernameTv;
    private TextView descriptionTv;
    private TextView timestampTv;
    private TextView numLikesTv;
    private ImageView mediaIv;
    private ImageView profileIv;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        usernameTv = findViewById(R.id.userNameTv);
        descriptionTv = findViewById(R.id.descriptionTv);
        numLikesTv = findViewById(R.id.numLikesTv);
        mediaIv = findViewById(R.id.mediaIv);
        profileIv = findViewById(R.id.profileIv);
        timestampTv = findViewById(R.id.createdAtTv);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(POST_OBJECT_EXTRA));
        if (post != null) {
            descriptionTv.setText(post.getDescription());
            usernameTv.setText(post.getUser().getUsername());
            numLikesTv.setText(String.format("%s Likes", post.getPostLikes()));
            timestampTv.setText(post.getCreatedAt().toString());
            if (!post.getProfImage().isEmpty()) {

                Glide.with(this).load(post.getProfImage()).transform(new CircleCrop()).into(profileIv);
            }
            if (post.getImage() != null) {
//                Log.i(TAG, "IMAGE: " + post.getImage());
                Glide.with(this).load(post.getImage().getUrl()).transform(new RoundedCornersTransformation(30, 15)).into(mediaIv);
            } else
                mediaIv.setVisibility(View.GONE);
        }


    }
}