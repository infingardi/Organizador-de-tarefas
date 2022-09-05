package com.infingardi.organizadordetarefas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data // Gera getters e setters
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String taskDescription;

    @Column(nullable = false)
    private Date data = new Date();

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private String userId;

}
