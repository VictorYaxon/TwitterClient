package org.victoryaxon.twiterclient.hashtags;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Tweet;
import org.victoryaxon.twiterclient.api.CustomTwitterApiClient;
import org.victoryaxon.twiterclient.entities.Hashtag;
import org.victoryaxon.twiterclient.hashtags.events.HashtagsEvent;
import org.victoryaxon.twiterclient.lib.base.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by VictorYaxon on 30/06/16
 */
public class HashtagsRepositoryImpl implements HashtagsRepository {
    private final EventBus eventBus;
    private final CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    public HashtagsRepositoryImpl(CustomTwitterApiClient client, EventBus eventBus) {
        this.client = client;
        this.eventBus = eventBus;
    }

    public void getHashtags(){
        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<Hashtag> items = new ArrayList<Hashtag>();
                        for (Tweet tweet : result.data) {

                            if (containsHashtags(tweet)) {
                                Hashtag tweetModel = new Hashtag();

                                tweetModel.setId(tweet.idStr);
                                tweetModel.setFavoriteCount(tweet.favoriteCount);
                                tweetModel.setTweetText(tweet.text);

                                List<String> hashtags = new ArrayList<String>();
                                for (HashtagEntity hashtag : tweet.entities.hashtags) {
                                    hashtags.add(hashtag.text);
                                }
                                tweetModel.setHashtags(hashtags);

                                items.add(tweetModel);
                            }
                        }
                        Collections.sort(items, new Comparator<Hashtag>() {
                            public int compare(Hashtag t1, Hashtag t2) {
                                return t2.getFavoriteCount() - t1.getFavoriteCount();
                            }
                        });
                        postEvent(items);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        postEvent(e.getMessage());
                    }
                }
        );
    }

    private boolean containsHashtags(Tweet tweet) {
        return  tweet.entities != null &&
                tweet.entities.hashtags != null &&
                !tweet.entities.hashtags.isEmpty();
    }

    private void postEvent(String error) {
        post(null, error);
    }

    private void postEvent(List<Hashtag> items) {
        post(items, null);
    }

    private void post(List<Hashtag> items, String error){
        HashtagsEvent event = new HashtagsEvent();
        event.setError(error);
        event.setHashtags(items);
        eventBus.post(event);
    }
}
