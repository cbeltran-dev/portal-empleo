package com.sise.portalempleo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sise.portalempleo.services.UsuarioService;
import com.sise.portalempleo.shared.BaseResponse;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public BaseResponse listar(){
        try {
            return BaseResponse.success(usuarioService.listaUsuarios());
        } catch (Exception e) {
            return BaseResponse.error(e.getMessage());
        }
    }
}
