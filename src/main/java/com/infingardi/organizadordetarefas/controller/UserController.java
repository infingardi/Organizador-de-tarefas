package com.infingardi.organizadordetarefas.controller;

import com.infingardi.organizadordetarefas.domain.User;
import com.infingardi.organizadordetarefas.dto.TokenDto;
import com.infingardi.organizadordetarefas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // Cria um constructor com todos os atributos final
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody @Valid User user){
        try {
            return ResponseEntity.status(201).body(userService.create(user));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid User user){
        try {
            // Retorna um token
            return ResponseEntity.status(200).body(userService.login(user));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
