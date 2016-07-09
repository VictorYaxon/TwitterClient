package org.victoryaxon.twiterclient.hashtags.di;

import dagger.Component;
import org.victoryaxon.twiterclient.hashtags.ui.HashtagsFragment;
import org.victoryaxon.twiterclient.hashtags.HashtagsPresenter;
import org.victoryaxon.twiterclient.lib.base.di.LibsModule;

import javax.inject.Singleton;

/**
 * Created by VictorYaxon on 30/06/16
 */
@Singleton
@Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {
    void inject(HashtagsFragment fragment);
}
