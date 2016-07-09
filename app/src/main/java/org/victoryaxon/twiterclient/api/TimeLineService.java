package org.victoryaxon.twiterclient.api;

import com.twitter.sdk.android.core.models.Tweet;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.List;

/**
 * Created by VictorYaxon on 25/06/16
 */
public interface TimeLineService {
    @GET("/1.1/statuses/home_timeline.json")
    void homeTimeline(@Query("count") Integer count,
                      @Query("trim_user") Boolean trim_user,
                      @Query("exclude_replies") Boolean exclude_replies,
                      @Query("contributor_details") Boolean contributor_details,
                      @Query("include_entities") Boolean include_entities,
                      Callback<List<Tweet>> callback);
}
