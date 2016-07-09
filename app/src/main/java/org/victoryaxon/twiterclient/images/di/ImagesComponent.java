package org.victoryaxon.twiterclient.images.di;

import dagger.Component;
import org.victoryaxon.twiterclient.images.ImagesPresenter;
import org.victoryaxon.twiterclient.images.ui.ImagesFragment;
import org.victoryaxon.twiterclient.lib.base.di.LibsModule;

import javax.inject.Singleton;

/**
 * Created by VictorYaxon on 29/06/16
 */
@Singleton
@Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);
    ImagesPresenter getPresenter();
}
