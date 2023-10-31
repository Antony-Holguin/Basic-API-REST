package com.tonystark.apirest.controllers;

import com.tonystark.apirest.dto.UserDTO;
import com.tonystark.apirest.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    //Inyectar servicio
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("usuarios")
    public List<UserDTO> getAllUsers(@RequestParam(value = "nPage", defaultValue = "0", required = false) int nPage,
                                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return usuarioService.getUsers(nPage, pageSize);
    }

    @GetMapping("usuario/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id){
        return new ResponseEntity<>(usuarioService.getUserById(id), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDto){
        return new ResponseEntity<>(usuarioService.createUser(userDto), HttpStatus.CREATED);
    }



    @PutMapping("usuario/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable long id){
        return new ResponseEntity<>(usuarioService.updateUser(userDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("usuario/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        usuarioService.deleteUser(id);
        return new ResponseEntity<>("User deleted succesfully", HttpStatus.OK);
    }


}
