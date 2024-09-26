package br.upe.ProjectNest.domain.projetos.controllers;

import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoDTO;
import br.upe.ProjectNest.domain.projetos.models.Projeto;
import br.upe.ProjectNest.domain.projetos.services.ProjetoService;
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

    @GetMapping("/all")
    public ResponseEntity<List<Projeto>> getAll() {
        System.out.println("entrou");
        return ResponseEntity.ok().body(projetoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok().body(projetoService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UUID> create(@RequestBody ProjetoDTO projetoDTO) {
        return ResponseEntity.ok().body(projetoService.save(projetoDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        try {
        projetoService.delete(id);
        return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}