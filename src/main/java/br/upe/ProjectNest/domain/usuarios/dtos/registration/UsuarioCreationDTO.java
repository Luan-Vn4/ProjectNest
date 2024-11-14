package br.upe.ProjectNest.domain.usuarios.dtos.registration;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(use=JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({@Type(EmpresaCreationDTO.class), @Type(PessoaCreationDTO.class),})
public interface UsuarioCreationDTO {

    String apelido();

    String email();

    String senha();

}
