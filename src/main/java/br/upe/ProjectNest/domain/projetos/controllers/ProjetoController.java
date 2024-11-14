package br.upe.ProjectNest.domain.projetos.controllers;

import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoCreationDTO;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoDTO;
import br.upe.ProjectNest.domain.projetos.services.ProjetoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/projetos")
@AllArgsConstructor
public class ProjetoController {

    private ProjetoService projetoService;

    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> getAll() {
        return ResponseEntity.ok().body(projetoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok().body(projetoService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProjetoDTO> create(@Valid @RequestBody ProjetoCreationDTO projetoDTO) {
        return ResponseEntity.ok().body(projetoService.save(projetoDTO));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody ProjetoDTO projetoDTO) {
        projetoService.update(projetoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        try {
            projetoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
