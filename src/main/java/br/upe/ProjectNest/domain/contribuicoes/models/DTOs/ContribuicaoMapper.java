package br.upe.ProjectNest.domain.contribuicoes.models.DTOs;

import br.upe.ProjectNest.domain.contribuicoes.models.Contribuicao;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoCreationDTO;
import br.upe.ProjectNest.domain.projetos.models.Projeto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContribuicaoMapper {
    Contribuicao toEntity(ContribuicaoCreationDTO contribuicaoCreationDTO);

    Contribuicao toEntity(ContribuicaoDTO contribuicaoDTO);


    ContribuicaoCreationDTO toCreationDto(Contribuicao contribuicao);

    @Mapping(source = "usuario.uuid", target = "idUsuario")
    @Mapping(source = "projeto.uuid", target = "idProjeto")
    ContribuicaoDTO toDto(Contribuicao Contribuicao);

}
