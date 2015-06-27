package io.exziled.cartest2_2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import io.exziled.cartest2_2.music.MusicLandingFragment;

/**
 * Created by bcarlson on 6/15/15.
 */
public class RootSelection extends Fragment {
    SVG [] aSVGObjs;
    ImageView []aImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_root_selection, container, false);

        aSVGObjs = new SVG[6];
        aImageView = new ImageView[6];

        aSVGObjs[0] = SVGParser.getSVGFromResource(getResources(), R.raw.sound16);
        aImageView[0] = (ImageView)view.findViewById(R.id.ivMusic);
        aImageView[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment musicFragment = new MusicLandingFragment();

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                                .replace(R.id.activeFragment, musicFragment)
                                .commit();
            }
        });

        aSVGObjs[1] = SVGParser.getSVGFromResource(getResources(), R.raw.earth26);
        aImageView[1] = (ImageView)view.findViewById(R.id.ivMaps);

        aSVGObjs[2] = SVGParser.getSVGFromResource(getResources(), R.raw.earth26);
        aImageView[2] = (ImageView)view.findViewById(R.id.ivPhone);

        aSVGObjs[3] = SVGParser.getSVGFromResource(getResources(), R.raw.camera119);
        aImageView[3] = (ImageView)view.findViewById(R.id.ivCamera);

        aSVGObjs[4] = SVGParser.getSVGFromResource(getResources(), R.raw.vehicle92);
        aImageView[4] = (ImageView)view.findViewById(R.id.ivControl);

        aSVGObjs[5] = SVGParser.getSVGFromResource(getResources(), R.raw.vehicle92);
        aImageView[5] = (ImageView)view.findViewById(R.id.ivSettings);


        for(int i = 0; i < 6; i++)
        {
            aImageView[i].setImageDrawable(aSVGObjs[i].createPictureDrawable());
            aImageView[i].setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            aImageView[i].setMinimumWidth(500);
            aImageView[i].setMinimumHeight(500);
            aImageView[i].setMaxHeight(500);
            aImageView[i].setMaxWidth(500);
//            aImageView[i].setPadding(50, 50, 50, 50);
        }

        return view;
    }
}