package com.yonathanzetune.codepathparstagram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import org.parceler.Parcels;

import java.lang.annotation.Target;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.yonathanzetune.codepathparstagram.MainActivity.TAG;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private Boolean isProfile;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts, Boolean isProfile) {
        this.context = context;
        this.posts = posts;
        this.isProfile = isProfile;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post, isProfile);
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView usernameTv;
        private TextView descriptionTv;
        private TextView timestampTv;
        private TextView numLikesTv;
        private ImageView mediaIv;
        private ImageView likesIv;
        private ImageView profileIv;
        private LinearLayout bottomBar;
        private LinearLayout topbar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTv = itemView.findViewById(R.id.userNameTv);
            bottomBar = itemView.findViewById(R.id.postBottomBar);
            topbar = itemView.findViewById(R.id.postTopBar);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            numLikesTv = itemView.findViewById(R.id.numLikesTv);
            mediaIv = itemView.findViewById(R.id.mediaIv);
            profileIv = itemView.findViewById(R.id.profileIv);
            likesIv = itemView.findViewById(R.id.favoriteButton);
            timestampTv = itemView.findViewById(R.id.createdAtTv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, PostDetailActivity.class);

                    Post post = posts.get(getAdapterPosition());
                    i.putExtra(PostDetailActivity.POST_OBJECT_EXTRA, Parcels.wrap(post));
                    context.startActivity(i);
                }
            });


        }

        public void bind(final Post post, Boolean isProf) {
            descriptionTv.setText(post.getDescription());
            usernameTv.setText(post.getUser().getUsername());
            numLikesTv.setText(String.format("%s Likes", post.getPostLikes()));
            timestampTv.setText(post.getCreatedAt().toString());

            likesIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    post.incrPostLikes();
                    likesIv.setImageDrawable(context.getDrawable(R.drawable.ufi_heart_active));
                    numLikesTv.setText(String.format("%s Likes", post.getPostLikes()));
                }
            });
            profileIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, ProfileActivity.class);
                    i.putExtra(ProfileActivity.PROFILE_EXTRA_USER, Parcels.wrap(post.getUser()));
                    context.startActivity(i);
                }
            });
            if (!post.getProfImage().isEmpty()) {

                Glide.with(context).load(post.getProfImage()).transform(new CircleCrop()).into(profileIv);
            }
            if (post.getImage() != null) {
//                Log.i(TAG, "IMAGE: " + post.getImage());
                if (isProf) {
                    mediaIv.getLayoutParams().height = 500;
                    mediaIv.getLayoutParams().width = 500;
                    mediaIv.requestLayout();

                    Glide.with(context).load(post.getImage().getUrl()).into(mediaIv);
                } else
                    Glide.with(context).load(post.getImage().getUrl()).transform(new RoundedCornersTransformation(30, 15)).into(mediaIv);
            } else
                mediaIv.setVisibility(View.GONE);
            if (isProf) {
                descriptionTv.setVisibility(View.GONE);
                usernameTv.setVisibility(View.GONE);
                numLikesTv.setVisibility(View.GONE);
                timestampTv.setVisibility(View.GONE);
                bottomBar.setVisibility(View.GONE);
                topbar.setVisibility(View.GONE);
            }
        }


    }
}
