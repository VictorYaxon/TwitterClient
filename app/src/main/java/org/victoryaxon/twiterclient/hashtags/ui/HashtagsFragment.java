package org.victoryaxon.twiterclient.hashtags.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.victoryaxon.twiterclient.R;
import org.victoryaxon.twiterclient.TwitterClientApp;
import org.victoryaxon.twiterclient.entities.Hashtag;
import org.victoryaxon.twiterclient.hashtags.HashtagsPresenter;
import org.victoryaxon.twiterclient.hashtags.di.HashtagsComponent;
import org.victoryaxon.twiterclient.hashtags.ui.adapters.HashtagsAdapter;
import org.victoryaxon.twiterclient.hashtags.ui.adapters.OnItemClickListener;

import javax.inject.Inject;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashtagsFragment extends Fragment implements HashtagsView, OnItemClickListener {

    @Inject
    HashtagsAdapter adapter;
    @Inject
    HashtagsPresenter hashtagsPresenter;

    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public void onResume() {
        super.onResume();
        hashtagsPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        hashtagsPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        hashtagsPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        setupInjection();
        setupRecyclerView();

        hashtagsPresenter.getHashtagTweets();
        return view;
    }

    private void setupInjection() {
        TwitterClientApp app = (TwitterClientApp)getActivity().getApplication();
        HashtagsComponent hashtagsComponent = app.getHashtagsComponent(this, this);

        hashtagsComponent.inject(this);
    }


    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onHashtagsError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setHashtags(List<Hashtag> items) {
        adapter.setItems(items);
    }

    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(Hashtag tweet) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweet.getTweetURL()));
        startActivity(intent);
    }
}