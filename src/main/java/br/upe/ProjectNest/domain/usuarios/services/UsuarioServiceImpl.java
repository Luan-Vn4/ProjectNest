package br.upe.ProjectNest.domain.usuarios.services;

import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Override
    public Usuario getByUuid(UUID uuid) {
        return null;
    }
}
