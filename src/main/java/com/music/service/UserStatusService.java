package com.music.service;

import com.music.model.UserStatusEntity;

public interface UserStatusService {
    UserStatusEntity findByName(String name);
}
