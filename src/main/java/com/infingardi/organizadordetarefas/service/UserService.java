package com.infingardi.organizadordetarefas.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.infingardi.organizadordetarefas.domain.User;
import com.infingardi.organizadordetarefas.repository.UserRepository;
import com.infingardi.organizadordetarefas.utils.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public User create(User user) {
        // Realiza a critografia da senha
        user.setPassword(passwordEncoder().encode(user.getPassword()));

        return userRepository.save(user);
    }

    public String login(User user) {
        try {
            User u = userRepository.findByEmail(user.getEmail());

            if(!(passwordEncoder().matches(user.getPassword(), u.getPassword()))) throw new Error("Incorrect Password");

            return this.createToken(u);
        } catch (JWTCreationException exception){
            throw new Error("Fail to create Token");
        }
    }
}
