package com.food.cravings.service;

import com.food.cravings.model.User;

public interface UserService {
    public User findUserByJwtToken(String jwtToken) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
