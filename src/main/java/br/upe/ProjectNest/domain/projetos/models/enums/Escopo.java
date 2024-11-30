package br.upe.ProjectNest.domain.projetos.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Escopo {
    SAUDE("Saúde"),
    GERENCIAMENTO("Gerenciamento"),
    INTELIGENCIA_ARTIFICIAL("Inteligência Artificial"),
    EDUCACAO("Educação"),
    CULINARIA("Culinária"),
    JOGO("Jogo"),
    BIOLOGIA("Biologia");

    private final String descricao;

    Escopo(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }
}
