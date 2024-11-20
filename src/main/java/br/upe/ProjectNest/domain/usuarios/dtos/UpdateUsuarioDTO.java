package br.upe.ProjectNest.domain.usuarios.dtos;

import br.upe.ProjectNest.domain.usuarios.models.Usuario;

public interface UpdateUsuarioDTO {

    String apelido();

    // Implementação do padrão visitor, pois o mapper não conseguirá saber qual instância de
    // UpdateUsuarioDTO ele irá receber
    Usuario visit(UpdateUsuarioMapper mapper);

}
