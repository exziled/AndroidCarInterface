package io.exziled.cartest2_2.music;

import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

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

    List<Artist> artists;

    ArtistAdapter(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_artist_item, viewGroup, false);
        ArtistViewHolder avh = new ArtistViewHolder(v);

        return avh;
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder artistViewHolder, int i) {
        artistViewHolder.artistName.setText(artists.get(i).mName);
//        artistViewHolder.artistImage.setImageDrawable(artists.get(i).getArtistDrawable());
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }
}
