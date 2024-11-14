package br.upe.ProjectNest.domain.projetos.repositories;

import br.upe.ProjectNest.domain.projetos.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {

    Optional<Projeto> findByUrlRepositorio(String url);
}
