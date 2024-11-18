package br.upe.ProjectNest.domain.contribuicoes.controllers;

import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoCreationDTO;
import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoDTO;
import br.upe.ProjectNest.domain.contribuicoes.services.ContribuicaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contribuicoes")
@AllArgsConstructor
public class ContribuicaoController {

    private final ContribuicaoService contribuicaoService;

    @GetMapping
    public ResponseEntity<List<ContribuicaoDTO>> getAll() {
        return ResponseEntity.ok().body(contribuicaoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContribuicaoDTO> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok().body(contribuicaoService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ContribuicaoDTO> create(@Valid @RequestBody ContribuicaoCreationDTO contribuicaoDTO) {
        return ResponseEntity.ok().body(contribuicaoService.save(contribuicaoDTO));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody ContribuicaoDTO contribuicaoDTO) {
        contribuicaoService.update(contribuicaoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            contribuicaoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}