package com.music.utils.Observers;

import com.music.entity.ArtistEntity;

public interface ArtistObserver extends Observer {
    void updateArtist(ArtistEntity artistEntity);
}
