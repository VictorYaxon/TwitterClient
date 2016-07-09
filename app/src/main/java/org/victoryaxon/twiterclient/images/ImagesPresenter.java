package org.victoryaxon.twiterclient.images;

import org.victoryaxon.twiterclient.images.events.ImagesEvent;

/**
 * Created by VictorYaxon on 29/06/16
 */
public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onEventMainThread(ImagesEvent event);
}
