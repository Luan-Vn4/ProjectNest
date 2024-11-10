package br.upe.ProjectNest.domain.usuarios.dtos;

import br.upe.ProjectNest.domain.usuarios.models.Empresa;
import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import org.mapstruct.*;

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

    Pessoa toEntity(PessoaDTO pessoaDTO);

    PessoaDTO toDto(Pessoa pessoa);

    Empresa toEntity(EmpresaDTO empresaDTO);

    EmpresaDTO toDto(Empresa empresa);

}