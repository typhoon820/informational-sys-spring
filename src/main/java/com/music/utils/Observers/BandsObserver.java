package com.music.utils.Observers;

import com.music.entity.BandEntity;

import java.util.List;

public interface BandsObserver extends Observer {
    void updateBands(List<BandEntity> bands);
}
