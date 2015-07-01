package io.exziled.cartest2_2.music;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import io.exziled.cartest2_2.R;

/**
 * Created by Ben on 6/28/2015.
 */
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {

        CardView artistCard;
        TextView artistName;
        ImageView artistImage;

        ArtistViewHolder(View itemView) {
            super(itemView);

            artistCard = (CardView)itemView.findViewById(R.id.cvArtist);
            artistName = (TextView)itemView.findViewById(R.id.tvAristName);
            artistImage = (ImageView)itemView.findViewById(R.id.ivArtistImages);

        }
    }

    private final ArrayList mArtistData;

    ArtistAdapter(TreeMap<String, Artist> artists) {
        mArtistData = new ArrayList();

        mArtistData.addAll(artists.entrySet());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_artist_item, viewGroup, false);
        return new ArtistViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder artistViewHolder, int i) {

        Map.Entry<String, Artist> artistObj = (Map.Entry)mArtistData.get(i);

        artistViewHolder.artistName.setText(artistObj.getValue().getName());
        Bitmap albumArt = artistObj.getValue().getSingleImage(0);
        if (albumArt != null)
        {
            artistViewHolder.artistImage.setImageDrawable(new BitmapDrawable(Resources.getSystem(), albumArt));
        }

    }

    @Override
    public int getItemCount() {
        return mArtistData.size();
    }
}
