package com.tonystark.apirest.services;

import com.tonystark.apirest.dto.UserDTO;
import org.apache.catalina.User;

import java.util.List;

public interface UsuarioService {
    public UserDTO createUser(UserDTO usuarioDTO);

    public List<UserDTO> getUsers(int nPage, int pageSize);

    public UserDTO getUserById(long id);

    //Update user
    public UserDTO updateUser(UserDTO userDTO, long id);

    public void deleteUser(long id);

}
