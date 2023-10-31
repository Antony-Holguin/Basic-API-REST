package com.tonystark.apirest.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    private Long id;
    private String name;
    private String lastname;
    private int age;
    private String password;

}
