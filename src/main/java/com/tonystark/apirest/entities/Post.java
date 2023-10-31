package com.tonystark.apirest.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "posts")
public class Post {

    //@Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;


    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "picture", nullable = false)
    private String picture;

}
