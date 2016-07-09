package org.victoryaxon.twiterclient.hashtags.di;

import android.content.Context;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;
import dagger.Module;
import dagger.Provides;
import org.victoryaxon.twiterclient.api.CustomTwitterApiClient;
import org.victoryaxon.twiterclient.entities.Hashtag;
import org.victoryaxon.twiterclient.hashtags.*;
import org.victoryaxon.twiterclient.hashtags.ui.HashtagsView;
import org.victoryaxon.twiterclient.hashtags.ui.adapters.HashtagsAdapter;
import org.victoryaxon.twiterclient.hashtags.ui.adapters.OnItemClickListener;
import org.victoryaxon.twiterclient.lib.base.EventBus;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VictorYaxon on 30/06/16
 */
@Module
public class HashtagsModule {
    private HashtagsView view;
    private OnItemClickListener clickListener;

    public HashtagsModule(HashtagsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    List<Hashtag> provideItems() {
        return new ArrayList<Hashtag>();
    }

    @Provides
    @Singleton
    OnItemClickListener provideClickListener() {
        return this.clickListener;
    }

    @Provides
    HashtagsAdapter provideAdapter(List<Hashtag> dataset, OnItemClickListener clickListener) {
        return new HashtagsAdapter(dataset, clickListener);
    }

    @Provides
    @Singleton
    HashtagsView provideHashtagsView() {
        return this.view;
    }

    @Provides
    @Singleton
    HashtagsPresenter provideHashtagsPresenter(HashtagsView view, HashtagsInteractor interactor, EventBus eventBus) {
        return new HashtagsPresenterImpl(view, interactor, eventBus);
    }

    @Provides
    @Singleton
    HashtagsInteractor provideHashtagsInteractor(HashtagsRepository repository) {
        return new HashtagsInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagsRepository provideHashtagsRepository(CustomTwitterApiClient client, EventBus eventBus) {
        return new HashtagsRepositoryImpl(client, eventBus);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient provideTwitterApiClient(TwitterSession session) {
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession provideTwitterSession() {
        return Twitter.getSessionManager().getActiveSession();
    }
}
