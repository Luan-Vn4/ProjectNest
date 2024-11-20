package br.upe.ProjectNest.domain.contribuicoes.models.DTOs;

import br.upe.ProjectNest.domain.contribuicoes.models.Contribuicao;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContribuicaoMapper {

    Contribuicao toEntity(ContribuicaoCreationDTO contribuicaoCreationDTO);

    Contribuicao toEntity(ContribuicaoDTO contribuicaoDTO);

    ContribuicaoCreationDTO toCreationDto(Contribuicao contribuicao);

    @Mapping(target = "idUsuarios", expression = "java(mapUsuariosToIds(contribuicao.getUsuarios()))")
    @Mapping(source = "projeto.uuid", target = "idProjeto")
    ContribuicaoDTO toDto(Contribuicao contribuicao);

    default Set<UUID> mapUsuariosToIds(Set<Usuario> usuarios) {
        return usuarios.stream().map(Usuario::getUuid).collect(Collectors.toSet());
    }
}
