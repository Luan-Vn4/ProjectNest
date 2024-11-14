package br.upe.ProjectNest.domain.contribuicoes.repositories;

import br.upe.ProjectNest.domain.contribuicoes.models.Contribuicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContribuicaoRepository extends JpaRepository<Contribuicao, UUID> {
}
