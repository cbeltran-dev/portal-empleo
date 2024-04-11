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

import com.sise.portalempleo.Adapters.RegistroPostulacionAdapter;
import com.sise.portalempleo.entities.RegistroPostulacion;
import com.sise.portalempleo.pyload.requests.RequestInserts.RegistroPostulacionRequestInsert;
import com.sise.portalempleo.pyload.requests.RequestUpdates.RegistroPostulacionRequestUpdate;
import com.sise.portalempleo.services.RegistroPostulacionService;
import com.sise.portalempleo.shared.BaseResponse;
import com.sise.portalempleo.utils.ValidationUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/registroPostulacion")
public class RegistroPostulacionController {
    @Autowired
    private RegistroPostulacionService registroPostulacionService;
    private RegistroPostulacionAdapter registroPostulacionAdapter;

    public RegistroPostulacionController() {
        registroPostulacionAdapter = new RegistroPostulacionAdapter();
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarRegistroPostulaciones() {
        try {
            List<RegistroPostulacion> RegistroPostulaciones = registroPostulacionService.listaRegistroPostulacion();
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(RegistroPostulaciones),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idRegistroPostulacion}")
    public ResponseEntity<BaseResponse> listarRegistroPostulacionPorId(@PathVariable Integer idRegistroPostulacion) {
        try {
            RegistroPostulacion registroPostulacion = registroPostulacionService.listaRegistroPostulacionPorId(idRegistroPostulacion);

            if (registroPostulacion == null) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.errorNotFound(),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success((registroPostulacion)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarRegistroPostulacion(
            @Valid @RequestBody RegistroPostulacionRequestInsert registroPostulacionRequestInsert, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }
            RegistroPostulacion registroPostulacion = registroPostulacionService.insertarRegistroPostulacion(registroPostulacionAdapter.insertToEntity(registroPostulacionRequestInsert));
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(registroPostulacion),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idRegistroPostulacion}")
    public ResponseEntity<BaseResponse> actualizarRegistroPostulacion(@PathVariable Integer idRegistroPostulacion,
            @Valid @RequestBody RegistroPostulacionRequestUpdate registroPostulacionRequestUpdate, Errors errors) {
        try {
            RegistroPostulacion registroPostulacion = registroPostulacionAdapter.updateToEntity(registroPostulacionRequestUpdate);
            registroPostulacion.setIdRegistroPostulacion(idRegistroPostulacion);
            RegistroPostulacion newRegistroPostulacion = registroPostulacionService.actualizarRegistroPostulacion(registroPostulacion);
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(newRegistroPostulacion),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/darbaja/{idRegistroPostulacion}")
    public ResponseEntity<BaseResponse> darbajaRegistroPostulacion(@PathVariable Integer idRegistroPostulacion) {
        try {
            registroPostulacionService.darbajaRegistroPostulacion(idRegistroPostulacion);
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
