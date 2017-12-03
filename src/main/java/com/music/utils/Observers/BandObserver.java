package com.music.utils.Observers;

import com.music.entity.BandEntity;

public interface BandObserver extends Observer {

    void updateBand(BandEntity bandEntity);
}
