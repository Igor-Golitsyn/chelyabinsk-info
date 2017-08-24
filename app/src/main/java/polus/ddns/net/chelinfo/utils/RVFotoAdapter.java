package polus.ddns.net.chelinfo.utils;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import polus.ddns.net.chelinfo.R;

/**
 * Created by golit on 24.08.2017.
 */

public class RVFotoAdapter extends RecyclerView.Adapter<RVFotoAdapter.EntryViewHolder>  {
    List<String> imageLink;
    Context context;

    public RVFotoAdapter(List<String> imageLink, Context context) {
        this.imageLink = imageLink;
        this.context=context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.entry_foto, viewGroup, false);
        EntryViewHolder viewHolder = new EntryViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int i) {
        imageLink.get(i);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width =displayMetrics.widthPixels;
        Picasso.with(context).load(imageLink.get(i)).resize(width,width).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageLink.size();
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        public EntryViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.foto_card_view);
            imageView = (ImageView) itemView.findViewById(R.id.image_card_view);
        }
    }
}
