package br.upe.ProjectNest.domain.usuarios.services;

import br.upe.ProjectNest.domain.usuarios.dtos.fetch.EmpresaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.PessoaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioMapper;
import br.upe.ProjectNest.domain.usuarios.dtos.registration.UsuarioCreationDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.registration.UsuarioCreationMapper;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import br.upe.ProjectNest.domain.usuarios.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    UsuarioMapper usuarioMapper;

    UsuarioCreationMapper usuarioCreationMapper;

    UsuarioRepository usuarioRepository;


    @Override
    @Transactional
    public UsuarioDTO registerUsuario(UsuarioCreationDTO dto) {
        Usuario usuario = usuarioCreationMapper.toEntity(dto);
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
    public Page<UsuarioDTO> searchByApelido(String apelido, Pageable pageable) {
        return usuarioRepository.searchByApelido(apelido, pageable).map(usuarioMapper::toDto);
    }

    @Override
    public Page<PessoaDTO> searchPessoaByApelido(String apelido, Pageable pageable) {
        return usuarioRepository.searchPessoaByApelido(apelido, pageable).map(usuarioMapper::toDto);
    }

    @Override
    public Page<EmpresaDTO> searchEmpresaByNome(String empresa, Pageable pageable) {
        return usuarioRepository.searchEmpresaByNomeContains(empresa, pageable).map(usuarioMapper::toDto);
    }

    @Override
    public Page<EmpresaDTO> getAllEmpresas(Pageable pageable) {
        return usuarioRepository.findAllEmpresas(pageable).map(usuarioMapper::toDto);
    }

    @Override
    public Optional<EmpresaDTO> getEmpresaByCNPJ(String cnpj) {
        return usuarioRepository.findEmpresaByCNPJ(cnpj).map(value -> usuarioMapper.toDto(value));
    }

    @Override
    @Transactional
    public UsuarioDTO update(UsuarioDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public void deleteUsuario(UUID uuid) {
        usuarioRepository.deleteById(uuid);
    }

}
