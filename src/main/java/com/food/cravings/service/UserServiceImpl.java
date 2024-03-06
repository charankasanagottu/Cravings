package com.food.cravings.service;

import com.food.cravings.config.JwtProvider;
import com.food.cravings.model.User;
import com.food.cravings.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwtToken) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwtToken);
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new Exception("Could not find user");
        }
        else{
            return user;
        }

    }
}
