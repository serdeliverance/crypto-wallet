package com.serdeliverance.cryptowallet.api;

import com.serdeliverance.cryptowallet.converters.UserDTOConverter;
import com.serdeliverance.cryptowallet.dto.CreateUserDTO;
import com.serdeliverance.cryptowallet.dto.UpdateUserDTO;
import com.serdeliverance.cryptowallet.dto.UserDTO;
import com.serdeliverance.cryptowallet.exceptions.ResourceNotFoundException;
import com.serdeliverance.cryptowallet.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.serdeliverance.cryptowallet.converters.UserDTOConverter.convertToModel;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UserController {
    
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable("id") Integer id) {
        log.info("Getting user with id: {}", id);
        return userService
                .get(id)
                .map(UserDTOConverter::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("user:" + id));
    }

    @GetMapping
    public List<UserDTO> getAll() {
        log.info("Getting all users");
        return userService
                .getAll()
                .stream()
                .map(UserDTOConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateUserDTO user) {
        log.info("creating user...");
        userService.create(convertToModel(user));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody UpdateUserDTO updateUser) {
        log.info("updating user: {}", id);
        userService.update(convertToModel(id, updateUser));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        log.info("deleting user with userId: {}", id);
        userService.delete(id);
    }
}
