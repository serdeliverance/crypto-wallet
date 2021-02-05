package com.serdeliverance.cryptowallet.api;

import com.serdeliverance.cryptowallet.converters.UserDTOConverter;
import com.serdeliverance.cryptowallet.dto.UserDTO;
import com.serdeliverance.cryptowallet.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable("id") Integer id) {
        LOGGER.info("Getting user with id: {}", id);
        return userService
                .get(id)
                .map(UserDTOConverter::convertToDTO)
                .orElseThrow(() -> new ResourceAccessException("user:" + id));
    }

    @GetMapping
    public List<UserDTO> getAll() {
        LOGGER.info("Getting all users");
        return userService
                .getAll()
                .stream()
                .map(UserDTOConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
