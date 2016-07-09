package org.victoryaxon.twiterclient.images.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import org.victoryaxon.twiterclient.entities.Image;
import org.victoryaxon.twiterclient.images.ImagesPresenter;
import org.victoryaxon.twiterclient.images.di.ImagesComponent;
import org.victoryaxon.twiterclient.images.ui.adapters.ImagesAdapter;
import org.victoryaxon.twiterclient.images.ui.adapters.OnItemClickListener;

import javax.inject.Inject;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment implements ImagesView, OnItemClickListener {


    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.container)
    FrameLayout container;

    @Inject
    ImagesPresenter presenter;
    @Inject
    ImagesAdapter adapter;

    public ImagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        seupInjection();
        setupRecyclerView();
        presenter.getImageTweets();
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }

    private void seupInjection() {
        TwitterClientApp app = (TwitterClientApp)getActivity().getApplication();
        ImagesComponent imagesComponent = app.getImagesComponent(this, this, this);
        //presenter = imagesComponent.getPresenter()
        imagesComponent.inject(this);
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void showImages() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImages() {
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
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<Image> items) {
        adapter.setItems(items);
    }

    @Override
    public void onItemClick(Image image) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getTweetUrl()));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
