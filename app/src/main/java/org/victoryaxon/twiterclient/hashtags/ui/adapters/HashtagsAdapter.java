package org.victoryaxon.twiterclient.hashtags.ui.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.victoryaxon.twiterclient.R;
import org.victoryaxon.twiterclient.entities.Hashtag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VictorYaxon on 30/06/16
 */
public class HashtagsAdapter extends RecyclerView.Adapter<HashtagsAdapter.ViewHolder> {

    private List<Hashtag> items;
    private OnItemClickListener clickListener;

    public HashtagsAdapter(
                           List<Hashtag> items,
                           OnItemClickListener clickListener) {
        this.items = items;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hashtag, parent, false);
        return new ViewHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hashtag tweet = items.get(position);
        holder.setClickListener(tweet, clickListener);
        holder.txtTweet.setText(tweet.getTweetText());
        holder.setItems(tweet.getHashtags());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Hashtag> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtTweet) TextView txtTweet;
        @Bind(R.id.recyclerView)  RecyclerView recyclerView;

        private View view;
        private ArrayList<String> items;
        private HashtagListAdapter adapter;

        public ViewHolder(Context context, View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;

            items = new ArrayList<String>();
            adapter = new HashtagListAdapter(items);
            CustomGridLayoutManager layoutManager = new CustomGridLayoutManager(context, 3);
            recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
            recyclerView.setAdapter(adapter);
            recyclerView.setHorizontalScrollBarEnabled(true);

        }

        public void setClickListener(final Hashtag tweet,
                                     final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(tweet);
                }
            });

        }

        public void setItems(List<String> newItems){
            items.clear();
            items.addAll(newItems);
            adapter.notifyDataSetChanged();
        }
    }
}