package br.upe.ProjectNest.domain.usuarios.dtos.registration;

import br.upe.ProjectNest.domain.usuarios.models.Empresa;
import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioRegistrationMapper {

    default Usuario toEntity(UsuarioRegistrationDTO usuarioRegistrationDTO) {
        if (usuarioRegistrationDTO instanceof PessoaRegistrationDTO) return toEntity((PessoaRegistrationDTO) usuarioRegistrationDTO);
        if (usuarioRegistrationDTO instanceof EmpresaRegistrationDTO) return toEntity((EmpresaRegistrationDTO) usuarioRegistrationDTO);
        throw new ClassCastException("A classe " + usuarioRegistrationDTO.getClass() + " não é suportada");
    }

    default UsuarioRegistrationDTO toDto(Usuario usuario) {
        if (usuario instanceof Pessoa) return toDto((Pessoa) usuario);
        if (usuario instanceof Empresa) return toDto((Empresa) usuario);
        throw new ClassCastException("A classe " + usuario.getClass() + " não é suportada");
    }

    Pessoa toEntity(PessoaRegistrationDTO pessoaDTO);

    PessoaRegistrationDTO toDto(Pessoa pessoa);

    Empresa toEntity(EmpresaRegistrationDTO empresaDTO);

    EmpresaRegistrationDTO toDto(Empresa empresa);

}
