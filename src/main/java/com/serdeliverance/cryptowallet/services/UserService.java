package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.domain.User;
import com.serdeliverance.cryptowallet.exceptions.ResourceNotFoundException;
import com.serdeliverance.cryptowallet.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> get(Integer id) {
        log.info("Getting user with id: {}", id);
        return userRepository.find(id);
    }

    public List<User> getAll() {
        log.info("Getting all users");
        return userRepository.getAll();
    }

    public void create(User user) {
        log.info("Creating user");
        userRepository.save(user);
    }

    public void update(User user) {
        Integer userId = user.getId().get();
        if (this.exists(userId)) {
            userRepository.update(user);
        } else throw new ResourceNotFoundException("user: " + userId);
    }

    public void delete(Integer userId) {
        if (this.exists(userId)) {
            userRepository.delete(userId);
        } else throw new ResourceNotFoundException("user: " + userId);
    }

    public void validateUser(Integer userId) {
        log.info("Validating user {}", userId);
        get(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    private boolean exists(Integer id) {
        return this.get(id).isPresent();
    }
}
