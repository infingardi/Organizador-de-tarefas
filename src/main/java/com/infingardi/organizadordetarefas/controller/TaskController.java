package com.infingardi.organizadordetarefas.controller;

import com.infingardi.organizadordetarefas.domain.Task;
import com.infingardi.organizadordetarefas.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor // Cria um constructor com todos os atributos final
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        return ResponseEntity.status(200).body(taskService.create(task));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Task>> listAllByUserId(@PathVariable String userId) {
        return ResponseEntity.status(200).body(taskService.listAllByUserId(userId));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Optional<Task>> listTaskById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(taskService.listTaskById(id));
    }
}
