package com.tonystark.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class PostDTO {
    private long id;
    private String title;
    private String description;

    private String picture;
}
