package com.sise.portalempleo.controllers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sise.portalempleo.entities.Usuario;
import com.sise.portalempleo.pyload.requests.UsuarioRequest;
import com.sise.portalempleo.pyload.responses.UsuarioResponse;
import com.sise.portalempleo.services.UsuarioService;
import com.sise.portalempleo.shared.BaseResponse;
import com.sise.portalempleo.utils.MappedUtils;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.listaUsuarios();
            List<UsuarioResponse> usuarioResponseList = new ArrayList<>();
            for (Usuario usuario : usuarios) {
                usuarioResponseList.add(MappedUtils.toUsuarioResponse(usuario));
            }
            return new ResponseEntity<BaseResponse>(
                BaseResponse.success(usuarioResponseList),
                HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                BaseResponse.error(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<BaseResponse> listarUsuarioPorId(@PathVariable Integer idUsuario){
        try {
            Usuario usuario = usuarioService.listaUsuarioPorId(idUsuario);

            if(usuario==null){
                return new ResponseEntity<BaseResponse>(
                    BaseResponse.errorNotFound(),
                    HttpStatus.NOT_FOUND
                );
            }

            return new ResponseEntity<BaseResponse>(
                BaseResponse.success(MappedUtils.toUsuarioResponse(usuario)),
                HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                BaseResponse.error(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarUsuario(@RequestBody UsuarioRequest usuarioRequest){
        try {
            
            Usuario usuario = usuarioService.insertarUsuario(MappedUtils.toUsuarioEntity(usuarioRequest));
            return new ResponseEntity<BaseResponse>(
                BaseResponse.success(MappedUtils.toUsuarioResponse(usuario)),
                HttpStatus.CREATED);
        } catch (Exception e) { 
            return new ResponseEntity<BaseResponse>(
                BaseResponse.error(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<BaseResponse> actualizarUsuario(@PathVariable Integer idUsuario, @RequestBody UsuarioRequest usuarioRequest){
        try {
            Usuario usuario = MappedUtils.toUsuarioEntity(usuarioRequest);
            usuario.setIdUsuario(idUsuario);
            
            Usuario newUsuario = usuarioService.actualizarUsuario(usuario);

            return new ResponseEntity<BaseResponse>(
                BaseResponse.success(MappedUtils.toUsuarioResponse(newUsuario)),
                HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                BaseResponse.error(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PatchMapping("/darbaja/{idUsuario}")
    public ResponseEntity<BaseResponse> darbajaUsuario(@PathVariable Integer idUsuario){
        try {

            usuarioService.darbajaUsuario(idUsuario);

            return new ResponseEntity<BaseResponse>(
                BaseResponse.success(null),
                HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(
                BaseResponse.error(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}


