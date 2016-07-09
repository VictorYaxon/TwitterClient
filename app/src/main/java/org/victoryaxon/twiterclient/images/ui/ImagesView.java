package org.victoryaxon.twiterclient.images.ui;

import org.victoryaxon.twiterclient.entities.Image;

import java.util.List;

/**
 * Created by VictorYaxon on 29/06/16
 */
public interface ImagesView {
    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> items);
}
