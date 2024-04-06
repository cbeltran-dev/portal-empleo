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

import com.sise.portalempleo.Adapters.CategoriaEmpleoAdapter;
import com.sise.portalempleo.entities.CategoriaEmpleo;
import com.sise.portalempleo.pyload.requests.CategoriaEmpleoRequest;
import com.sise.portalempleo.services.CategoriaEmpleoService;
import com.sise.portalempleo.shared.BaseResponse;
import com.sise.portalempleo.utils.ValidationUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoriaEmpleo")
public class CategoriaEmpleoController {
    @Autowired
    private CategoriaEmpleoService categoriaEmpleoService;
    private CategoriaEmpleoAdapter categoriaEmpleoAdapter;

    public CategoriaEmpleoController() {
        categoriaEmpleoAdapter = new CategoriaEmpleoAdapter();
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarCategoriaEmpleos() {
        try {
            List<CategoriaEmpleo> categoriaEmpleo = categoriaEmpleoService.listaCategoriaEmpleos();
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(categoriaEmpleo),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idCategoriaEmpleo}")
    public ResponseEntity<BaseResponse> listarCategoriaEmpleoPorId(@PathVariable Integer idCategoriaEmpleo) {

        try {
            CategoriaEmpleo categoriaEmpleo = categoriaEmpleoService.listaCategoriaEmpleoPorId(idCategoriaEmpleo);
            if (categoriaEmpleo == null) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.errorNotFound(),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(categoriaEmpleo),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarCategoriaEmpleo(
            @Valid @RequestBody CategoriaEmpleoRequest categoriaEmpleoRequest, Errors errors) {

        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }
            CategoriaEmpleo categoriaEmpleo = categoriaEmpleoService.insertarCategoriaEmpleo(
                    categoriaEmpleoAdapter.toEntity(categoriaEmpleoRequest));
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(categoriaEmpleo),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{idCategoriaEmpleo}")
    public ResponseEntity<BaseResponse> actualizarCategoriaEmpleo(@PathVariable Integer idCategoriaEmpleo,
            @Valid @RequestBody CategoriaEmpleoRequest categoriaEmpleoRequest, Errors errors) {
        try {

            CategoriaEmpleo categoriaEmpleo = categoriaEmpleoAdapter.toEntity(categoriaEmpleoRequest);
            categoriaEmpleo.setIdCategoriaEmpleo(idCategoriaEmpleo);
            CategoriaEmpleo newCategoriaEmpleo = categoriaEmpleoService.actualizarCategoriaEmpleo(categoriaEmpleo);

            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(newCategoriaEmpleo),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/darbaja/{idCategoriaEmpleo}")
    public ResponseEntity<BaseResponse> darbajaCategoriaEmpleo(@PathVariable Integer idCategoriaEmpleo) {
        try {
            categoriaEmpleoService.darbajaCategoriaEmpleo(idCategoriaEmpleo);
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
