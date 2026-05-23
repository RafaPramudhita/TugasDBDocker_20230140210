package com.praktikumDB.deploy.service;


import com.praktikumDB.deploy.model.User;
import com.praktikumDB.deploy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) {
        // PERBAIKAN: Isi ID dengan NIM jika ID masih kosong
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(user.getNim());
        }
        userRepository.save(user);
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

    public User updateUser(String id, User request) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(request.getName());
            existingUser.setNim(request.getNim());
            return userRepository.save(existingUser);
        }
        return null;
    }
}