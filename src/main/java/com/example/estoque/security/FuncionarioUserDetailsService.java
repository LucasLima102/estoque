package com.example.estoque.security;

import com.example.estoque.model.LoginFuncionario;
import com.example.estoque.repositories.LoginFuncionarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioUserDetailsService implements UserDetailsService {

    private final LoginFuncionarioRepository loginFuncionarioRepository;

    public FuncionarioUserDetailsService(LoginFuncionarioRepository loginFuncionarioRepository) {
        this.loginFuncionarioRepository = loginFuncionarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginFuncionario login = loginFuncionarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));

        return User.builder()
                .username(login.getUsuario())
                .password(login.getSenha())
                .roles("FUNCIONARIO")
                .build();
    }
}
