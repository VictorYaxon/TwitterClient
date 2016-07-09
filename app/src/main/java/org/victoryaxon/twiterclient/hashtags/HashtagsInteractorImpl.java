package org.victoryaxon.twiterclient.hashtags;

/**
 * Created by VictorYaxon on 30/06/16
 */
public class HashtagsInteractorImpl implements HashtagsInteractor {
    private HashtagsRepository hashtagsRepository;

    public HashtagsInteractorImpl(HashtagsRepository hashtagsRepository) {
        this.hashtagsRepository = hashtagsRepository;
    }

    @Override
    public void getHashtagItemsList() {
        hashtagsRepository.getHashtags();
    }
}
