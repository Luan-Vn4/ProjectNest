CREATE TABLE contribuicoes (
    uuid UUID PRIMARY KEY,
    id_usuario UUID,
    id_projeto UUID,
    titulo VARCHAR(100),
    descricao TEXT,
    url_repositorio VARCHAR(255) UNIQUE,
    CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios (uuid) ON DELETE CASCADE,
    CONSTRAINT fk_id_projeto FOREIGN KEY (id_projeto) REFERENCES projetos (uuid) ON DELETE  CASCADE

);