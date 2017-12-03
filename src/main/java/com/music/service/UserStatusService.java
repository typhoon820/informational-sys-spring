package com.music.service;

import com.music.entity.UserStatusEntity;

public interface UserStatusService {
    UserStatusEntity findByName(String name);
}
