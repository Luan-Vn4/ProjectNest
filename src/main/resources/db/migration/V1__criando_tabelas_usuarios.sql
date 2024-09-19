CREATE TABLE empresas
(
    uuid UUID        NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    CONSTRAINT pk_empresas PRIMARY KEY (uuid)
);

CREATE TABLE pessoas
(
    uuid      UUID        NOT NULL,
    nome      VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    pronomes  VARCHAR(25),
    CONSTRAINT pk_pessoas PRIMARY KEY (uuid)
);

CREATE TABLE usuarios
(
    uuid    UUID         NOT NULL,
    apelido VARCHAR(50)  NOT NULL,
    email   VARCHAR(255) NOT NULL,
    senha   CHAR(60)     NOT NULL,
    CONSTRAINT pk_usuarios PRIMARY KEY (uuid)
);

ALTER TABLE empresas
    ADD CONSTRAINT uc_empresas_cnpj UNIQUE (cnpj);

ALTER TABLE usuarios
    ADD CONSTRAINT uc_usuarios_apelido UNIQUE (apelido);

ALTER TABLE usuarios
    ADD CONSTRAINT uc_usuarios_email UNIQUE (email);

ALTER TABLE empresas
    ADD CONSTRAINT FK_EMPRESAS_ON_UUID FOREIGN KEY (uuid) REFERENCES usuarios (uuid);

ALTER TABLE pessoas
    ADD CONSTRAINT FK_PESSOAS_ON_UUID FOREIGN KEY (uuid) REFERENCES usuarios (uuid);