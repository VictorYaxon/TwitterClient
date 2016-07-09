package org.victoryaxon.twiterclient.hashtags.ui;

import org.victoryaxon.twiterclient.entities.Hashtag;

import java.util.List;

/**
 * Created by VictorYaxon on 30/06/16
 */
public interface HashtagsView {

    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void onHashtagsError(String error);
    void setHashtags(List<Hashtag> items);
}
