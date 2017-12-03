package com.music.utils.Observers;

import com.music.entity.GenreEntity;

public interface GenreObserver extends Observer {
    void updateGenre(GenreEntity genreEntity);
}
