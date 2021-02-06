package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.domain.User;
import com.serdeliverance.cryptowallet.exceptions.ResourceNotFoundException;
import com.serdeliverance.cryptowallet.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> get(Integer id) {
        return userRepository.find(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        boolean exists = user.getId()
                .flatMap(id -> this.get(id))
                .isPresent();
        if (exists) {
            userRepository.update(user);
        } else throw new ResourceNotFoundException("user: " + user.getId().get());


    }

    private boolean exists(Integer userId) {
        return this.get(userId).isPresent();
    }
}
