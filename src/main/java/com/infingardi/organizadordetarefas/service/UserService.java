package com.infingardi.organizadordetarefas.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.infingardi.organizadordetarefas.domain.User;
import com.infingardi.organizadordetarefas.dto.TokenDto;
import com.infingardi.organizadordetarefas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

// Camada responsavel por se comunicar com o banco e aplicar regras de negócios
@Service
@RequiredArgsConstructor // Cria um constructor com todos os atributos final
public class UserService {
    private final UserRepository userRepository;

    // Função de criptografia
    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private String createToken(User user) {
        Instant expire = Instant.now().plusSeconds(30 * 60); // Tempo de expiração: 30 minutos

        Algorithm algorithm = Algorithm.HMAC256("secret"); // Senha usada apenas para propósitos de aprendizado
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("username", user.getUsername()) // Parâmetros salvos no token
                .withClaim("email", user.getEmail())
                .withExpiresAt(expire)
                .sign(algorithm);

        return token;
    }

    public User create(User user) throws Exception {
        if(existsByEmail(user.getEmail())) throw new Exception("Email already in use");

        user.setPassword(passwordEncoder().encode(user.getPassword()));

        return userRepository.save(user);
    }

    public TokenDto login(User user) throws Exception {
        try {
            User u = userRepository.findByEmail(user.getEmail());

            if(u == null) throw new Exception("Email or password is incorrect");
            if(!(passwordEncoder()
                    .matches(user.getPassword(), u.getPassword()))) throw new Exception("Email or password is incorrect");


            return TokenDto.builder()
                    .token(createToken(u))
                    .build();
        } catch (JWTCreationException exception){
            throw new Error("Fail to create Token");
        }
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
