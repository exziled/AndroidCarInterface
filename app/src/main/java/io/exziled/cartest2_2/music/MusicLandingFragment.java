package io.exziled.cartest2_2.music;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.exziled.cartest2_2.R;

/**
 * Created by Ben on 6/26/2015.
 */
public class MusicLandingFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_music_root, container, false);

        return view;
    }
}
