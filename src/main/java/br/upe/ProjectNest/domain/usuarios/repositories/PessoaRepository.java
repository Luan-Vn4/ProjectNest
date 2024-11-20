package br.upe.ProjectNest.domain.usuarios.repositories;

import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    Page<Pessoa> searchByApelidoContains(String apelido, Pageable pageable);

}
