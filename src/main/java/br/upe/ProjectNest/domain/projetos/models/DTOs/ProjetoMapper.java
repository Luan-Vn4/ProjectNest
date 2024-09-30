package br.upe.ProjectNest.domain.projetos.models.DTOs;

import br.upe.ProjectNest.domain.projetos.models.Projeto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProjetoMapper {

    Projeto toEntity(ProjetoCreationDTO projetoCreationDTO);

    Projeto toEntity(ProjetoDTO projetoDTO);


    ProjetoCreationDTO toCreationDto(Projeto projeto);

    @Mapping(source = "dono.uuid", target = "idDono")
    ProjetoDTO toDto(Projeto projeto);
}
