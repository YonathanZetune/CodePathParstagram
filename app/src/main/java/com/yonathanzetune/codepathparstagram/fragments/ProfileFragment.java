package com.yonathanzetune.codepathparstagram.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.yonathanzetune.codepathparstagram.LoginActivity;
import com.yonathanzetune.codepathparstagram.Post;
import com.yonathanzetune.codepathparstagram.R;

import java.util.List;

public class ProfileFragment extends PostsFragment {

    public static final String TAG = "ProfileFragment";

    private Button logoutButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logoutButton = view.findViewById(R.id.button);
        logoutButton.setVisibility(View.VISIBLE);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOutInBackground();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }

    @Override
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Parse Error:", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post:" + post.getDescription() + ", User: " + post.getUser().getUsername());

                }
                postList.addAll(posts);
                adapter.notifyDataSetChanged();

            }
        });
    }
}
