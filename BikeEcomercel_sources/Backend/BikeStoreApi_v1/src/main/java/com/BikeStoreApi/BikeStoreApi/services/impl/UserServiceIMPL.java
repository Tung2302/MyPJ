package com.BikeStoreApi.BikeStoreApi.services.impl;

import com.BikeStoreApi.BikeStoreApi.dtos.UserDTO;
import com.BikeStoreApi.BikeStoreApi.entities.Role;
import com.BikeStoreApi.BikeStoreApi.entities.User;
import com.BikeStoreApi.BikeStoreApi.repositories.UserRepository;
import com.BikeStoreApi.BikeStoreApi.services.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${ra.jwt.secret}")
    private String Secret_key;

    @Override
    public User saveUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());
        user.setPhone(userDTO.getPhone());
        user.addRole(new Role(userDTO.getRoleId()));
        user.setDisable(userDTO.getDisable());
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllLatestUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void updateUser(User selectedUser) {
        userRepository.save(selectedUser);
    }

    @Override
    public boolean checkExistId(Integer id) {
        return userRepository.existsById(id);

    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        try {
            Optional<User> user = userRepository.findByEmail(email);
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Cant find user by this email");
        }
    }

    @Override
    public User registerUser(User user) {
        User registerUser = userRepository.save(user);
        return registerUser;
    }

    @Override
    public Optional<User> getByToken(String authorizationHeader) throws IOException {
        String token = authorizationHeader.substring("Bearer".length());
        System.out.println(token);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(Secret_key.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                System.out.println(username);
                return userRepository.findByEmail(username);
            } catch (Exception e) {
                throw new IOException("cant find user with token");
            }
        } else {
            return null;
        }
    }
}