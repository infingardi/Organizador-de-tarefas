package com.infingardi.organizadordetarefas.controller;

import com.infingardi.organizadordetarefas.domain.User;
import com.infingardi.organizadordetarefas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // Cria um constructor com todos os atributos final
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user){
        return ResponseEntity.status(201).body(userService.create(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        // Retorna um token
        return ResponseEntity.status(200).body(userService.login(user));
    }
}
