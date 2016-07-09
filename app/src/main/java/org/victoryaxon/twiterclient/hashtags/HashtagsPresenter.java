package org.victoryaxon.twiterclient.hashtags;

import org.victoryaxon.twiterclient.hashtags.events.HashtagsEvent;

/**
 * Created by VictorYaxon on 30/06/16
 */
public interface HashtagsPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashtagsEvent event);
}
