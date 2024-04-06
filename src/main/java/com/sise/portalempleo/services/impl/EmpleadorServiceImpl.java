package com.sise.portalempleo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.portalempleo.entities.Empleador;
import com.sise.portalempleo.repositories.EmpleadorRepository;
import com.sise.portalempleo.repositories.UsuarioRespository;
import com.sise.portalempleo.services.EmpleadorService;
@Service
public class EmpleadorServiceImpl implements EmpleadorService {

    @Autowired
    EmpleadorRepository empleadorRepository;
    @Autowired
    UsuarioRespository usuarioRespository;

    @Override
    public List<Empleador> listaEmpleadores() {
        return empleadorRepository.findByEstadoAuditoria("1");
    }

    @Override
    public Empleador listaEmpleadorPorId(Integer idEmpleador) {
        return empleadorRepository.findOneByIdEmpleadorAndEstadoAuditoria(idEmpleador, "1");
    }

    @Override
    public Empleador insertarEmpleador(Empleador empleador) {
        return empleadorRepository.save(empleador);
    }

    @Override
    public Empleador actualizarEmpleador(Empleador empleador) {
        empleadorRepository.actualizarEmpleador(empleador.getIdEmpleador(),
        empleador.getNombre(),empleador.getDescripcion(), empleador.getRuc(), 
        empleador.getDireccion(), empleador.getTelefono(), empleador.getFotoPerfil());
        return empleadorRepository.findOneByIdEmpleadorAndEstadoAuditoria(empleador.getIdEmpleador(), "1");
    }

    @Override
    public void darbajaEmpleador(Integer idEmpleador) {
        empleadorRepository.darBajaEmpleador(idEmpleador);
    }
    
}
