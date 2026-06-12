package com.lojinha.sistemaloja.service;

import com.lojinha.sistemaloja.model.Usuario;
import com.lojinha.sistemaloja.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {

        usuario.setSenha(
                passwordEncoder.encode(usuario.getSenha())
        );

        return repository.save(usuario);
    }

    public Usuario login(String nome, String senha) {

        Optional<Usuario> user = repository.findByNome(nome);

        if (user.isPresent()) {

            Usuario usuarioBanco = user.get();

            if (passwordEncoder.matches(
                    senha,
                    usuarioBanco.getSenha())) {

                return usuarioBanco;
            }
        }

        throw new RuntimeException("Usuário ou senha inválidos");
    }
}