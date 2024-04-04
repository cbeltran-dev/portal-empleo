package com.sise.portalempleo.controllers;

import java.util.List;
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
import com.sise.portalempleo.Adapters.TipoUsuarioAdapter;
import com.sise.portalempleo.entities.TipoUsuario;
import com.sise.portalempleo.pyload.requests.TipoUsuarioRequest;
import com.sise.portalempleo.services.TipoUsuarioSerivice;
import com.sise.portalempleo.shared.BaseResponse;
import com.sise.portalempleo.utils.ValidationUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipoUsuario")
public class TipoUsuarioController {
    @Autowired
    private TipoUsuarioSerivice tipoUsuarioService;
    private TipoUsuarioAdapter tipoUsuarioAdapter;

    public TipoUsuarioController() {
        tipoUsuarioAdapter = new TipoUsuarioAdapter();
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarTipoUsuarios() {
        try {
            List<TipoUsuario> tipoUsuario = tipoUsuarioService.listaTipoUsuarios();
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(tipoUsuario),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idTipoUsuario}")
    public ResponseEntity<BaseResponse> listarTipoUsuarioPorId(@PathVariable Integer idTipoUsuario) {

        try {
            TipoUsuario tipoUsuario = tipoUsuarioService.listaTipoUsuarioPorId(idTipoUsuario);
            if (tipoUsuario == null) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.errorNotFound(),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(tipoUsuario),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarTipoUsuario(
            @Valid @RequestBody TipoUsuarioRequest tipoUsuarioRequest, Errors errors) {

        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }
            TipoUsuario tipoUsuario = tipoUsuarioService.insertarTipoUsuario(
                    tipoUsuarioAdapter.toEntity(tipoUsuarioRequest));
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(tipoUsuario),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{idTipoUsuario}")
    public ResponseEntity<BaseResponse> actualizarTipoUsuario(@PathVariable Integer idTipoUsuario,
            @Valid @RequestBody TipoUsuarioRequest tipoUsuarioRequest, Errors errors) {
        try {

            TipoUsuario tipoUsuario = tipoUsuarioAdapter.toEntity(tipoUsuarioRequest);
            tipoUsuario.setIdTipoUsuario(idTipoUsuario);
            TipoUsuario newTipoUsuario = tipoUsuarioService.actualizarTipoUsuario(tipoUsuario);

            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(newTipoUsuario),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     @PatchMapping("/darbaja/{idTipoUsuario}")
    public ResponseEntity<BaseResponse> darbajaTipoUsuario(@PathVariable Integer idTipoUsuario) {
        try {
            tipoUsuarioService.darbajaTipoUsuario(idTipoUsuario);
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
