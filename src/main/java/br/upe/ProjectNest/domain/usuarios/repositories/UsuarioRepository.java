package br.upe.ProjectNest.domain.usuarios.repositories;

import br.upe.ProjectNest.domain.usuarios.models.Empresa;
import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Page<Usuario> searchByApelido(String apelido, Pageable pageable);

    @Query("SELECT p FROM Pessoa p WHERE p.apelido = :apelido")
    Page<Pessoa> searchPessoaByApelido(String apelido, Pageable pageable);

    @Query("SELECT e FROM Empresa e WHERE e.apelido = :nome")
    Page<Empresa> searchEmpresaByNome(String nome, Pageable pageable);

    @Query("SELECT e FROM Empresa e")
    Page<Empresa> findAllEmpresas(Pageable pageable);

    @Query("SELECT e FROM Empresa e WHERE e.cnpj = :cnpj")
    Page<Empresa> findEmpresaByCNPJ(String cnpj, Pageable pageable);

}
