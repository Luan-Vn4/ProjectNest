package br.upe.ProjectNest.domain.usuarios.services;

import br.upe.ProjectNest.domain.usuarios.dtos.EmpresaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.PessoaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.UpdateUsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.UsuarioCreationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import br.upe.ProjectNest.domain.usuarios.exceptions.UsuarioNotFoundException;

public interface UsuarioService {

    // REGISTRO
    UsuarioDTO create(UsuarioCreationDTO dto);

    // BUSCA
    Optional<UsuarioDTO> getByUuid(UUID uuid);

    Optional<UsuarioDTO> getByEmail(String email);

    PagedModel<UsuarioDTO> searchByApelido(String apelido, Pageable pageable);

    PagedModel<PessoaDTO> searchPessoaByApelido(String apelido, Pageable pageable);

    PagedModel<EmpresaDTO> searchEmpresaByNome(String empresa, Pageable pageable);

    PagedModel<EmpresaDTO> getAllEmpresas(Pageable pageable);

    Optional<EmpresaDTO> getEmpresaByCNPJ(String cnpj);

    // ATUALIZAÇÃO
    UsuarioDTO update(UpdateUsuarioDTO dto, UUID uuid);

    // DELEÇÃO

    /**
     * Deleta um usuário, de acordo com seu UUID
     * @param uuid UUID do usuário que será deletado
     * @throws UsuarioNotFoundException caso não exista um usuário com aquele UUID
     */
    void delete(UUID uuid);

    Set<UsuarioDTO> findUsuariosByUUIDs(Set<UUID> uuids);

}
