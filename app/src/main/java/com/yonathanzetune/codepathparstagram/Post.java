package com.yonathanzetune.codepathparstagram;

import android.text.format.DateUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER_IMAGE = "profileImage";
    public static final String KEY_USER = "user";
    public static final String CREATED_AT = "createdAt";
    public static final String KEY_LIKES = "likes";

    public Post() {

    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public String getCreatedAtTime() {
        return getRelativeTimeAgo(getString(CREATED_AT));
    }

    public void setDescription(String descrip) {
        put(KEY_DESCRIPTION, descrip);
    }


    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image) {
        put(KEY_IMAGE, image);
    }

    public String getProfImage() {
        if (this.getUser().getParseFile(KEY_USER_IMAGE) != null)
            return this.getUser().getParseFile(KEY_USER_IMAGE).getUrl();
        return "";
    }

    public void setProfImage(ParseFile image) {
//        put(KEY_IMAGE, image);
    }

    public int getPostLikes() {
        return getInt(KEY_LIKES);
    }

    public void incrPostLikes() {

        put(KEY_LIKES, getInt(KEY_LIKES) + 1);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);
//2020-06-30T21:09:01.707Z
        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

    @Nullable
    @Override
    public String getString(@NonNull String key) {
        return super.getString(key);
    }
}
