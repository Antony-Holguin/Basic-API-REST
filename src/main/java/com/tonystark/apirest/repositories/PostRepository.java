package com.tonystark.apirest.repositories;

import com.tonystark.apirest.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
