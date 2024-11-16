ALTER TABLE contribuicoes
    DROP CONSTRAINT fk_id_usuario;

CREATE TABLE contribuicoes_usuarios
(
    id_contribuicao UUID NOT NULL,
    id_usuario      UUID NOT NULL,
    CONSTRAINT pk_contribuicoes_usuarios PRIMARY KEY (id_contribuicao, id_usuario)
);

ALTER TABLE contribuicoes_usuarios
    ADD CONSTRAINT fk_conusu_on_contribuicao FOREIGN KEY (id_contribuicao) REFERENCES contribuicoes (uuid);

ALTER TABLE contribuicoes_usuarios
    ADD CONSTRAINT fk_conusu_on_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios (uuid);

ALTER TABLE contribuicoes
    DROP COLUMN id_usuario;

ALTER TABLE contribuicoes
    ALTER COLUMN descricao TYPE VARCHAR(255) USING (descricao::VARCHAR(255));

ALTER TABLE contribuicoes
    ALTER COLUMN descricao SET NOT NULL;

ALTER TABLE contribuicoes
    ALTER COLUMN id_projeto SET NOT NULL;

ALTER TABLE contribuicoes
    ALTER COLUMN titulo SET NOT NULL;