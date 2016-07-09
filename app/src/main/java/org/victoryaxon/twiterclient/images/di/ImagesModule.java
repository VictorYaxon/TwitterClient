package org.victoryaxon.twiterclient.images.di;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;
import dagger.Module;
import dagger.Provides;
import org.victoryaxon.twiterclient.api.CustomTwitterApiClient;
import org.victoryaxon.twiterclient.entities.Image;

import org.victoryaxon.twiterclient.images.*;
import org.victoryaxon.twiterclient.images.ui.ImagesView;
import org.victoryaxon.twiterclient.images.ui.adapters.ImagesAdapter;
import org.victoryaxon.twiterclient.images.ui.adapters.OnItemClickListener;
import org.victoryaxon.twiterclient.lib.base.EventBus;
import org.victoryaxon.twiterclient.lib.base.ImageLoader;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VictorYaxon on 29/06/16
 */
@Module
public class ImagesModule {
    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener){
        return new ImagesAdapter(dataset, imageLoader, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Image> providesItemsList(){
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(ImagesView view, EventBus eventBus, ImagesInteractor interactor){
        return new ImagesPresenterImpl(view, eventBus, interactor);
    }

    @Provides
    @Singleton
    ImagesView providesView(){
        return this.view;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository){
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImagesImagesRepositiry(EventBus eventBus, CustomTwitterApiClient client){
        return new ImagesRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session){
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwitter(){
        return Twitter.getSessionManager().getActiveSession();
    }
}
