package br.upe.ProjectNest.domain.usuarios.controllers;

import br.upe.ProjectNest.domain.usuarios.dtos.*;
import br.upe.ProjectNest.domain.usuarios.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;


    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioDTO> getBtUuid(@PathVariable UUID uuid) {
        Optional<UsuarioDTO> result = usuarioService.getByUuid(uuid);
        return ResponseEntity.ok(result.orElseThrow(() ->
            new EntityNotFoundException("Não foi encontrado um usuário com UUID %s"
                .formatted(uuid))
        ));
    }

    @GetMapping(params = "apelido")
    public ResponseEntity<PagedModel<UsuarioDTO>> searchByApelido(
        @PathParam("apelido") String apelido, Pageable pageable) {
        return ResponseEntity.ok(usuarioService.searchByApelido(apelido, pageable));
    }

    @GetMapping(value = "/pessoas", params = "apelido")
    public ResponseEntity<PagedModel<PessoaDTO>> searchPessoaByApelido(@PathParam("apelido") String apelido,
                                                                       Pageable pageable) {
        return ResponseEntity.ok(usuarioService.searchPessoaByApelido(apelido, pageable));
    }

    @GetMapping(value = "/empresas", params = "nome")
    public ResponseEntity<PagedModel<EmpresaDTO>> searchEmpresaByNome(@PathParam("nome") String nome,
                                                                Pageable pageable) {
        return ResponseEntity.ok(usuarioService.searchEmpresaByNome(nome, pageable));
    }

    @GetMapping(value = "/empresas")
    public ResponseEntity<PagedModel<EmpresaDTO>> getAllEmpresas(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.getAllEmpresas(pageable));
    }

    @GetMapping(value = "/empresas", params = "cnpj")
    public ResponseEntity<EmpresaDTO> getByCNPJ(@PathParam("cnpj") String cnpj) {
        Optional<EmpresaDTO> result = usuarioService.getEmpresaByCNPJ(cnpj);
        return ResponseEntity.ok(result.orElseThrow(EntityNotFoundException::new));
    }


    @PutMapping(value = "/pessoas/{uuid}")
    public ResponseEntity<PessoaDTO> updateUsuario(@Valid @RequestBody UpdatePessoaDTO usuario,
                                                   @PathVariable(name="uuid") UUID uuid) {
        PessoaDTO updated = (PessoaDTO) usuarioService.update(usuario, uuid);
        return ResponseEntity.ok(updated);
    }

    @PutMapping(value = "/empresas/{uuid}")
    public ResponseEntity<EmpresaDTO> updateEmpresa(@Valid @RequestBody UpdateEmpresaDTO empresa,
                                                    @PathVariable(name="uuid") UUID uuid) {
        EmpresaDTO updated = (EmpresaDTO) usuarioService.update(empresa, uuid);
        return ResponseEntity.ok(updated);
    }

}
