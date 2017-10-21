package com.music.DAO;

import com.music.model.UserStatusEntity;
import com.music.service.UserService;

public interface UserStatusDAO {
    UserStatusEntity findByName(String name);
}
