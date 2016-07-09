package org.victoryaxon.twiterclient.lib.base;

/**
 * Created by VictorYaxon on 26/06/16
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
