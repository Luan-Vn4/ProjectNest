package br.upe.ProjectNest.domain.usuarios.dtos;

import br.upe.ProjectNest.domain.usuarios.models.Empresa;
import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import java.util.UUID;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface UpdateUsuarioMapper {

    default Usuario toEntity(UpdateUsuarioDTO dto, UUID uuid) {
        Usuario usuario = dto.visit(this);
        usuario.setUuid(uuid);
        return usuario;
    }

    Pessoa toEntity(UpdatePessoaDTO dto);

    Empresa toEntity(UpdateEmpresaDTO dto);

}
