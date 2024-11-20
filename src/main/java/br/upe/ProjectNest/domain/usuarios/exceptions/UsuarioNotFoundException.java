package br.upe.ProjectNest.domain.usuarios.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.experimental.StandardException;

import java.util.UUID;

@StandardException
public class UsuarioNotFoundException extends EntityNotFoundException {

    public UsuarioNotFoundException(UUID uuid) {
        super("Não foi possível encontrar um usuário com o UUID: " + uuid);
    }

}
