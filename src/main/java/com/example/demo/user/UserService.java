package com.example.demo.user;

import java.util.List;

public class UserService {
    private static final List<User> users = User.getSampleUsers();


    public List<User> getUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }
}

