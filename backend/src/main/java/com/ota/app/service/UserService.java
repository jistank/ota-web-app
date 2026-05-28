package com.ota.app.service;
import com.ota.app.model.User;
import com.ota.app.model.UserPrincipal;
import com.ota.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    


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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		if (user == null) throw new UsernameNotFoundException("User not found");
		return new UserPrincipal(user);
	}
}













