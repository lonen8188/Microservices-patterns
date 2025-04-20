package com.test.user_service.service;

import com.test.user_service.dto.ResponseDto;
import com.test.user_service.entity.User;

public interface UserService {
    User saveUser(User user);

    ResponseDto getUser(Long userId);
}
