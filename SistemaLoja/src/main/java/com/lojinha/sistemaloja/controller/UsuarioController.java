package com.lojinha.sistemaloja.controller;

import com.lojinha.sistemaloja.dto.LoginRequestDTO;
import com.lojinha.sistemaloja.dto.LoginResponseDTO;
import com.lojinha.sistemaloja.model.Usuario;
import com.lojinha.sistemaloja.security.JwtService;
import com.lojinha.sistemaloja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class UsuarioController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService service;

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO request) {

        Usuario usuario = service.login(
                request.getNome(),
                request.getSenha());

        String token = jwtService.gerarToken(
                usuario.getNome());

        return new LoginResponseDTO(token);
    }
}