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
import com.sise.portalempleo.Adapters.OfertaTrabajoAdapter;
import com.sise.portalempleo.entities.OfertaTrabajo;
import com.sise.portalempleo.pyload.requests.RequestInserts.OfertaTrabajoRequestInsert;
import com.sise.portalempleo.pyload.requests.RequestUpdates.OfertaTrabajoRequestUpdate;
import com.sise.portalempleo.services.OfertaTrabajoService;
import com.sise.portalempleo.shared.BaseResponse;
import com.sise.portalempleo.utils.ValidationUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ofertaTrabajo")
public class OfertaTrabajoController {
    
    @Autowired
    private OfertaTrabajoService ofertaTrabajoService;
    private OfertaTrabajoAdapter ofertaTrabajoAdapter;

    public OfertaTrabajoController() {
        ofertaTrabajoAdapter = new OfertaTrabajoAdapter();
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarOfertaTrabajo() {
        try {
            List<OfertaTrabajo> OfertaTrabajo = ofertaTrabajoService.listaOfertaTrabajo();
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(OfertaTrabajo),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idOfertaTrabajo}")
    public ResponseEntity<BaseResponse> listarOfertaTrabajoPorId(@PathVariable Integer idOfertaTrabajo) {
        try {
            OfertaTrabajo ofertaTrabajo = ofertaTrabajoService.listaOfertaTrabajoPorId(idOfertaTrabajo);

            if (ofertaTrabajo == null) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.errorNotFound(),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success((ofertaTrabajo)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarOfertaTrabajo(
            @Valid @RequestBody OfertaTrabajoRequestInsert ofertaTrabajoRequestInsert, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }
            OfertaTrabajo ofertaTrabajo = ofertaTrabajoService.insertarOfertaTrabajo(ofertaTrabajoAdapter.insertToEntity(ofertaTrabajoRequestInsert));
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(ofertaTrabajo),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idOfertaTrabajo}")
    public ResponseEntity<BaseResponse> actualizarOfertaTrabajo(@PathVariable Integer idOfertaTrabajo,
            @Valid @RequestBody OfertaTrabajoRequestUpdate ofertaTrabajoRequestUpdate, Errors errors) {
        try {
            OfertaTrabajo ofertaTrabajo = ofertaTrabajoAdapter.updateToEntity(ofertaTrabajoRequestUpdate);
            ofertaTrabajo.setIdOfertaTrabajo(idOfertaTrabajo);
            OfertaTrabajo newOfertaTrabajo = ofertaTrabajoService.actualizarOfertaTrabajo(ofertaTrabajo);
            if (errors.hasErrors()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.error(ValidationUtil.getOneMessageFromErrors(errors.getFieldErrors())),
                        HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(newOfertaTrabajo),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/darbaja/{idOfertaTrabajo}")
    public ResponseEntity<BaseResponse> darbajaOfertaTrabajo(@PathVariable Integer idOfertaTrabajo) {
        try {
            ofertaTrabajoService.darbajaOfertaTrabajo(idOfertaTrabajo);
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoria/{idCategoriaEmpleo}")
    public ResponseEntity<BaseResponse> buscarPorCategoriaEmpleo(@PathVariable Integer idCategoriaEmpleo) {
        try {
        List<OfertaTrabajo> lstOfertaTrabajo = ofertaTrabajoService.buscarPorCategoriaEmpleo(idCategoriaEmpleo);

            if (lstOfertaTrabajo == null || lstOfertaTrabajo.isEmpty()) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.errorNotFound(),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success((lstOfertaTrabajo)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cambiarEstado/{idOfertaTrabajo}")
    public ResponseEntity<BaseResponse> activarDesactivarOferta(@PathVariable Integer idOfertaTrabajo) {
        try {
            OfertaTrabajo ofertaExistente = ofertaTrabajoService.listaOfertaTrabajoPorId(idOfertaTrabajo);
            if (ofertaExistente == null) {
                return new ResponseEntity<BaseResponse>(
                        BaseResponse.errorNotFound(),
                        HttpStatus.NOT_FOUND);
            }
            OfertaTrabajo ofertaActualizada = ofertaTrabajoService.activarDesactivarOferta(ofertaExistente);
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.success(ofertaActualizada),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                    BaseResponse.error(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
