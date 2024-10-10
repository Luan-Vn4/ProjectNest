CREATE TABLE projetos
(
    uuid            UUID PRIMARY KEY,
    id_dono         UUID         NOT NULL,
    titulo          VARCHAR(100) NOT NULL,
    descricao       TEXT         NOT NULL,
    escopo          VARCHAR      NOT NULL,
    status          VARCHAR      NOT NULL,
    url_repositorio VARCHAR(255) UNIQUE,
    CONSTRAINT fk_dono FOREIGN KEY (id_dono) REFERENCES usuarios (uuid) ON DELETE CASCADE
);
