package com.example.tekushop.service;

import com.example.tekushop.common.PasswordUtil;
import com.example.tekushop.model.User;
import com.example.tekushop.repository.UserRepo;

public class UserLoginService {
    private UserRepo userRepository = new UserRepo();

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        System.out.println(user.getPassword());

        if (user != null) {
            String hashedPassword = PasswordUtil.hashPassword(password);
            System.out.println(hashedPassword);
            return hashedPassword.equals(user.getPassword());
        }
        return false;
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
