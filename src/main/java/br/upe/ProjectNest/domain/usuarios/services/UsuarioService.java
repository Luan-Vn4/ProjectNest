package br.upe.ProjectNest.domain.usuarios.services;

import br.upe.ProjectNest.domain.usuarios.dtos.fetch.EmpresaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.PessoaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.registration.UsuarioCreationDTO;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UsuarioService {

    // REGISTRO
    UsuarioDTO registerUsuario(UsuarioCreationDTO dto);

    // BUSCA
    Optional<UsuarioDTO> getByUuid(UUID uuid);

    Optional<UsuarioDTO> getByEmail(String email);

    PagedModel<UsuarioDTO> searchByApelido(String apelido, Pageable pageable);

    PagedModel<PessoaDTO> searchPessoaByApelido(String apelido, Pageable pageable);

    PagedModel<EmpresaDTO> searchEmpresaByNome(String empresa, Pageable pageable);

    PagedModel<EmpresaDTO> getAllEmpresas(Pageable pageable);

    Optional<EmpresaDTO> getEmpresaByCNPJ(String cnpj);

    // ATUALIZAÇÃO
    PessoaDTO update(PessoaDTO dto);

    EmpresaDTO update(EmpresaDTO dto);

    // DELEÇÃO
    void deleteUsuario(UUID uuid);

    Set<UsuarioDTO> findUsuariosByUUIDs(Set<UUID> uuids);

}
