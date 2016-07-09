package org.victoryaxon.twiterclient;

import android.app.Application;
import android.support.v4.app.Fragment;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import org.victoryaxon.twiterclient.hashtags.di.DaggerHashtagsComponent;
import org.victoryaxon.twiterclient.hashtags.di.HashtagsComponent;
import org.victoryaxon.twiterclient.hashtags.di.HashtagsModule;
import org.victoryaxon.twiterclient.hashtags.ui.HashtagsView;
import org.victoryaxon.twiterclient.images.di.DaggerImagesComponent;
import org.victoryaxon.twiterclient.images.di.ImagesComponent;
import org.victoryaxon.twiterclient.images.di.ImagesModule;
import org.victoryaxon.twiterclient.images.ui.ImagesView;
import org.victoryaxon.twiterclient.lib.base.di.LibsModule;

import io.fabric.sdk.android.Fabric;


/**
 * Created by VictorYaxon on 25/06/16
 */
public class TwitterClientApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, org.victoryaxon.twiterclient.images.ui.adapters.OnItemClickListener clickListener){
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, clickListener))
                .build();
    }

    public HashtagsComponent getHashtagsComponent(HashtagsView view, org.victoryaxon.twiterclient.hashtags.ui.adapters.OnItemClickListener clickListener){
        return DaggerHashtagsComponent
                .builder()
                .libsModule(new LibsModule(null))
                .hashtagsModule(new HashtagsModule(view, clickListener))
                .build();
    }
}
