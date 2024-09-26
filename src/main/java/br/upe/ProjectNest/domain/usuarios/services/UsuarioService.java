package br.upe.ProjectNest.domain.usuarios.services;

import br.upe.ProjectNest.domain.common.pagination.PaginatedResult;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.EmpresaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.PessoaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.registration.UsuarioCreationDTO;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {

    // REGISTRO
    UsuarioDTO registerUsuario(UsuarioCreationDTO dto);

    // BUSCA
    Optional<UsuarioDTO> getByUuid(UUID uuid);

    Optional<UsuarioDTO> getByEmail(String email);

    PaginatedResult<UsuarioDTO> searchByApelido(String apelido, Pageable pageable);

    PaginatedResult<PessoaDTO> searchPessoaByApelido(String apelido, Pageable pageable);

    PaginatedResult<EmpresaDTO> searchEmpresaByNome(String empresa, Pageable pageable);

    PaginatedResult<EmpresaDTO> getAllEmpresas(Pageable pageable);

    Optional<EmpresaDTO> getEmpresaByCNPJ(String cnpj);

    // ATUALIZAÇÃO
    UsuarioDTO update(UsuarioDTO dto);

    // DELEÇÃO
    void deleteUsuario(UUID uuid);

}
