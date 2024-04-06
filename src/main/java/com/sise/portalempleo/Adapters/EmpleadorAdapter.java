package com.sise.portalempleo.Adapters;

import com.sise.portalempleo.entities.Empleador;
import com.sise.portalempleo.entities.Usuario;
import com.sise.portalempleo.pyload.requests.RequestInserts.EmpleadorRequestInsert;
import com.sise.portalempleo.pyload.requests.RequestUpdates.EmpleadorRequestUpdate;
import com.sise.portalempleo.utils.AdapterTemplate;

public class EmpleadorAdapter implements AdapterTemplate<Empleador, EmpleadorRequestInsert, EmpleadorRequestUpdate> {

        @Override
        public Empleador insertToEntity(EmpleadorRequestInsert insert) {
                Empleador entity = new Empleador();

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(insert.getIdUsuario());

                entity.setUsuario(usuario);
                entity.setNombre(insert.getNombre());
                entity.setDescripcion(insert.getDescripcion());
                entity.setRuc(insert.getRuc());
                entity.setDireccion(insert.getDireccion());
                entity.setTelefono(insert.getTelefono());
                entity.setFotoPerfil(insert.getFotoPerfil());
                return entity;

        }

        @Override
        public Empleador updateToEntity(EmpleadorRequestUpdate update) {
                Empleador entity = new Empleador();
                entity.setNombre(update.getNombre());
                entity.setDescripcion(update.getDescripcion());
                entity.setRuc(update.getRuc());
                entity.setDireccion(update.getDireccion());
                entity.setTelefono(update.getTelefono());
                entity.setFotoPerfil(update.getFotoPerfil());
                return entity;
        }

}
