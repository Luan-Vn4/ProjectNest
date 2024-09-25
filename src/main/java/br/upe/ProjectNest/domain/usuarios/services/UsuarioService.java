package br.upe.ProjectNest.domain.usuarios.services;

import br.upe.ProjectNest.domain.usuarios.models.Usuario;

import java.util.UUID;

public interface UsuarioService {

    Usuario getByUuid(UUID uuid);
}
