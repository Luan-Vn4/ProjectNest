package br.upe.ProjectNest.domain.usuarios.controllers;

import br.upe.ProjectNest.domain.usuarios.dtos.fetch.EmpresaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.PessoaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.registration.EmpresaCreationDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.registration.PessoaCreationDTO;
import br.upe.ProjectNest.domain.usuarios.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@AllArgsConstructor
public class UsuarioController {

    UsuarioService usuarioService;

    @PostMapping("/pessoas")
    public ResponseEntity<PessoaDTO> register(@Valid @RequestBody PessoaCreationDTO dto) {
        PessoaDTO pessoaDTO = (PessoaDTO) usuarioService.registerUsuario(dto);
        return ResponseEntity.ok().body(pessoaDTO);
    }

    @PostMapping("/empresas")
    public ResponseEntity<EmpresaDTO> register(@Valid @RequestBody EmpresaCreationDTO dto) {
        EmpresaDTO empresaDTO = (EmpresaDTO) usuarioService.registerUsuario(dto);
        return ResponseEntity.ok().body(empresaDTO);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioDTO> getBtUuid(@PathVariable UUID uuid) {
        Optional<UsuarioDTO> result = usuarioService.getByUuid(uuid);
        return ResponseEntity.ok(result.orElseThrow(EntityNotFoundException::new));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> searchByApelido(
        @PathParam("apelido") String apelido, Pageable pageable) {
        return ResponseEntity.ok(usuarioService.searchByApelido(apelido, pageable));
    }

    @GetMapping("/pessoas/apelido/{apelido}")
    public ResponseEntity<Page<PessoaDTO>> searchPessoaByApelido(@PathVariable("apelido") String apelido,
                                                                 Pageable pageable) {
        return ResponseEntity.ok(usuarioService.searchPessoaByApelido(apelido, pageable));
    }

    @GetMapping(value = "/empresas", params = "nome")
    public ResponseEntity<Page<EmpresaDTO>> searchEmpresaByNome(@PathParam("nome") String nome,
                                                                Pageable pageable) {
        return ResponseEntity.ok(usuarioService.searchEmpresaByNome(nome, pageable));
    }

    @GetMapping(value = "/empresas")
    public ResponseEntity<Page<EmpresaDTO>> getAllEmpresas(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.getAllEmpresas(pageable));
    }

    @GetMapping(value = "/empresas", params = "cnpj")
    public ResponseEntity<EmpresaDTO> getByCNPJ(@PathParam("cnpj") String cnpj) {
        Optional<EmpresaDTO> result = usuarioService.getEmpresaByCNPJ(cnpj);
        return ResponseEntity.ok(result.orElseThrow(EntityNotFoundException::new));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody UsuarioDTO usuario) {
        UsuarioDTO updated = usuarioService.update(usuario);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid) {
        usuarioService.deleteUsuario(uuid);
        return ResponseEntity.ok().build();
    }


}
