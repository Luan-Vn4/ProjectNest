package br.upe.ProjectNest.domain.usuarios.repositories;

import br.upe.ProjectNest.domain.usuarios.models.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {

    Page<Empresa> searchByApelidoContains(String nome, Pageable pageable);

    Optional<Empresa> findByCnpj(String cnpj);

}
