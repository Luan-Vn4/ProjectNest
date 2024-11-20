package br.upe.ProjectNest.infrastructure.security.authentication.authorities;

import br.upe.ProjectNest.domain.usuarios.models.Empresa;
import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Set;

@Component
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class RoleNames {

    // CONSTANTES
    public static String PESSOA = "ROLE_PESSOA";

    public static String EMPRESA = "ROLE_EMPRESA";

    public static String ADMIN = "ROLE_ADMIN";

    /**
     * Roles padrões, segundo o tipo de usuário
     */
    public static Map<Class<? extends Usuario>, Set<String>> defaultRoles = Map.of(
        Pessoa.class, Set.of(PESSOA),
        Empresa.class, Set.of(EMPRESA)
    );

    public String getPessoa() {
        return PESSOA;
    }

    public String getEmpresa() {
        return EMPRESA;
    }

    public String getAdmin() {
        return ADMIN;
    }

}
