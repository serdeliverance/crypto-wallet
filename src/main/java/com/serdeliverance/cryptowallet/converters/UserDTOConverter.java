/* (C)2022 */
package com.serdeliverance.cryptowallet.converters;

import com.serdeliverance.cryptowallet.domain.User;
import com.serdeliverance.cryptowallet.dto.CreateUserDTO;
import com.serdeliverance.cryptowallet.dto.UpdateUserDTO;
import com.serdeliverance.cryptowallet.dto.UserDTO;
import java.util.Optional;

/** Converter for UserDTO to User and viceversa */
public class UserDTOConverter {

  public static UserDTO convertToDTO(User user) {
    return new UserDTO(user.id(), user.username(), user.email());
  }

  public static User convertToModel(CreateUserDTO createUserDTO) {
    return new User(
        Optional.empty(),
        createUserDTO.username(),
        createUserDTO.password(),
        createUserDTO.email());
  }

  public static User convertToModel(Integer id, UpdateUserDTO updateUserDTO) {
    return new User(
        Optional.of(id), updateUserDTO.username(), updateUserDTO.password(), updateUserDTO.email());
  }
}
