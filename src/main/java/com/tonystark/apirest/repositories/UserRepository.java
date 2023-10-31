package com.tonystark.apirest.repositories;

import com.tonystark.apirest.entities.Usuario;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
}
