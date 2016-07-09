package org.victoryaxon.twiterclient.images;

/**
 * Created by VictorYaxon on 29/06/16
 */
public class ImagesInteractorImpl implements ImagesInteractor {
   private ImagesRepository repository;

    public ImagesInteractorImpl(ImagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getImages();
    }
}
