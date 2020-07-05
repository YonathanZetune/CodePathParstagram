package com.yonathanzetune.codepathparstagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.yonathanzetune.codepathparstagram.MainActivity.TAG;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;

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
        holder.bind(post);
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView usernameTv;
        private TextView descriptionTv;
        private ImageView mediaIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTv = itemView.findViewById(R.id.userNameTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            mediaIv = itemView.findViewById(R.id.mediaIv);

        }

        public void bind(Post post) {
            descriptionTv.setText(post.getDescription());
            usernameTv.setText(post.getUser().getUsername());
            if (post.getImage() != null) {
//                Log.i(TAG, "IMAGE: " + post.getImage());
                Glide.with(context).load(post.getImage().getUrl()).into(mediaIv);
            }
            else
                mediaIv.setVisibility(View.GONE);
        }
    }
}
