package com.tonystark.apirest.services;

import com.tonystark.apirest.dto.UserDTO;
import com.tonystark.apirest.entities.Usuario;
import com.tonystark.apirest.exceptions.ResourceNotFoundException;
import com.tonystark.apirest.repositories.UserRepository;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UsuarioService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO usuarioDTO) {

        //De json a pojo
        Usuario usuario = convertirAEntidad(usuarioDTO);

        //De pojo a Json
        Usuario nuevoUsuario = userRepository.save(usuario);

        //Crear objeto DTO
        UserDTO userDtoResponse = convertirADto(nuevoUsuario);

        return userDtoResponse;

    }

    @Override
    public List<UserDTO> getUsers(int nPage, int pageSize) {
        //Pageable
        Pageable objetoPaginable = PageRequest.of(nPage, pageSize);
        //Page
        Page<Usuario> listaUsuarios = userRepository.findAll(objetoPaginable);

        List<Usuario> users = listaUsuarios.getContent();
        return users.
                stream().
                map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(long id) {
        Usuario usuarioById = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id",id));

        return convertirADto(usuarioById);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, long id) {
        Usuario usuario = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
        usuario.setName(userDTO.getName());
        usuario.setLastName(userDTO.getLastname());
        usuario.setAge(userDTO.getAge());
        usuario.setPassword(userDTO.getPassword());

        Usuario usuarioActualizado = userRepository.save(usuario);

        return convertirADto(usuarioActualizado);
    }

    @Override
    public void deleteUser(long id) {
        //Find User
        Usuario user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", id));

        userRepository.delete(user);
    }

    //Retornar dto
    private UserDTO convertirADto(Usuario usuario){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(usuario.getId());
        userDTO.setName(usuario.getName());
        userDTO.setLastname(usuario.getLastName());
        userDTO.setAge(usuario.getAge());
        userDTO.setPassword(usuario.getPassword());
        return userDTO;

    }

    //Retonar entidad
    private Usuario convertirAEntidad(UserDTO userDTO){
        Usuario usuario = new Usuario();
        usuario.setId(userDTO.getId());
        usuario.setName(userDTO.getName());
        usuario.setLastName(userDTO.getLastname());
        usuario.setAge(userDTO.getAge());
        usuario.setPassword(userDTO.getPassword());

        return usuario;
    }

}
