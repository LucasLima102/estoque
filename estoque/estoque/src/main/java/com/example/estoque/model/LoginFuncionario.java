package com.example.estoque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "login_funcionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 60)
    private String usuario;

    @Column(nullable = false, length = 255)
    private String senha;

    private LocalDateTime ultimoLogin;

    @OneToOne
    @JoinColumn(name = "funcionario_id", nullable = false, unique = true)
    private Funcionario funcionario;
}
