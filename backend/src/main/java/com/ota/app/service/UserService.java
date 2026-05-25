package com.ota.app.service;
import com.ota.app.model.User;
import com.ota.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    public User createUser(User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent())
            throw new RuntimeException("Email already in use");
        return userRepo.save(user);
    }

    public User updateUser(Long id, User user) {
        if (!userRepo.existsById(id)) return null;
        user.setId(id);
        return userRepo.save(user);
    }

    public boolean deleteUser(Long id) {
        if (!userRepo.existsById(id)) return false;
        userRepo.deleteById(id);
        return true;
    }
}