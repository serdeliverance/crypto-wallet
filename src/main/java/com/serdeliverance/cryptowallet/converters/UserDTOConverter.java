package com.serdeliverance.cryptowallet.converters;

import com.serdeliverance.cryptowallet.domain.User;
import com.serdeliverance.cryptowallet.dto.UserDTO;

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
}
