package br.upe.ProjectNest.domain.usuarios.controllers;

import br.upe.ProjectNest.domain.common.pagination.PaginatedResult;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.EmpresaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.PessoaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.registration.UsuarioRegistrationDTO;
import br.upe.ProjectNest.domain.usuarios.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    @PostMapping
    public ResponseEntity<UsuarioDTO> register(@Valid @RequestBody UsuarioRegistrationDTO usuario) {
        UsuarioDTO saved = usuarioService.registerUsuario(usuario);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioDTO> getBtUuid(@PathVariable UUID uuid) {
        Optional<UsuarioDTO> result = usuarioService.getByUuid(uuid);
        return ResponseEntity.ok(result.orElseThrow(EntityNotFoundException::new));
    }

    @GetMapping("/apelido/{apelido}")
    public ResponseEntity<PaginatedResult<UsuarioDTO>> searchByApelido(
        @PathVariable("apelido") String apelido, @RequestParam("current-page") int currentPage,
        @RequestParam("page-size") int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return ResponseEntity.ok(usuarioService.searchByApelido(apelido, pageable));
    }

    @GetMapping("/pessoas/{apelido}")
    public ResponseEntity<PaginatedResult<PessoaDTO>> searchPessoaByApelido(
        @PathVariable("apelido") String apelido, @RequestParam("current-page") int currentPage,
        @RequestParam("page-size") int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return ResponseEntity.ok(usuarioService.searchPessoaByApelido(apelido, pageable));
    }

    @GetMapping("/empresas/{nome}")
    public ResponseEntity<PaginatedResult<EmpresaDTO>> searchEmpresaByNome(
        @PathVariable("nome") String nome, @RequestParam("current-page") int currentPage,
        @RequestParam("page-size") int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return ResponseEntity.ok(usuarioService.searchEmpresaByNome(nome, pageable));
    }

    @GetMapping("/empresas")
    public ResponseEntity<PaginatedResult<EmpresaDTO>> getAllEmpresas(
        @RequestParam("current-page") int currentPage, @RequestParam("page-size") int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return ResponseEntity.ok(usuarioService.getAllEmpresas(pageable));
    }

    @GetMapping("/empresas/cnpj/{cnpj}")
    public ResponseEntity<EmpresaDTO> getByCNPJ(@PathVariable String cnpj) {
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
