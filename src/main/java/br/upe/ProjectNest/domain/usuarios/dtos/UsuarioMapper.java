package br.upe.ProjectNest.domain.usuarios.dtos;

import br.upe.ProjectNest.domain.usuarios.models.Empresa;
import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import br.upe.ProjectNest.infrastructure.security.authentication.authorities.Role;
import org.mapstruct.*;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    default Usuario toEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO instanceof PessoaDTO) return toEntity((PessoaDTO) usuarioDTO);
        if (usuarioDTO instanceof EmpresaDTO) return toEntity((EmpresaDTO) usuarioDTO);
        throw new ClassCastException("A classe " + usuarioDTO.getClass() + " não é suportada");
    }

    default UsuarioDTO toDto(Usuario usuario) {
        if (usuario instanceof Pessoa) return toDto((Pessoa) usuario);
        if (usuario instanceof Empresa) return toDto((Empresa) usuario);
        throw new ClassCastException("A classe " + usuario.getClass() + " não é suportada");
    }

    default Set<String> mapRolesToStrings(Set<Role> roles) {
        if (roles == null) return Set.of();

        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

    default Set<Role> mapStringsToRoles(Set<String> roles) {
        return Set.of();
    }

    Pessoa toEntity(PessoaDTO pessoaDTO);

    PessoaDTO toDto(Pessoa pessoa);

    Empresa toEntity(EmpresaDTO empresaDTO);

    EmpresaDTO toDto(Empresa empresa);

}