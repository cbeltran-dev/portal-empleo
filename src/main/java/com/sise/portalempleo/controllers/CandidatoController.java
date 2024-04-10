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

import com.sise.portalempleo.Adapters.CandidatoAdapter;
import com.sise.portalempleo.entities.Candidato;
import com.sise.portalempleo.pyload.requests.RequestInserts.CandidatoRequestInsert;
import com.sise.portalempleo.pyload.requests.RequestUpdates.CandidatoRequestUpdate;
import com.sise.portalempleo.services.CandidatoService;
import com.sise.portalempleo.shared.BaseResponse;
import com.sise.portalempleo.utils.ValidationUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;
    private CandidatoAdapter candidatoAdapter;

    public CandidatoController() {
        candidatoAdapter = new CandidatoAdapter();
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarCandidatos() {
        try {
            List<Candidato> Candidatos = candidatoService.listaCandidatos();
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(Candidatos),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idCandidato}")
    public ResponseEntity<BaseResponse> listarCandidatoPorId(@PathVariable Integer idCandidato) {
        try {
            Candidato candidato = candidatoService.listaCandidatoPorId(idCandidato);

            if (candidato == null) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.errorNotFound(),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success((candidato)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarCandidato(
            @Valid @RequestBody CandidatoRequestInsert candidatoRequestInsert, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }
            Candidato candidato = candidatoService.insertarCandidato(candidatoAdapter.insertToEntity(candidatoRequestInsert));
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(candidato),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idCandidato}")
    public ResponseEntity<BaseResponse> actualizarCandidato(@PathVariable Integer idCandidato,
            @Valid @RequestBody CandidatoRequestUpdate candidatoRequestUpdate, Errors errors) {
        try {
            Candidato candidato = candidatoAdapter.updateToEntity(candidatoRequestUpdate);
            candidato.setIdCandidato(idCandidato);
            Candidato newCandidato = candidatoService.actualizarCandidato(candidato);
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(newCandidato),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/darbaja/{idCandidato}")
    public ResponseEntity<BaseResponse> darbajaCandidato(@PathVariable Integer idCandidato) {
        try {
            candidatoService.darbajaCandidato(idCandidato);
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
