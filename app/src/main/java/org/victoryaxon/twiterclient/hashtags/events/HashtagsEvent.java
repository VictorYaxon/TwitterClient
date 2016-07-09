package org.victoryaxon.twiterclient.hashtags.events;

import org.victoryaxon.twiterclient.entities.Hashtag;

import java.util.List;

/**
 * Created by VictorYaxon on 30/06/16
 */
public class HashtagsEvent {
    private String error;
    private List<Hashtag> hashtags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}