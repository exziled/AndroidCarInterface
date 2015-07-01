package io.exziled.cartest2_2.music;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import io.exziled.cartest2_2.R;

/**
 * Created by Ben on 6/26/2015.
 */
public class ArtistBrowseFragment extends Fragment {
    final String TAG = "ArtistBrowseFragment";

    //private List<Artist> mArtists;
    private TreeMap<String, Artist> mArtists;
    private List<String> mLetters;

    private RecyclerView mRecycler;
    private ListView mLettersList;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_artist_browse, container, false);

        mRecycler = (RecyclerView)view.findViewById(R.id.rvArtistName);
        mLettersList = (ListView)view.findViewById(R.id.lvAristLetter);

        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        mRecycler.setLayoutManager(llm);
        mRecycler.setHasFixedSize(true);

        initializeArtistList(view.getContext());

        final LetterArrayAdapter letterAdapter = new LetterArrayAdapter(view.getContext(), mLetters);
        mLettersList.setAdapter(letterAdapter);

        initializeAdapters();
        return view;
    }


    private void initializeArtistList(Context appContext) {
        //mArtists = new ArrayList<>();
        mArtists = new TreeMap<>();
        mLetters = new ArrayList<>();

        ContentResolver artistResolver = appContext.getContentResolver();
        Uri artistURI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String orderBy = MediaStore.Audio.Artists.ARTIST;
        Cursor curArtist = artistResolver.query(artistURI, null, null, null, orderBy + " ASC");

        if (curArtist == null) {
            Log.e(TAG, "Failed to retrieve music cursor");
            return;
        }

        if (!curArtist.moveToFirst()){
            Log.e(TAG, "Failed to move cursor to first row of results");
            return;
        }

        int cArtist = curArtist.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int cArtistID = curArtist.getColumnIndex(MediaStore.Audio.Media.ARTIST_KEY);
        int cAlbumId = curArtist.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);

        Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

        do {
            Bitmap bmAlbumArt = null;

            if (cAlbumId != -1) {

                Long albumId = curArtist.getLong(cAlbumId);
                Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri, albumId);

                try {
                    bmAlbumArt = MediaStore.Images.Media.getBitmap(appContext.getContentResolver(), albumArtUri);
                    bmAlbumArt = Bitmap.createScaledBitmap(bmAlbumArt, 150, 150, true);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            if (!mArtists.containsKey(curArtist.getString(cArtistID)))
            {
                Artist addArtist = new Artist (curArtist.getString(cArtist));

                if (bmAlbumArt != null)
                {
                    addArtist.addArtwork(bmAlbumArt);
                }

                mArtists.put(curArtist.getString(cArtistID), addArtist);
            } else {
                Artist artist = mArtists.get(curArtist.getString(cArtistID));

                if (bmAlbumArt != null)
                {
                    artist.addArtwork(bmAlbumArt);
                }
            }

            // Populate Letter List
            String firstLetter = curArtist.getString(cArtist).substring(0, 1);
            if (!mLetters.contains(firstLetter))
            {
                mLetters.add(curArtist.getString(cArtist).substring(0, 1));
            }

        } while(curArtist.moveToNext());

        Log.i(TAG, "Finished querying media");
    }

    private void initializeAdapters() {
        ArtistAdapter artistAdapter = new ArtistAdapter(mArtists);
        mRecycler.setAdapter(artistAdapter);
    }


    private class LetterArrayAdapter extends ArrayAdapter<String> {
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public LetterArrayAdapter(Context context, List<String> objects) {
            super(context, 0, objects);

            for (int i = 0; i < objects.size(); ++i)
            {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null)
            {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_artist_lettter, parent, false);
            }

            TextView tvLetter = (TextView)convertView.findViewById(R.id.tvArtistLetter);

            tvLetter.setText(getItem(position));

            return convertView;
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }

}



