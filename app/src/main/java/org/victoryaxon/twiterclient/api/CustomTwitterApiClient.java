package org.victoryaxon.twiterclient.api;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;

/**
 * Created by VictorYaxon on 25/06/16
 */
public class CustomTwitterApiClient extends TwitterApiClient {

    public CustomTwitterApiClient(Session session) {
        super(session);
    }

    public TimeLineService getTimelineService(){
        return getService(TimeLineService.class);
    }
}
