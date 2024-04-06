package com.sise.portalempleo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.sise.portalempleo.Adapters.EmpleadorAdapter;
import com.sise.portalempleo.entities.Empleador;
import com.sise.portalempleo.pyload.requests.RequestInserts.EmpleadorRequestInsert;
import com.sise.portalempleo.pyload.requests.RequestUpdates.EmpleadorRequestUpdate;
import com.sise.portalempleo.services.EmpleadorService;
import com.sise.portalempleo.shared.BaseResponse;
import com.sise.portalempleo.utils.ValidationUtil;

import jakarta.validation.Valid;

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


@RestController
@RequestMapping("/empleador")
public class EmpleadorController {
    
    @Autowired
    private EmpleadorService empleadorService;
    private EmpleadorAdapter empleadorAdapter;

    public EmpleadorController() {
        empleadorAdapter = new EmpleadorAdapter();
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarEmpleadores() {
        try {
            List<Empleador> Empleadores = empleadorService.listaEmpleadores();
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(Empleadores),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idEmpleador}")
    public ResponseEntity<BaseResponse> listarEmpleadorPorId(@PathVariable Integer idEmpleador) {
        try {
            Empleador empleador = empleadorService.listaEmpleadorPorId(idEmpleador);

            if (empleador == null) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.errorNotFound(),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success((empleador)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarEmpleador(
            @Valid @RequestBody EmpleadorRequestInsert empleadorRequest, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }
            Empleador empleador = empleadorService.insertarEmpleador(empleadorAdapter.insertToEntity(empleadorRequest));
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(empleador),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idEmpleador}")
    public ResponseEntity<BaseResponse> actualizarEmpleador(@PathVariable Integer idEmpleador,
            @Valid @RequestBody EmpleadorRequestUpdate empleadorRequestUpdate, Errors errors) {
        try {
            Empleador empleador = empleadorAdapter.updateToEntity(empleadorRequestUpdate);
            empleador.setIdEmpleador(idEmpleador);
            Empleador newEmpleador = empleadorService.actualizarEmpleador(empleador);
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(newEmpleador),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/darbaja/{idEmpleador}")
    public ResponseEntity<BaseResponse> darbajaEmpleador(@PathVariable Integer idEmpleador) {
        try {
            empleadorService.darbajaEmpleador(idEmpleador);
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
