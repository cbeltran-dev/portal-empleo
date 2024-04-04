package com.sise.portalempleo.services.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sise.portalempleo.entities.TipoUsuario;
import com.sise.portalempleo.repositories.TipoUsuarioRepository;
import com.sise.portalempleo.services.TipoUsuarioSerivice;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioSerivice {

    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public List<TipoUsuario> listaTipoUsuarios() {
        return tipoUsuarioRepository.findByEstadoAuditoria("1");
    }

    @Override
    public TipoUsuario listaTipoUsuarioPorId(Integer idTipoUsuario) {
        return tipoUsuarioRepository.findOneByIdTipoUsuarioAndEstadoAuditoria(idTipoUsuario, "1");
    }

    @Override
    public TipoUsuario insertarTipoUsuario(TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    @Override
    public TipoUsuario actualizarTipoUsuario(TipoUsuario tipoUsuario) {
        tipoUsuarioRepository.actualizarTipoUsuario(tipoUsuario.getIdTipoUsuario(), tipoUsuario.getDescripcion());
        return tipoUsuarioRepository.findOneByIdTipoUsuarioAndEstadoAuditoria(tipoUsuario.getIdTipoUsuario(), "1");
    }

    @Override
    public void darbajaTipoUsuario(Integer idTipoUsuario) {
        tipoUsuarioRepository.darbajaUsuario(idTipoUsuario);
    }
    
}
