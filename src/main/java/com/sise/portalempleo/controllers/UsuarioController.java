package com.sise.portalempleo.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sise.portalempleo.Adapters.UsuarioAdapter;
import com.sise.portalempleo.entities.Usuario;
import com.sise.portalempleo.pyload.requests.RequestInserts.UsuarioRequestInsert;
import com.sise.portalempleo.pyload.requests.RequestUpdates.UsuarioRequestUpdate;
import com.sise.portalempleo.pyload.requests.RequestUpdates.UsuarioRequestUpdateClave;
import com.sise.portalempleo.services.UsuarioService;
import com.sise.portalempleo.shared.BaseEntity;
import com.sise.portalempleo.shared.BaseResponse;
import com.sise.portalempleo.utils.ValidationUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    private UsuarioAdapter usuarioAdapter;

    public UsuarioController() {
        usuarioAdapter = new UsuarioAdapter();
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarUsuarios() {
        try {
            List<Usuario> Usuarios = usuarioService.listaUsuarios();
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(Usuarios),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<BaseResponse> listarUsuarioPorId(@PathVariable Integer idUsuario) {
        try {
            Usuario usuario = usuarioService.listaUsuarioPorId(idUsuario);

            if (usuario == null) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.errorNotFound(),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success((usuario)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarUsuario(
            @Valid @RequestBody UsuarioRequestInsert usuarioRequestInsert, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }
            Usuario usuario = usuarioService.insertarUsuario(usuarioAdapter.insertToEntity(usuarioRequestInsert));
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(usuario),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<BaseResponse> actualizarUsuario(@PathVariable Integer idUsuario,
            @RequestBody UsuarioRequestUpdate usuarioRequestUpdate) {
        try {
            Usuario usuario = usuarioAdapter.updateToEntity(usuarioRequestUpdate);
            usuario.setIdUsuario(idUsuario);

            Usuario newUsuario = usuarioService.actualizarUsuario(usuario);

            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(newUsuario),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/cambiarClave/{idUsuario}")
    public ResponseEntity<BaseResponse> cambiarClaveUsuario(
            @PathVariable Integer idUsuario,
            @Valid @RequestBody UsuarioRequestUpdateClave usuarioRequestUpdateClave,
            Errors errors) {

        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }
            Usuario usuario = usuarioAdapter.updateClaveToEntity(usuarioRequestUpdateClave);
            usuario.setIdUsuario(idUsuario);

            usuarioService.cambiarClave(usuario);

            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PatchMapping("/darbaja/{idUsuario}")
    public ResponseEntity<BaseResponse> darbajaUsuario(@PathVariable Integer idUsuario) {
        try {
            usuarioService.darbajaUsuario(idUsuario);
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
