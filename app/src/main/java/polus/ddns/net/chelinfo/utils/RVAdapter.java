package polus.ddns.net.chelinfo.utils;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import polus.ddns.net.chelinfo.R;
import polus.ddns.net.chelinfo.beans.NewsItem;

/**
 * Created by Игорь on 18.11.2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.EntryViewHolder> {
    static final String TAG = ConstantManager.TAG_PREFIX + "RVAdapter";

    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        static final String TAG = ConstantManager.TAG_PREFIX + "EntryViewHolder";
        CardView cardView;
        TextView cardName;
        TextView cardSize;
        TextView cardSeeders;
        TextView cardSeedersStart;
        TextView cardSizeStart;

        EntryViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "onCreateEntryViewHolder");
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            cardName = (TextView) itemView.findViewById(R.id.card_name);
            cardSize = (TextView) itemView.findViewById(R.id.card_size);
            cardSizeStart = (TextView) itemView.findViewById(R.id.card_size_startName);
            cardSeeders = (TextView) itemView.findViewById(R.id.card_seeders);
            cardSeedersStart = (TextView) itemView.findViewById(R.id.card_seeders_statName);
        }
    }

    List<NewsItem> newsItems;

    public RVAdapter(List<NewsItem> newsItems) {
        Log.d(TAG, "RVAdapteronCreate");
        this.newsItems = newsItems;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "onAttachedToRecyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        EntryViewHolder viewHolder = new EntryViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EntryViewHolder entryViewHolder, int i) {
        Log.d(TAG, "onBindViewHolder");
        entryViewHolder.cardName.setText(newsItems.get(i).getName());
        if (newsItems.get(i).getSeeders() != 0) {
            entryViewHolder.cardSeeders.setVisibility(View.VISIBLE);
            entryViewHolder.cardSeedersStart.setVisibility(View.VISIBLE);
            entryViewHolder.cardSeeders.setText(String.valueOf(newsItems.get(i).getSeeders()));
        }
        if (!newsItems.get(i).getSize().isEmpty()) {
            entryViewHolder.cardSize.setVisibility(View.VISIBLE);
            entryViewHolder.cardSizeStart.setVisibility(View.VISIBLE);
            entryViewHolder.cardSize.setText(newsItems.get(i).getSize());
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        return newsItems.size();
    }
}
