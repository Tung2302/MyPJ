package com.BikeStoreApi.BikeStoreApi.services;

import com.BikeStoreApi.BikeStoreApi.dtos.UserDTO;
import com.BikeStoreApi.BikeStoreApi.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(UserDTO userDTO);
    public List<User> getAllLatestUsers();
    public List<User> getAllUsers();
    public Optional<User> getUserById(Integer id);
    public void updateUser(User selectedUser);
    public boolean checkExistId(Integer id);
    public void deleteUser(Integer id);
    public Optional<User> findUserByEmail(String email);
    public User registerUser(User user);
    Optional<User> getByToken(String authorizationHeader) throws Exception;
}
