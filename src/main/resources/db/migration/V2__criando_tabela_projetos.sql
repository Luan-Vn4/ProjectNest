CREATE TABLE projetos
(
    uuid            UUID PRIMARY KEY,
    id_dono         UUID         NOT NULL,
    titulo          VARCHAR(100) NOT NULL,
    descricao       TEXT NOT NULL,
    url_repositorio VARCHAR(255) UNIQUE,
    CONSTRAINT fk_dono FOREIGN KEY (id_dono) REFERENCES usuarios (uuid) ON DELETE CASCADE
);

CREATE TYPE escopo_enum AS ENUM (
    'SAUDE',
    'GERENCIAMENTO',
    'INTELIGENCIA_ARTIFICIAL',
    'EDUCACAO',
    'CULINARIA',
    'JOGO',
    'BIOLOGIA'
);

CREATE TYPE status_enum AS ENUM (
    'PENDENTE',
    'CONCLUIDO',
    'EM_ANDAMENTO'
);

ALTER TABLE projetos
    ADD COLUMN escopo escopo_enum NOT NULL,
    ADD COLUMN status status_enum NOT NULL;