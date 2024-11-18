CREATE TABLE privileges (
    id   SMALLINT NOT NULL,
    name VARCHAR(50) UNIQUE,
    CONSTRAINT pk_privilege PRIMARY KEY (id)
);

CREATE TABLE roles (
    id   SMALLINT NOT NULL,
    name VARCHAR(50) UNIQUE,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE roles_privileges (
    id_privilege SMALLINT NOT NULL REFERENCES privileges(id),
    id_role      SMALLINT NOT NULL REFERENCES roles(id),
    CONSTRAINT pk_roles_privileges PRIMARY KEY (id_privilege, id_role)
);

CREATE TABLE usuarios_roles (
    id_role      SMALLINT NOT NULL REFERENCES roles(id),
    uuid_usuario UUID     NOT NULL REFERENCES usuarios(uuid),
    CONSTRAINT pk_usuarios_roles PRIMARY KEY (id_role, uuid_usuario)
);

-- CRIANDO ROLES
INSERT INTO roles (id, name) VALUES
    (1, 'ROLE_PESSOA'),
    (2, 'ROLE_EMPRESA'),
    (3, 'ROLE_ADMIN');

INSERT INTO privileges (id, name) VALUES
    (1, 'CRIAR_PROJETOS'),
    (2, 'CONTRIBUIR_PROJETOS'),
    (3, 'DELETAR_PROJETOS'),
    (4, 'VISUALIZAR_PROJETOS'),
    (5, 'EDITAR_PROJETOS'),
    (6, 'EDITAR_COMENTARIOS'),
    (7, 'CRIAR_COMENTARIOS'),
    (8, 'DELETAR_COMENTARIOS');

INSERT INTO roles_privileges (id_role, id_privilege) VALUES
    -- Privilégios de ROLE_PESSOA
    (1, 1), -- CRIAR_PROJETOS
    (1, 2), -- CONTRIBUIR_PROJETOS
    (1, 4), -- VISUALIZAR_PROJETOS
    (1, 7), -- CRIAR_COMENTARIOS

    -- Privilégios de ROLE_EMPRESA
    (2, 1), -- CRIAR_PROJETOS
    (2, 4), -- VISUALIZAR_PROJETOS
    (2, 7), -- CRIAR_COMENTARIOS

    -- Privilégios de ROLE_ADMIN
    (3, 1), -- CRIAR_PROJETOS
    (3, 2), -- CONTRIBUIR_PROJETOS
    (3, 3), -- DELETAR_PROJETOS
    (3, 4), -- VISUALIZAR_PROJETOS
    (3, 5), -- EDITAR_PROJETOS
    (3, 6), -- EDITAR_COMENTARIOS
    (3, 7), -- CRIAR_COMENTARIOS
    (3, 8); -- DELETAR_COMENTARIOS