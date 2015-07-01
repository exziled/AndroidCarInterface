package io.exziled.cartest2_2.music;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ben on 6/28/2015.
 */
public class Artist {
    final String TAG = "Artist";

    private String mName;
    private ArrayList<Bitmap> mAlbumArt;

    Artist(String name) {
        mName = name;
        mAlbumArt = new ArrayList<>();
    }

    Artist(String name, Bitmap albumArt) {
        mName = name;
        mAlbumArt.add(albumArt);
    }


    public void addArtwork(Bitmap albumArt) {
        mAlbumArt.add(albumArt);
    }

    public String getName() {return mName;}
    public Bitmap getSingleImage(int i) {
        if (mAlbumArt.size() <= i) {
            return null;
        }

        return mAlbumArt.get(i);
    }
}
