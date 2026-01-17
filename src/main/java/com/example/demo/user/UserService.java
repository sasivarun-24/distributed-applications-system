package com.example.demo.user;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
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

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public String getUserId() {
        return "123456789";
    }
}
