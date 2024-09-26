package br.upe.ProjectNest.domain.usuarios.repositories;

import br.upe.ProjectNest.domain.usuarios.models.Empresa;
import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.apelido LIKE %:apelido%")
    Page<Usuario> searchByApelido(String apelido, Pageable pageable);

    @Query("SELECT p FROM Pessoa p WHERE p.apelido = :apelido")
    Page<Pessoa> searchPessoaByApelido(String apelido, Pageable pageable);

    @Query("SELECT e FROM Empresa e WHERE e.apelido = :nome")
    Page<Empresa> searchEmpresaByNome(String nome, Pageable pageable);

    @Query("SELECT e FROM Empresa e")
    Page<Empresa> findAllEmpresas(Pageable pageable);

    @Query("SELECT e FROM Empresa e WHERE e.cnpj = :cnpj")
    Optional<Empresa> findEmpresaByCNPJ(String cnpj);

}
