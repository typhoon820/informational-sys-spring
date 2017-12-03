package com.music.DAO;

import com.music.entity.UserStatusEntity;

public interface UserStatusDAO {
    UserStatusEntity findByName(String name);
}
