package com.infingardi.organizadordetarefas.service;

import com.infingardi.organizadordetarefas.domain.Task;
import com.infingardi.organizadordetarefas.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Cria um constructor com todos os atributos final
public class TaskService {
    private final TaskRepository taskRepository;

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> listAllByUserId(String userId) {
        return taskRepository.findAllByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    public Optional<Task> listTaskById(UUID id) {
        return taskRepository.findById(id);
    }
}
