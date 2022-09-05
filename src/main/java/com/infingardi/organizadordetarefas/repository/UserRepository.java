package com.infingardi.organizadordetarefas.repository;

import com.infingardi.organizadordetarefas.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// Ao extender o JpaRepository a maioria das queries são criadas
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    Boolean existsByEmail(String Email);
}
