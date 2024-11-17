package br.upe.ProjectNest.domain.usuarios.services;

import br.upe.ProjectNest.domain.usuarios.dtos.EmpresaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.PessoaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioMapper;
import br.upe.ProjectNest.domain.usuarios.models.Empresa;
import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import br.upe.ProjectNest.domain.usuarios.repositories.EmpresaRepository;
import br.upe.ProjectNest.domain.usuarios.repositories.PessoaRepository;
import br.upe.ProjectNest.domain.usuarios.repositories.UsuarioRepository;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.UsuarioCreationDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.UsuarioCreationMapper;
import br.upe.ProjectNest.infrastructure.security.authentication.authorities.Role;
import br.upe.ProjectNest.infrastructure.security.authentication.authorities.RoleNames;
import br.upe.ProjectNest.infrastructure.security.authentication.services.roles.RoleService;
import jakarta.persistence.EntityNotFoundException;
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
    public PessoaDTO update(PessoaDTO dto) {
        Pessoa received = usuarioMapper.toEntity(dto);
        Optional<Pessoa> queryResult = pessoaRepository.findById(dto.uuid());

        if (queryResult.isEmpty()) throw new EntityNotFoundException(
            "Não foi possível encontrar uma pessoa com o UID: " + dto.uuid());

        Pessoa found = queryResult.get();
        found.merge(received);

        return usuarioMapper.toDto(usuarioRepository.saveAndFlush(found));
    }

    @Override
    @Transactional
    public EmpresaDTO update(EmpresaDTO dto) {
        Empresa received = usuarioMapper.toEntity(dto);
        Optional<Empresa> queryResult = empresaRepository.findById(dto.uuid());

        if (queryResult.isEmpty()) throw new EntityNotFoundException(
            "Não foi possível encontrar uma empresa com o UID: " + dto.uuid()
        );

        Empresa found = queryResult.get();
        found.merge(received);

        return usuarioMapper.toDto(usuarioRepository.saveAndFlush(found));
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        usuarioRepository.deleteById(uuid);
    }

    @Override
    public Set<UsuarioDTO> findUsuariosByUUIDs(Set<UUID> uuids) {
        return usuarioRepository.findByUuidIn(uuids).stream().map(usuarioMapper::toDto).collect(Collectors.toSet());
    }
}
