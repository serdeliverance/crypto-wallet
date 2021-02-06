package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.domain.User;
import com.serdeliverance.cryptowallet.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return new ArrayList<>();
    }
}
