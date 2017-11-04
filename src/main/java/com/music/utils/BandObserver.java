package com.music.utils;

import com.music.model.BandEntity;

public interface BandObserver extends Observer {

    void updateBand(BandEntity bandEntity);
}
