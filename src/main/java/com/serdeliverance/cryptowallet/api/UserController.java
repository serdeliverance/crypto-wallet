package com.serdeliverance.cryptowallet.api;

import com.serdeliverance.cryptowallet.converters.UserDTOConverter;
import com.serdeliverance.cryptowallet.dto.CreateUserDTO;
import com.serdeliverance.cryptowallet.dto.UpdateUserDTO;
import com.serdeliverance.cryptowallet.dto.UserDTO;
import com.serdeliverance.cryptowallet.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.stream.Collectors;

import static com.serdeliverance.cryptowallet.converters.UserDTOConverter.*;
import static com.serdeliverance.cryptowallet.converters.UserDTOConverter.convertToModel;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateUserDTO user) {
        LOGGER.info("creating user...");
        userService.create(convertToModel(user));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody UpdateUserDTO updateUser) {
        LOGGER.info("updating user: {}", id);
        userService.update(convertToModel(id, updateUser));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        LOGGER.info("deleting user with userId: {}", id);
        userService.delete(id);
    }
}
