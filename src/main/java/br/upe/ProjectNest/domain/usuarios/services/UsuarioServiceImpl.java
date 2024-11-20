package br.upe.ProjectNest.domain.usuarios.services;

import br.upe.ProjectNest.domain.usuarios.dtos.*;
import br.upe.ProjectNest.domain.usuarios.exceptions.UsuarioNotFoundException;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import br.upe.ProjectNest.domain.usuarios.repositories.EmpresaRepository;
import br.upe.ProjectNest.domain.usuarios.repositories.PessoaRepository;
import br.upe.ProjectNest.domain.usuarios.repositories.UsuarioRepository;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.UsuarioCreationDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.UsuarioCreationMapper;
import br.upe.ProjectNest.infrastructure.security.authentication.authorities.Role;
import br.upe.ProjectNest.infrastructure.security.authentication.authorities.RoleNames;
import br.upe.ProjectNest.infrastructure.security.authentication.services.roles.RoleService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioMapper usuarioMapper;

    private UsuarioCreationMapper usuarioCreationMapper;

    private UpdateUsuarioMapper updateUsuarioMapper;

    private UsuarioRepository usuarioRepository;

    private EmpresaRepository empresaRepository;

    private PessoaRepository pessoaRepository;

    private RoleService roleService;

    @Override
    @Transactional
    public UsuarioDTO create(UsuarioCreationDTO dto) {
        Usuario usuario = usuarioCreationMapper.toEntity(dto);

        Set<Role> defaultRoles = roleService.getByNames(RoleNames.defaultRoles.get(usuario.getClass()));
        usuario.addRoles(defaultRoles);

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    public Optional<UsuarioDTO> getByUuid(UUID uuid) {
        Optional<Usuario> usuario = usuarioRepository.findById(uuid);
        return usuario.map(value -> usuarioMapper.toDto(value));
    }

    @Override
    public Optional<UsuarioDTO> getByEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        return usuario.map(value -> usuarioMapper.toDto(value));
    }

    @Override
    public PagedModel<UsuarioDTO> searchByApelido(String apelido, Pageable pageable) {
        return new PagedModel<>(
            usuarioRepository.searchByApelidoContains(apelido, pageable).map(usuarioMapper::toDto));
    }

    @Override
    public PagedModel<PessoaDTO> searchPessoaByApelido(String apelido, Pageable pageable) {
        return new PagedModel<>(
            pessoaRepository.searchByApelidoContains(apelido, pageable).map(usuarioMapper::toDto));
    }

    @Override
    public PagedModel<EmpresaDTO> searchEmpresaByNome(String empresa, Pageable pageable) {
        return new PagedModel<>(
            empresaRepository.searchByApelidoContains(empresa, pageable).map(usuarioMapper::toDto));
    }

    @Override
    public PagedModel<EmpresaDTO> getAllEmpresas(Pageable pageable) {
        return new PagedModel<>(
            empresaRepository.findAll(pageable).map(usuarioMapper::toDto));
    }

    @Override
    public Optional<EmpresaDTO> getEmpresaByCNPJ(String cnpj) {
        return empresaRepository.findByCnpj(cnpj).map(value -> usuarioMapper.toDto(value));
    }

    @Override
    @Transactional
    public UsuarioDTO update(UpdateUsuarioDTO dto, UUID uuid) {
        Usuario received = updateUsuarioMapper.toEntity(dto, uuid);
        Optional<Usuario> queryResult = usuarioRepository.findById(uuid);

        if (queryResult.isEmpty()) throw new UsuarioNotFoundException(uuid);

        Usuario found = queryResult.get();
        found.merge(received);

        return usuarioMapper.toDto(usuarioRepository.saveAndFlush(found));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        Optional<UsuarioDTO> usuario = getByUuid(uuid);

        if (usuario.isEmpty()) throw new UsuarioNotFoundException(uuid);

        usuarioRepository.deleteById(uuid);
    }

    @Override
    public Set<UsuarioDTO> findUsuariosByUUIDs(Set<UUID> uuids) {
        return usuarioRepository.findByUuidIn(uuids).stream().map(usuarioMapper::toDto).collect(Collectors.toSet());
    }

}
