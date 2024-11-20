package br.upe.ProjectNest.domain.usuarios.dtos;

import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import jakarta.validation.constraints.Size;

public record UpdatePessoaDTO(
    @Size(max = 50)
    String apelido,
    @Size(max = 50)
    String nome,
    @Size(max = 50)
    String sobrenome,
    @Size(max = 25)
    String pronomes) implements UpdateUsuarioDTO {

    public Usuario visit(UpdateUsuarioMapper mapper) {
        return mapper.toEntity(this);
    }

}
