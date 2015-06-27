package io.exziled.cartest2_2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

/**
 * Created by bcarlson on 6/14/15.
 */
public class AutoStatusBar extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.auto_status_layout, container, false);

        return view;
    }
}