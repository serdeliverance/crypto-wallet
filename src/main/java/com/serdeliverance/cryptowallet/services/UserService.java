package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.domain.User;
import com.serdeliverance.cryptowallet.exceptions.ResourceNotFoundException;
import com.serdeliverance.cryptowallet.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

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

    private boolean exists(Integer id) {
        return this.get(id).isPresent();
    }
}
