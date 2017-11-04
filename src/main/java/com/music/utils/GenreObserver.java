package com.music.utils;

import com.music.model.GenreEntity;

public interface GenreObserver extends Observer {
    void updateGenre(GenreEntity genreEntity);
}
