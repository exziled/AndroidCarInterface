package io.exziled.cartest2_2.music;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.exziled.cartest2_2.R;

/**
 * Created by Ben on 6/26/2015.
 */
public class ArtistBrowseFragment extends Fragment {

    private List<Artist> mArtists;
    private RecyclerView mRecycler;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_artist_browse, container, false);

        mRecycler = (RecyclerView)view.findViewById(R.id.rvArtistName);

        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        mRecycler.setLayoutManager(llm);
        mRecycler.setHasFixedSize(true);

        initializeArtistList();
        initializeAdapter();

        return view;
    }


    private void initializeArtistList() {
        mArtists = new ArrayList<>();

        for (int i = 0; i < 20; i++)
        {
            mArtists.add(new Artist("Test - "));
        }

        mArtists.add(new Artist("Grimes"));
        mArtists.add(new Artist("Purity Ring"));
    }

    private void initializeAdapter() {
        ArtistAdapter artistAdapter = new ArtistAdapter(mArtists);
        mRecycler.setAdapter(artistAdapter);
    }

}



