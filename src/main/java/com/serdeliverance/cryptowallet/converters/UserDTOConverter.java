package com.serdeliverance.cryptowallet.converters;

import com.serdeliverance.cryptowallet.domain.User;
import com.serdeliverance.cryptowallet.dto.CreateUserDTO;
import com.serdeliverance.cryptowallet.dto.UserDTO;

import java.util.Optional;

/**
 * Converter for UserDTO to User and viceversa
 */
public class UserDTOConverter {

    public static UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public static User convertToModel(CreateUserDTO createUserDTO) {
        return new User(
                Optional.empty(),
                createUserDTO.getUsername(),
                createUserDTO.getPassword(),
                createUserDTO.getEmail());
    }
}
