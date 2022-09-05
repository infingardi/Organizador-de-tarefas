package com.infingardi.organizadordetarefas.repository;

import com.infingardi.organizadordetarefas.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    Optional<List<Task>> findAllByUserId(String userId);
}
