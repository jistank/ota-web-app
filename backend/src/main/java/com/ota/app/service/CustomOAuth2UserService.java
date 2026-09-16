/*
package com.ota.app.service;

import com.ota.app.model.User;
import com.ota.app.model.UserRole;
import com.ota.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final DefaultOAuth2UserService defaultService = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = defaultService.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        String firstName = oauth2User.getAttribute("given_name");
        String lastName = oauth2User.getAttribute("family_name");

        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setFirstName(firstName != null ? firstName : "Google");
            user.setLastName(lastName != null ? lastName : "User");
            user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
            user.setRole(UserRole.ROLE_CLIENT);
            userRepo.save(user);
        }

        Map<String, Object> attributes = oauth2User.getAttributes();

        return new DefaultOAuth2User(
            List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority(user.getRole().name())),
            attributes,
            "email"
        );
    }
}
*/
