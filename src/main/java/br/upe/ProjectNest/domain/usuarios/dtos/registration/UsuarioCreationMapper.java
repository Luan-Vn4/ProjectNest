package br.upe.ProjectNest.domain.usuarios.dtos.registration;

import br.upe.ProjectNest.domain.usuarios.models.Empresa;
import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioCreationMapper {

    default Usuario toEntity(UsuarioCreationDTO usuarioCreationDTO) {
        if (usuarioCreationDTO instanceof PessoaCreationDTO) return toEntity((PessoaCreationDTO) usuarioCreationDTO);
        if (usuarioCreationDTO instanceof EmpresaCreationDTO) return toEntity((EmpresaCreationDTO) usuarioCreationDTO);
        throw new ClassCastException("A classe " + usuarioCreationDTO.getClass() + " não é suportada");
    }

    default UsuarioCreationDTO toDto(Usuario usuario) {
        if (usuario instanceof Pessoa) return toDto((Pessoa) usuario);
        if (usuario instanceof Empresa) return toDto((Empresa) usuario);
        throw new ClassCastException("A classe " + usuario.getClass() + " não é suportada");
    }

    Pessoa toEntity(PessoaCreationDTO pessoaDTO);

    PessoaCreationDTO toDto(Pessoa pessoa);

    Empresa toEntity(EmpresaCreationDTO empresaDTO);

    EmpresaCreationDTO toDto(Empresa empresa);

}
