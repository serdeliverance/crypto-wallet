package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public Optional<User> get(Integer id) {
        return Optional.empty();
    }

    public List<User> getAll() {
        return new ArrayList<>();
    }
}
