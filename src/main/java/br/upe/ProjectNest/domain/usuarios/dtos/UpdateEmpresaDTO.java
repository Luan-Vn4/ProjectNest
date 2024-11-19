package br.upe.ProjectNest.domain.usuarios.dtos;

import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record UpdateEmpresaDTO(
    @Size(max = 50)
    String apelido,
    @CNPJ
    String cnpj) implements UpdateUsuarioDTO {

    @Override
    public Usuario visit(UpdateUsuarioMapper mapper) {
        return mapper.toEntity(this);
    }

}
